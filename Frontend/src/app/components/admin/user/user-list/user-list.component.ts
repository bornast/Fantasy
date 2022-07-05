import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { User } from 'src/app/models/User';
import { ToastService } from 'src/app/services/toast.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

    usersForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private userService: UserService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadUsers();
	}

    loadUsers() {
        this.userService.getUsersByFilter(this.searchTxt, this.currentPage-1).subscribe((users) => {
			this.usersForList = this.transformUserForList(users.result);
            this.pagination = users.pagination;
            this.pagination.currentPage += 1;
		});
	}

	private transformUserForList(users: User[]): any[] {
		let usersForList = [];

		users.forEach(user => {
			let userForList = {
				id: user.id,
				name: user.firstName + " " + user.lastName,
				username: user.username
			};

			usersForList.push(userForList);
		});

		return usersForList;
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.userService.deleteUser(id).subscribe(() => {
				this.loadUsers();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadUsers();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadUsers();
    }

}
