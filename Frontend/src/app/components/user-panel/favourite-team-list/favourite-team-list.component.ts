import { Component, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { Pagination } from 'src/app/models/pagination';
import { Team } from 'src/app/models/team';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-favourite-team-list',
  templateUrl: './favourite-team-list.component.html',
  styleUrls: ['./favourite-team-list.component.scss']
})
export class FavouriteTeamListComponent implements OnInit {

    teamsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

    constructor(private teamService: TeamService, private toast: ToastService) { }

    ngOnInit() {
		this.loadTeams();
	}

	loadTeams() {
        this.teamService.getFavouriteTeamsByFilter(this.searchTxt, this.currentPage-1).subscribe((teams) => {
			this.teamsForList = teams.result;
            this.pagination = teams.pagination;
            this.pagination.currentPage += 1;
		});
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadTeams();
    }

    search() {
        this.loadTeams();
    }

    markAsUnfavored(team: Team) {
        this.teamService.markTeamAsUnfavored(team.id).subscribe(() => {
            this.loadTeams();
            this.toast.success("Successfully marked " + team.name + " as unfavored");
        }, () => {
            this.toast.error("Failed to mark as unfavored!");
        });
    }

    customOptions: OwlOptions = {
		loop: true,
		nav: true,
		dots: false,
		animateOut: 'fadeOut',
		animateIn: 'fadeIn',
		autoplayHoverPause: true,
		autoplay: true,
		mouseDrag: false,
		items: 1,
        navText: [
            "<i class='flaticon-left-chevron'></i>",
            "<i class='flaticon-right-chevron'></i>"
        ]
    };

}
