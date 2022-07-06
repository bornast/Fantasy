import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { President } from 'src/app/models/president';
import { PresidentService } from 'src/app/services/president.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-president-editor',
  templateUrl: './president-editor.component.html',
  styleUrls: ['./president-editor.component.scss']
})
export class PresidentEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	president: President;
	presidentToSave: any = {
	};

	constructor(
        private presidentService: PresidentService, 
        private toast: ToastService, 
        private route: ActivatedRoute) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getPresident(id);			
		}
	}

	getPresident(id: any) {
		this.presidentService.getPresident(id).subscribe((president) => {
			this.president = president;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.presidentService.createPresident(this.presidentToSave).subscribe((president) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getPresident(president["id"]);
			});
		}
		else {
			this.presidentService.updatePresident(this.president.id, this.presidentToSave).subscribe((president) => {
				this.toast.success("Successfully updated!");
				this.getPresident(president["id"]);
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
		this.presidentToSave.name = this.president.name;
        this.presidentToSave.dateOfBirth = this.president.dateOfBirth;
	}

}
