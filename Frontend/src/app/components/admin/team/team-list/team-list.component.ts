import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.scss']
})
export class TeamListComponent implements OnInit {

    teamsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private teamService: TeamService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadTeams();
	}

	loadTeams() {
        this.teamService.getTeamsByFilter(this.searchTxt, this.currentPage-1).subscribe((teams) => {
			this.teamsForList = teams.result;
            this.pagination = teams.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.teamService.deleteTeam(id).subscribe(() => {
				this.loadTeams();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadTeams();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadTeams();
    }

}
