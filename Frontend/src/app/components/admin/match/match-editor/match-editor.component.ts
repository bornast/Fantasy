import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { Match, MatchSubstitution } from 'src/app/models/match';
import { RecordName } from 'src/app/models/recordName';
import { FormationService } from 'src/app/services/formation.service';
import { LeagueService } from 'src/app/services/league.service';
import { MatchService } from 'src/app/services/match.service';
import { RefereeService } from 'src/app/services/referee.service';
import { StadiumService } from 'src/app/services/stadium.service';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-match-editor',
  templateUrl: './match-editor.component.html',
  styleUrls: ['./match-editor.component.scss']
})
export class MatchEditorComponent implements OnInit {

	crudAction: any = CRUDACTION.create;
	match: Match;
	matchToSave: any = {
        homeTeam: {

        },
        awayTeam: {

        }
	};

    teams: RecordName[];
    formations: RecordName[];
    stadiums: RecordName[];
    referees: RecordName[];
    leagues: RecordName[];
    homeTeamPlayers: RecordName[];
    awayTeamPlayers: RecordName[];

	constructor(
        private matchService: MatchService,
        private toast: ToastService, 
        private route: ActivatedRoute,
        private teamService: TeamService,
        private formationService: FormationService,
        private leagueService: LeagueService,
        private refereeService: RefereeService,
        private stadiumService : StadiumService,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getMatch(id);			
		}
		else {
			this.loadData();
		}		
	}

	getMatch(id: any) {
		this.matchService.getMatch(id).subscribe((match) => {
			this.match = match;
			this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.matchService.createMatch(this.matchToSave).subscribe((match) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getMatch(match["id"]);
                this.router.navigate(['/admin/match-list']);
			});
		}
		else {
			this.matchService.updateMatch(this.match.id, this.matchToSave).subscribe((match) => {
				this.toast.success("Successfully updated!");
				this.getMatch(match["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

	loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
            if (this.match.homeTeam != null && this.match.homeTeam.team != null)
                this.loadHomeTeamPlayers(this.match.homeTeam.team.id);
            if (this.match.awayTeam != null && this.match.awayTeam.team != null)
                this.loadAwayTeamPlayers(this.match.awayTeam.team.id);

        this.loadTeams();
        this.loadFormations();
        this.loadLeagues();
        this.loadReferees();
        this.loadStadiums();
	}

	private prepareSelectedData() {
        this.matchToSave.homeTeam = {
            teamId: this.match.homeTeam != null ? this.match.homeTeam.team.id : null,
            formationId: this.match.homeTeam != null ? this.match.homeTeam.formation.id : null,
            lineupPlayerIds: this.match.homeTeam != null ? this.match.homeTeam.lineupPlayers.map(x => x.id) : [],
            substitutePlayerIds: this.match.homeTeam != null ? this.match.homeTeam.substitutePlayers.map(x => x.id) : [],
            substitutions: this.match.homeTeam != null ? this.extractSubstitutions(this.match.homeTeam.substitutions) : []
        };

        this.matchToSave.awayTeam = {
            teamId: this.match.awayTeam != null ? this.match.awayTeam.team.id : null,
            formationId: this.match.awayTeam != null ? this.match.awayTeam.formation.id : null,
            lineupPlayerIds: this.match.awayTeam != null ? this.match.awayTeam.lineupPlayers.map(x => x.id) : [],
            substitutePlayerIds: this.match.awayTeam != null ? this.match.awayTeam.substitutePlayers.map(x => x.id) : [],
            substitutions: this.match.awayTeam != null ? this.extractSubstitutions(this.match.awayTeam.substitutions) : []
        };

        this.matchToSave.goals = []
        if (this.match.goals != null) {
            for (var i in this.match.goals) {
                this.matchToSave.goals.push({playerId: this.match.goals[i].player.id, minute: this.match.goals[i].minute});
            }            
        }

        this.matchToSave.cards = []
        if (this.match.cards != null) {
            for (var i in this.match.cards) {
                this.matchToSave.cards.push({playerId: this.match.cards[i].player.id, cardId: this.match.cards[i].card.id, minute: this.match.cards[i].minute});
            }            
        }        

		this.matchToSave.name = this.match.name;
        this.matchToSave.leagueId = this.match.league.id;
        this.matchToSave.refereeId = this.match.referee.id;
        this.matchToSave.stadiumId = this.match.stadium.id;
        this.matchToSave.matchDate = this.match.matchDate;
        this.matchToSave.spectatorCount = this.match.spectatorCount;
        console.log("matchToSave", this.matchToSave);
        console.log("match", this.match);
	}

    extractSubstitutions(substitutions: MatchSubstitution[]) {
        var result = [];
        for (var i in substitutions) {
            var obj = {
                lineupPlayerId: substitutions[i].lineupPlayer.id,
                substitutePlayerId: substitutions[i].substitutePlayer.id,
                minute: substitutions[i].minute,
            }
            result.push(obj);
        }
        return result;
    }

    private loadTeams() {
		this.teamService.getRecordNames().subscribe((teams) => {
			this.teams = teams;
		});
	}

    private loadFormations() {
		this.formationService.getRecordNames().subscribe((formations) => {
			this.formations = formations;
		});
	}

    private loadLeagues() {
		this.leagueService.getRecordNames().subscribe((leagues) => {
			this.leagues = leagues;
		});
	}

    private loadReferees() {
		this.refereeService.getRecordNames().subscribe((referees) => {
			this.referees = referees;
		});
	}

    private loadStadiums() {
		this.stadiumService.getRecordNames().subscribe((stadiums) => {
			this.stadiums = stadiums;
		});
	}

    private loadHomeTeamPlayers(id) {
		this.teamService.getTeamPlayers(id).subscribe((players) => {
			this.homeTeamPlayers = players;
		});
	}

    private loadAwayTeamPlayers(id) {
		this.teamService.getTeamPlayers(id).subscribe((players) => {
			this.awayTeamPlayers = players;
		});
	}

    private onHomeTeamChange(newValue) {
        this.loadHomeTeamPlayers(newValue);
    }

    private onAwayTeamChange(newValue) {
        this.loadAwayTeamPlayers(newValue);
    }

    addEmptyHomeLineupPlayer() {
		this.matchToSave.homeTeam.lineupPlayerIds.push(null);
	}

	removeHomeLineupPlayer(index: number) {
		this.matchToSave.homeTeam.lineupPlayerIds.splice(index, 1);
	}

}
