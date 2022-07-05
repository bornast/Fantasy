import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoleService } from 'src/app/services/role.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.scss']
})
export class RoleListComponent implements OnInit {

    rolesForList: any[];
	// pagination: Pagination;
	pageNumber: any = 1;
	searchTxt: string;

	constructor(private roleService: RoleService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadRoles();
	}

	loadRoles() {
		this.roleService.getRecordNames().subscribe((roles) => {
			this.rolesForList = roles;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.roleService.deleteRole(id).subscribe(() => {
				this.loadRoles();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

}
