import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { LeagueService } from 'src/app/services/league.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-league-list',
  templateUrl: './league-list.component.html',
  styleUrls: ['./league-list.component.scss']
})
export class LeagueListComponent implements OnInit {

    leaguesForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private leagueService: LeagueService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadLeagues();
	}

	loadLeagues() {
        this.leagueService.getLeaguesByFilter(this.searchTxt, this.currentPage-1).subscribe((leagues) => {
			this.leaguesForList = leagues.result;
            this.pagination = leagues.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.leagueService.deleteLeague(id).subscribe(() => {
				this.loadLeagues();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadLeagues();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadLeagues();
    }

}
