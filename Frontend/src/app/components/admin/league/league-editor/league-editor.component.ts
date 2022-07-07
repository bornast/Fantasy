import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { League } from 'src/app/models/league';
import { RecordName } from 'src/app/models/recordName';
import { LeagueService } from 'src/app/services/league.service';
import { SeasonService } from 'src/app/services/season.service';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-league-editor',
  templateUrl: './league-editor.component.html',
  styleUrls: ['./league-editor.component.scss']
})
export class LeagueEditorComponent implements OnInit {

	crudAction: any = CRUDACTION.create;
	league: League;
	leagueToSave: any = {
		teamIds: []
	};

	teams: RecordName[];
    seasons: RecordName[];

	constructor(
        private leagueService: LeagueService,
        private teamService: TeamService,
        private seasonService: SeasonService,
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getLeague(id);			
		}
		else {
			this.loadData();
		}		
	}

	getLeague(id: any) {
		this.leagueService.getLeague(id).subscribe((league) => {
			this.league = league;
			this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.leagueService.createLeague(this.leagueToSave).subscribe((league) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getLeague(league["id"]);
                this.router.navigate(['/admin/league-list']);
			});
		}
		else {
			this.leagueService.updateLeague(this.league.id, this.leagueToSave).subscribe((league) => {
				this.toast.success("Successfully updated!");
				this.getLeague(league["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

	loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
        this.loadTeams();
        this.loadSeasons();
	}

	private prepareSelectedData() {
		this.leagueToSave.name = this.league.name;
        this.leagueToSave.seasonId = this.league.season.id;
		this.league.teams.forEach(team => {
			this.leagueToSave.teamIds.push(team.id);
		});
        console.log("league", this.leagueToSave);
	}

	private loadTeams() {
		this.teamService.getRecordNames().subscribe((teams) => {
			this.teams = teams;
		});
	}

    private loadSeasons() {
		this.seasonService.getRecordNames().subscribe((seasons) => {
			this.seasons = seasons;
		});
	}

}
