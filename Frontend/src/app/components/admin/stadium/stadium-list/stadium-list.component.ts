import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { StadiumService } from 'src/app/services/stadium.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-stadium-list',
  templateUrl: './stadium-list.component.html',
  styleUrls: ['./stadium-list.component.scss']
})
export class StadiumListComponent implements OnInit {

    stadiumsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private stadiumService: StadiumService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadStadiums();
	}

	loadStadiums() {
        this.stadiumService.getStadiumsByFilter(this.searchTxt, this.currentPage-1).subscribe((stadiums) => {
			this.stadiumsForList = stadiums.result;
            this.pagination = stadiums.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.stadiumService.deleteStadium(id).subscribe(() => {
				this.loadStadiums();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadStadiums();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadStadiums();
    }

}
