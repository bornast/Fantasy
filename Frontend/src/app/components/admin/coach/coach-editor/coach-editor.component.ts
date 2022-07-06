import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { Coach } from 'src/app/models/coach';
import { CoachService } from 'src/app/services/coach.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-coach-editor',
  templateUrl: './coach-editor.component.html',
  styleUrls: ['./coach-editor.component.scss']
})
export class CoachEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	coach: Coach;
	coachToSave: any = {
	};

	constructor(
        private coachService: CoachService, 
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getCoach(id);			
		}
	}

	getCoach(id: any) {
		this.coachService.getCoach(id).subscribe((coach) => {
			this.coach = coach;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.coachService.createCoach(this.coachToSave).subscribe((coach) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getCoach(coach["id"]);
                this.router.navigate(['/admin/coach-list']);
			});
		}
		else {
			this.coachService.updateCoach(this.coach.id, this.coachToSave).subscribe((coach) => {
				this.toast.success("Successfully updated!");
				this.getCoach(coach["id"]);
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
		this.coachToSave.name = this.coach.name;
        this.coachToSave.dateOfBirth = this.coach.dateOfBirth;
	}

}
