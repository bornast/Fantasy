import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Match } from 'src/app/models/match';
import { Pagination } from 'src/app/models/pagination';
import { MatchService } from 'src/app/services/match.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-match-list',
  templateUrl: './match-list.component.html',
  styleUrls: ['./match-list.component.scss']
})
export class MatchListComponent implements OnInit {

    matchesForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private matchService: MatchService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadMatches();
	}

	loadMatches() {
        this.matchService.getMatchesByFilter(this.searchTxt, this.currentPage-1).subscribe((matches) => {
			this.matchesForList = matches.result;
            this.pagination = matches.pagination;
            this.pagination.currentPage += 1;
            console.log("matchesforlist", this.matchesForList);
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.matchService.deleteMatch(id).subscribe(() => {
				this.loadMatches();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadMatches();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadMatches();
    }

    calculateName(matchForList: Match) {

    }

}
