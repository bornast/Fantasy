import { Component, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { Pagination } from 'src/app/models/pagination';
import { Team } from 'src/app/models/team';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-team-picker',
  templateUrl: './team-picker.component.html',
  styleUrls: ['./team-picker.component.scss']
})
export class TeamPickerComponent implements OnInit {

    teamsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

    constructor(private teamService: TeamService, private toast: ToastService) { }

    ngOnInit() {
		this.loadTeams();
	}

	loadTeams() {
        this.teamService.getUnfavoredTeamsByFilter(this.searchTxt, this.currentPage-1).subscribe((teams) => {
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

    markAsFavourite(team: Team) {
        this.teamService.markTeamAsFavourite(team.id).subscribe(() => {
            this.loadTeams();
            this.toast.success("Successfully marked " + team.name + " as favourite");
        }, () => {
            this.toast.error("Failed to mark as favourite!");
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
