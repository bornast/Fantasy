import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { RoleService } from 'src/app/services/role.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.scss']
})
export class RoleListComponent implements OnInit {
    
    rolesForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private roleService: RoleService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadRoles();
	}

	loadRoles() {
        this.roleService.getRolesByFilter(this.searchTxt, this.currentPage-1).subscribe((roles) => {
			this.rolesForList = roles.result;
            this.pagination = roles.pagination;
            this.pagination.currentPage += 1;
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

    changePage(event: any) {
        this.currentPage = event;
        this.loadRoles();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadRoles();
    }

}
