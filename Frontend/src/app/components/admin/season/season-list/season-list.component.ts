import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { SeasonService } from 'src/app/services/season.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-season-list',
  templateUrl: './season-list.component.html',
  styleUrls: ['./season-list.component.scss']
})
export class SeasonListComponent implements OnInit {

    seasonsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private seasonService: SeasonService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadSeasons();
	}

	loadSeasons() {
        this.seasonService.getSeasonsByFilter(this.searchTxt, this.currentPage-1).subscribe((seasons) => {
			this.seasonsForList = seasons.result;
            this.pagination = seasons.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.seasonService.deleteSeasons(id).subscribe(() => {
				this.loadSeasons();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadSeasons();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadSeasons();
    }

}
