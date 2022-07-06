import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { RecordName } from 'src/app/models/recordName';
import { StadiumService } from 'src/app/services/stadium.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-stadium-editor',
  templateUrl: './stadium-editor.component.html',
  styleUrls: ['./stadium-editor.component.scss']
})
export class StadiumEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	stadium: RecordName;
	stadiumToSave: any = {
	};

	constructor(
        private stadiumService: StadiumService, 
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getStadium(id);			
		}
	}

	getStadium(id: any) {
		this.stadiumService.getStadium(id).subscribe((stadium) => {
			this.stadium = stadium;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.stadiumService.createStadium(this.stadiumToSave).subscribe((stadium) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getStadium(stadium["id"]);
                this.router.navigate(['/admin/stadium-list']);
			});
		}
		else {
			this.stadiumService.updateStadium(this.stadium.id, this.stadiumToSave).subscribe((stadium) => {
				this.toast.success("Successfully updated!");
				this.getStadium(stadium["id"]);
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
		this.stadiumToSave.name = this.stadium.name;
	}

}
