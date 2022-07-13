import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Match, MatchSubstitution } from 'src/app/models/match';
import { RecordName } from 'src/app/models/recordName';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.scss']
})
export class MatchDetailComponent implements OnInit {

    match: Match;
    homeTeamLineupStats: any[];
    awayTeamLineupStats: any[];
    homeTeamSubstituteStats: any[];
    awayTeamSubstituteStats: any[];

    constructor(private matchService: MatchService, private route: ActivatedRoute, private router: Router) { }

    ngOnInit(): void {
        let id = this.route.snapshot.params['id'];
		if (id) {
			this.getMatch(id);
		} else {
            this.router.navigate(['/home'])
        }
    }

    getMatch(id: any) {
		this.matchService.getMatch(id).subscribe((match) => {
			this.match = match;
            this.homeTeamLineupStats = this.getTeamLineupStats(match, match.homeTeam.lineupPlayers, match.homeTeam.substitutions);
            this.awayTeamLineupStats = this.getTeamLineupStats(match, match.awayTeam.lineupPlayers, match.awayTeam.substitutions);
            this.homeTeamSubstituteStats = this.getTeamSubstituteStats(match, match.homeTeam.substitutePlayers, match.homeTeam.substitutions);
            this.awayTeamSubstituteStats = this.getTeamSubstituteStats(match, match.awayTeam.substitutePlayers, match.awayTeam.substitutions);
		});
	}

    getTeamLineupStats(match: Match, lineupPlayers: RecordName[], substitutions: MatchSubstitution[]) {
        var result = [];

        for (const index in lineupPlayers) {
            var playerToPush = {
                playerId: lineupPlayers[index].id,
                name: lineupPlayers[index].name,
                goals: [],
                cards: [],
                substitutions: []
            };

            for (const goalIndex in match.goals) {
                if (match.goals[goalIndex].player.id == lineupPlayers[index].id) {
                    playerToPush.goals.push(match.goals[goalIndex].minute);
                }
            }

            for (const cardIndex in match.cards) {
                if (match.cards[cardIndex].player.id == lineupPlayers[index].id) {
                    playerToPush.cards.push({card: match.cards[cardIndex].card.name, minute: match.cards[cardIndex].minute});
                }
            }

            for (const subIndex in substitutions) {
                if (substitutions[subIndex].lineupPlayer.id == lineupPlayers[index].id) {
                    playerToPush.substitutions.push({withPlayer: substitutions[subIndex].substitutePlayer.name, minute: substitutions[subIndex].minute});
                }
            }

            result.push(playerToPush);
        }

        console.log("match", match);
        console.log("result lineup", result);
        return result;
    }

    getTeamSubstituteStats(match: Match, substitutePlayers: RecordName[], substitutions: MatchSubstitution[]) {
        var result = [];

        for (const index in substitutePlayers) {
            var playerToPush = {
                playerId: substitutePlayers[index].id,
                name: substitutePlayers[index].name,
                goals: [],
                cards: [],
                substitutions: []
            };

            for (const goalIndex in match.goals) {
                if (match.goals[goalIndex].player.id == substitutePlayers[index].id) {
                    playerToPush.goals.push(match.goals[goalIndex].minute);
                }
            }

            for (const cardIndex in match.cards) {
                if (match.cards[cardIndex].player.id == substitutePlayers[index].id) {
                    playerToPush.cards.push({card: match.cards[cardIndex].card.name, minute: match.cards[cardIndex].minute});
                }
            }

            for (const subIndex in substitutions) {
                if (substitutions[subIndex].substitutePlayer.id == substitutePlayers[index].id) {
                    playerToPush.substitutions.push({withPlayer: substitutions[subIndex].lineupPlayer.name, minute: substitutions[subIndex].minute});
                }
            }

            result.push(playerToPush);
        }

        console.log("result substitute", result);
        return result;
    }

    // formatHomeTeamLineupStats(match: Match) {
    //     var result = [];
    //     var lineupPlayers = match.homeTeam.lineupPlayers;
    //     for (const index in lineupPlayers) {
    //         var playerToPush = {
    //             playerId: lineupPlayers[index].id,
    //             name: lineupPlayers[index].name,
    //             goals: [],
    //             cards: []            
    //         };

    //         for (const goalIndex in match.goals) {
    //             if (match.goals[goalIndex].player.id == lineupPlayers[index].id) {
    //                 playerToPush.goals.push(match.goals[goalIndex].minute);
    //             }
    //         }

    //         for (const cardIndex in match.cards) {
    //             if (match.cards[cardIndex].player.id == lineupPlayers[index].id) {
    //                 playerToPush.cards.push({card: match.cards[cardIndex].card.name, minute: match.cards[cardIndex].minute});
    //             }
    //         }

    //         result.push(playerToPush);
    //     }
        
    //     console.log("match", match);
        

    //     console.log("result", result);
    //     return result;
    // }

}
