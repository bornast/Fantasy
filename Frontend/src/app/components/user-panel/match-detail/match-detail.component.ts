import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { ENTITYTYPE } from 'src/app/constants/entityTypeConstant';
import { MEDIATYPE } from 'src/app/constants/mediaTypeConstant';
import { Match, MatchPlayer, MatchSubstitution } from 'src/app/models/match';
import { Media } from 'src/app/models/media';
import { Pagination } from 'src/app/models/pagination';
import { RecordName } from 'src/app/models/recordName';
import { MatchService } from 'src/app/services/match.service';
import { MediaService } from 'src/app/services/media.service';
import { RateService } from 'src/app/services/rate.service';
import { ToastService } from 'src/app/services/toast.service';

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

    mediaForList: Media[];
    mediaPagination: Pagination;
    mediaCurrentPage: number = 1;

    myMedia: Media[];
    entityTypeId = ENTITYTYPE.memory;

    currentRate: number;
    currentRateId: null;
    currentRatePlayerId: number;
    currentRatePlayerName: string;

    constructor(private matchService: MatchService, 
        private mediaService: MediaService, 
        private rateService: RateService, 
        private route: ActivatedRoute, 
        private router: Router,
        private toast: ToastService) { }

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
            this.loadMemories();
            this.loadMyMemories();
		});
	}

    getTeamLineupStats(match: Match, lineupPlayers: MatchPlayer[], substitutions: MatchSubstitution[]) {
        var result = [];

        for (const index in lineupPlayers) {
            var playerToPush = {
                playerId: lineupPlayers[index].id,
                name: lineupPlayers[index].name,
                goals: [],
                cards: [],
                substitutions: [],
                rate: lineupPlayers[index].rate
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

    getTeamSubstituteStats(match: Match, substitutePlayers: MatchPlayer[], substitutions: MatchSubstitution[]) {
        var result = [];

        for (const index in substitutePlayers) {
            var playerToPush = {
                playerId: substitutePlayers[index].id,
                name: substitutePlayers[index].name,
                goals: [],
                cards: [],
                substitutions: [],
                rate: substitutePlayers[index].rate
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

    loadMemories() {
        this.mediaService.getApprovedMediaByFilter(this.match.id, this.mediaCurrentPage-1).subscribe((media) => {
			this.mediaForList = media.result;
            this.mediaPagination = media.pagination;
            this.mediaPagination.currentPage += 1;
            console.log("mediaforlist", this.mediaForList);
		});
	}

    loadMyMemories() {
        this.mediaService.getPersonalMedia(this.match.id).subscribe((media) => {
			this.myMedia = media;
            console.log("myMedia", this.myMedia);
		});
	}

    galleryOptions: OwlOptions = {
		loop: true,
		nav: true,
		dots: false,
		autoplayHoverPause: true,
		autoplay: true,
		margin: 30,
        navText: [
            "<i class='flaticon-left-chevron'></i>",
            "<i class='flaticon-right-chevron'></i>"
        ],
		responsive: {
			0: {
				items: 1,
			},
			576: {
				items: 2,
			},
			768: {
				items: 2,
			},
			992: {
				items: 2,
			}
		}
    }

    getImageMedia() {
        return this.mediaForList.filter(x => x.mediaTypeId != MEDIATYPE.video);
    }

    getVideoMedia() {
        return this.mediaForList.filter(x => x.mediaTypeId == MEDIATYPE.video);
    }

    ratePlayer(player: any) {
        this.currentRate = null;
        this.currentRateId = null;
        this.currentRatePlayerId = null;
        this.currentRatePlayerName = null;

        this.rateService.getRate(this.match.id, player.playerId).subscribe((rate) => {
            if (rate && rate.id > 0) {
                this.currentRateId = rate.id;
                this.currentRate = rate.rate;
            }
            this.currentRatePlayerName = player.name;
            this.currentRatePlayerId = player.playerId;
		});
    }

    submitRate() {
        if (this.currentRateId) {
            let objecToUpdate = {
                playerId: this.currentRatePlayerId,
                matchId: this.match.id,
                rate: this.currentRate
            };
            this.rateService.updateRate(this.currentRateId, objecToUpdate).subscribe(() => {
				this.toast.success("Successfully rated!");
				this.currentRate = null;
                this.currentRateId = null;
                this.currentRatePlayerId = null;
                this.currentRatePlayerName = null;
                this.getMatch(this.match.id);
			});
        } else {
            let objecToCreate = {
                playerId: this.currentRatePlayerId,
                matchId: this.match.id,
                rate: this.currentRate
            };
            this.rateService.createRate(objecToCreate).subscribe((match) => {
				this.toast.success("Successfully rated!");
				this.currentRate = null;
                this.currentRateId = null;
                this.currentRatePlayerId = null;
                this.currentRatePlayerName = null;
                this.getMatch(this.match.id);
			});
        }
    }

    removeRate() {
        if (this.currentRateId) {
            this.rateService.deleteRate(this.currentRateId).subscribe(() => {
				this.toast.success("Rate successfully removed!");
				this.currentRate = null;
                this.currentRateId = null;
                this.currentRatePlayerId = null;
                this.currentRatePlayerName = null;
                this.getMatch(this.match.id);
			});
        }
    }

}
