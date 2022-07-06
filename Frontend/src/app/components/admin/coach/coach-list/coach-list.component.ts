import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { CoachService } from 'src/app/services/coach.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-coach-list',
  templateUrl: './coach-list.component.html',
  styleUrls: ['./coach-list.component.scss']
})
export class CoachListComponent implements OnInit {

    coachesForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private coachService: CoachService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadCoaches();
	}

	loadCoaches() {
        this.coachService.getCoachesByFilter(this.searchTxt, this.currentPage-1).subscribe((coaches) => {
			this.coachesForList = coaches.result;
            this.pagination = coaches.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.coachService.deleteCoach(id).subscribe(() => {
				this.loadCoaches();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadCoaches();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadCoaches();
    }

}
