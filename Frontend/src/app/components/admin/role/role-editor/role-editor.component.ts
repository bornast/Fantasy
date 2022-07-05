import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { RecordName } from 'src/app/models/recordName';
import { RoleService } from 'src/app/services/role.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-role-editor',
  templateUrl: './role-editor.component.html',
  styleUrls: ['./role-editor.component.scss']
})
export class RoleEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	role: RecordName;
	roleToSave: any = {
	};

	constructor(
        private roleService: RoleService, 
        private toast: ToastService, 
        private route: ActivatedRoute) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getRole(id);			
		}
	}

	getRole(id: any) {
		this.roleService.getRole(id).subscribe((role) => {
			this.role = role;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.roleService.createRole(this.roleToSave).subscribe((role) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getRole(role["id"]);
			});
		}
		else {
			this.roleService.updateRole(this.role.id, this.roleToSave).subscribe((role) => {
				this.toast.success("Successfully updated!");
				this.getRole(role["id"]);
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
		this.roleToSave.name = this.role.name;
	}

}
