import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { Referee } from 'src/app/models/referee';
import { RefereeService } from 'src/app/services/referee.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-referee-editor',
  templateUrl: './referee-editor.component.html',
  styleUrls: ['./referee-editor.component.scss']
})
export class RefereeEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	referee: Referee;
	refereeToSave: any = {
	};

	constructor(
        private refereeService: RefereeService, 
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getReferee(id);			
		}
	}

	getReferee(id: any) {
		this.refereeService.getReferee(id).subscribe((referee) => {
			this.referee = referee;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.refereeService.createReferee(this.refereeToSave).subscribe((referee) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getReferee(referee["id"]);
                this.router.navigate(['/admin/referee-list']);
			});
		}
		else {
			this.refereeService.updateReferee(this.referee.id, this.refereeToSave).subscribe((referee) => {
				this.toast.success("Successfully updated!");
				this.getReferee(referee["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

    loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
	}

	private prepareSelectedData() {
		this.refereeToSave.name = this.referee.name;
        this.refereeToSave.dateOfBirth = this.referee.dateOfBirth;
	}

}
