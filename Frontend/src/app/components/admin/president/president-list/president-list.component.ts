import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { PresidentService } from 'src/app/services/president.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-president-list',
  templateUrl: './president-list.component.html',
  styleUrls: ['./president-list.component.scss']
})
export class PresidentListComponent implements OnInit {

    presidentsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private presidentService: PresidentService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadPresidents();
	}

	loadPresidents() {
        this.presidentService.getPresidentsByFilter(this.searchTxt, this.currentPage-1).subscribe((presidents) => {
			this.presidentsForList = presidents.result;
            this.pagination = presidents.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.presidentService.deletePresident(id).subscribe(() => {
				this.loadPresidents();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadPresidents();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadPresidents();
    }

}
