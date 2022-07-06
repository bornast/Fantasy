import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { RefereeService } from 'src/app/services/referee.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-referee-list',
  templateUrl: './referee-list.component.html',
  styleUrls: ['./referee-list.component.scss']
})
export class RefereeListComponent implements OnInit {

    refereesForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private refereeService: RefereeService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadReferees();
	}

	loadReferees() {
        this.refereeService.getRefereesByFilter(this.searchTxt, this.currentPage-1).subscribe((referees) => {
			this.refereesForList = referees.result;
            this.pagination = referees.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.refereeService.deleteReferee(id).subscribe(() => {
				this.loadReferees();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadReferees();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadReferees();
    }

}
