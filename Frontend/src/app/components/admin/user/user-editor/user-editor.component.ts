import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { ENTITYTYPE } from 'src/app/constants/entityTypeConstant';
import { RecordName } from 'src/app/models/recordName';
import { User } from 'src/app/models/User';
import { RoleService } from 'src/app/services/role.service';
import { ToastService } from 'src/app/services/toast.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-editor',
  templateUrl: './user-editor.component.html',
  styleUrls: ['./user-editor.component.scss']
})
export class UserEditorComponent implements OnInit {

    entityTypeId: any = ENTITYTYPE.user
	crudAction: any = CRUDACTION.create;
	user: User;
	userToSave: any = {
		roleIds: [],
        media: []
	};

	roles: RecordName[];

	constructor(
        private userService: UserService,
        private roleService: RoleService, 
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getUser(id);			
		}
		else {
			this.loadData();
		}		
	}

	getUser(id: any) {
		this.userService.getUser(id).subscribe((user) => {
			this.user = user;
			this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.userService.createUser(this.userToSave).subscribe((user) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getUser(user["id"]);
                this.router.navigate(['/admin/user-list']);
			});
		}
		else {
			this.userService.updateUser(this.user.id, this.userToSave).subscribe((user) => {
				this.toast.success("Successfully updated!");
				this.getUser(user["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

	loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
        this.loadRoles();
	}

	private prepareSelectedData() {
		this.userToSave.username = this.user.username;
        this.userToSave.firstName = this.user.firstName;
        this.userToSave.lastName = this.user.lastName;
		this.user.roles.forEach(role => {
			this.userToSave.roleIds.push(role.id);
		});
        this.userToSave.media = this.user.media.media;
        console.log("user", this.userToSave);
	}

	private loadRoles() {
		this.roleService.getRecordNames().subscribe((roles) => {
			this.roles = roles;
		});
	}

}
