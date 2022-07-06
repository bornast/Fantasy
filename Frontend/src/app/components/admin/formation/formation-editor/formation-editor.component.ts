import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { RecordName } from 'src/app/models/recordName';
import { FormationService } from 'src/app/services/formation.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-formation-editor',
  templateUrl: './formation-editor.component.html',
  styleUrls: ['./formation-editor.component.scss']
})
export class FormationEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	formation: RecordName;
	formationToSave: any = {
	};

	constructor(
        private formationService: FormationService, 
        private toast: ToastService, 
        private route: ActivatedRoute) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getFormation(id);			
		}
	}

	getFormation(id: any) {
		this.formationService.getFormation(id).subscribe((formation) => {
			this.formation = formation;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.formationService.createFormation(this.formationToSave).subscribe((formation) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getFormation(formation["id"]);
			});
		}
		else {
			this.formationService.updateFormation(this.formation.id, this.formationToSave).subscribe((formation) => {
				this.toast.success("Successfully updated!");
				this.getFormation(formation["id"]);
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
		this.formationToSave.name = this.formation.name;
	}

}
