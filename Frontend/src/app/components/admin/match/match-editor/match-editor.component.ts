import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { Match, MatchSubstitution } from 'src/app/models/match';
import { RecordName } from 'src/app/models/recordName';
import { CardService } from 'src/app/services/card.service';
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

        },
        goals: [{}],
        cards: [{}]
	};

    teams: RecordName[];
    formations: RecordName[];
    stadiums: RecordName[];
    referees: RecordName[];
    leagues: RecordName[];
    homeTeamPlayers: RecordName[];
    awayTeamPlayers: RecordName[];
    allPlayers: RecordName[];
    cards: RecordName[];

	constructor(
        private matchService: MatchService,
        private toast: ToastService, 
        private route: ActivatedRoute,
        private teamService: TeamService,
        private formationService: FormationService,
        private leagueService: LeagueService,
        private refereeService: RefereeService,
        private stadiumService : StadiumService,
        private cardService: CardService,
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
        console.log("aaa", this.matchToSave);
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
		if (this.crudAction == CRUDACTION.update) {
            this.prepareSelectedData();
            if (this.match.homeTeam != null && this.match.homeTeam.team != null)
                this.loadHomeTeamPlayers(this.match.homeTeam.team.id);
            if (this.match.awayTeam != null && this.match.awayTeam.team != null)
                this.loadAwayTeamPlayers(this.match.awayTeam.team.id);
        }
			

        this.loadTeams();
        this.loadFormations();
        this.loadLeagues();
        this.loadReferees();
        this.loadStadiums();
        this.loadCards();
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

    private loadCards() {
		this.cardService.getRecordNames().subscribe((cards) => {
			this.cards = cards;
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
        this.matchToSave.homeTeam.lineupPlayerIds = [null];
        this.matchToSave.homeTeam.substitutePlayerIds = [null];
        this.matchToSave.homeTeam.substitutions = [{}];
        this.loadHomeTeamPlayers(newValue);
        console.log("home team change", this.matchToSave.homeTeam);
    }

    private onAwayTeamChange(newValue) {
        this.matchToSave.awayTeam.lineupPlayerIds = [null];
        this.matchToSave.awayTeam.substitutePlayerIds = [null];
        this.matchToSave.awayTeam.substitutions = [{}];
        this.loadAwayTeamPlayers(newValue);
        console.log("away team change", this.matchToSave.awayTeam);
    }

    addEmptyHomeLineupPlayer() {
		this.matchToSave.homeTeam.lineupPlayerIds.push(null);
	}

	removeHomeLineupPlayer(index: number) {
		this.matchToSave.homeTeam.lineupPlayerIds.splice(index, 1);
	}

    addEmptyHomeSubstitutePlayer() {
		this.matchToSave.homeTeam.substitutePlayerIds.push(null);
	}

	removeHomeSubstitutePlayer(index: number) {
		this.matchToSave.homeTeam.substitutePlayerIds.splice(index, 1);
	}

    addEmptyHomeSubstitution() {
		this.matchToSave.homeTeam.substitutions.push({lineupPlayerId: null, substitutePlayer: null, minute: null});
	}

	removeHomeSubstitution(index: number) {
		this.matchToSave.homeTeam.substitutions.splice(index, 1);
	}

    addEmptyAwayLineupPlayer() {
		this.matchToSave.awayTeam.lineupPlayerIds.push(null);
	}

	removeAwayLineupPlayer(index: number) {
		this.matchToSave.awayTeam.lineupPlayerIds.splice(index, 1);
	}

    addEmptyAwaySubstitutePlayer() {
		this.matchToSave.awayTeam.substitutePlayerIds.push(null);
	}

	removeAwaySubstitutePlayer(index: number) {
		this.matchToSave.awayTeam.substitutePlayerIds.splice(index, 1);
	}

    addEmptyAwaySubstitution() {
		this.matchToSave.awayTeam.substitutions.push({lineupPlayerId: null, substitutePlayer: null, minute: null});
	}

	removeAwaySubstitution(index: number) {
		this.matchToSave.awayTeam.substitutions.splice(index, 1);
	}

    getAllPlayers() {
        return [...new Set([...this.homeTeamPlayers ,...this.awayTeamPlayers])]; //   => remove duplication
    }

    addEmptyGoal() {
		this.matchToSave.goals.push({playerId: null, minute: null});
	}

	removeGoal(index: number) {
		this.matchToSave.goals.splice(index, 1);
	}

    addEmptyCard() {
		this.matchToSave.cards.push({playerId: null, cardId: null, minute: null});
	}

	removeCard(index: number) {
		this.matchToSave.cards.splice(index, 1);
	}

}
