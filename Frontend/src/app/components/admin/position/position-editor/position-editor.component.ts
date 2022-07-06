import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { RecordName } from 'src/app/models/recordName';
import { PositionService } from 'src/app/services/position.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-position-editor',
  templateUrl: './position-editor.component.html',
  styleUrls: ['./position-editor.component.scss']
})
export class PositionEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	position: RecordName;
	positionToSave: any = {
	};

	constructor(
        private positionService: PositionService, 
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getPosition(id);			
		}
	}

	getPosition(id: any) {
		this.positionService.getPosition(id).subscribe((position) => {
			this.position = position;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.positionService.createPosition(this.positionToSave).subscribe((position) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getPosition(position["id"]);
                this.router.navigate(['/admin/position-list']);
			});
		}
		else {
			this.positionService.updatePositions(this.position.id, this.positionToSave).subscribe((position) => {
				this.toast.success("Successfully updated!");
				this.getPosition(position["id"]);
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
		this.positionToSave.name = this.position.name;
	}

}
