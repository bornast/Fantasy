import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { League } from 'src/app/models/league';
import { Pagination } from 'src/app/models/pagination';
import { Player } from 'src/app/models/player';
import { Team } from 'src/app/models/team';
import { PlayerService } from 'src/app/services/player.service';
import { TeamService } from 'src/app/services/team.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ChatService } from 'src/app/services/chat.service';
import { Chat } from 'src/app/models/chat';

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {

    team: Team;
    players: Player[];

    results: any[];
    resultPagination: Pagination;
    currentResultPage: number = 1;
    
    transfers: any[];
    transferPagination: Pagination;
    currentTransferPage: number = 1;

    leagues: League[];
    table: any[];
    chat: Chat;

    greetings: string[] = [];
    disabled = true;
    newmessage: string;
    private stompClient = null;
    currentUser = localStorage.getItem('fantasy-userId');

    constructor(private teamService: TeamService, private chatService: ChatService, private route: ActivatedRoute, private router: Router) { }

    ngOnInit(): void {        
        let id = this.route.snapshot.params['id'];
		if (id) {
			this.getTeam(id);			
		} else {
            this.router.navigate(['/home'])
        }
    }

    public ngOnDestroy() {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
      }

    getTeam(id: any) {
		this.teamService.getTeam(id).subscribe((team) => {
			this.team = team;
            this.connect();
            this.loadData();
		});
	}

    loadData() {
        this.loadResults();
        this.loadTransfers();
        this.loadPlayers();
        this.loadLeagues();        
    }

    loadResults() {
        this.teamService.getTeamResultsByFilter(this.team.id, this.currentResultPage-1).subscribe((results) => {
			this.results = results.result;
            this.resultPagination = results.pagination;
            this.resultPagination.currentPage += 1;
		});
    }

    loadTransfers() {
        this.teamService.getTeamTransfersByFilter(this.team.id, this.currentTransferPage-1).subscribe((transfers) => {
			this.transfers = transfers.result;
            this.transferPagination = transfers.pagination;
            this.transferPagination.currentPage += 1;
		});
    }

    loadPlayers() {
        this.teamService.getTeamPlayersStatistics(this.team.id).subscribe((players) => {
			this.players = players;
		});
    }

    loadLeagues() {
        this.teamService.getTeamLeagues(this.team.id).subscribe((leagues) => {
			this.leagues = leagues;
            if (this.leagues != null && this.leagues.length > 0) {
                this.loadTable(this.leagues[0].id);
                console.log("leagues", this.leagues);
            }            
		});
    }

    loadTable(leagueId) {
        this.teamService.getTeamTable(leagueId).subscribe((table) => {
			this.table = table;
            console.log("table", this.table);
		});
    }

    loadChat() {
        this.chatService.getChat(this.team.id).subscribe((chat) => {
			this.chat = chat;
            console.log("chat", this.chat);
		});
    }

    changeResultPage(event: any) {
        this.currentResultPage = event;
        this.loadResults();
    }
    
    changeTransferPage(event: any) {
        this.currentTransferPage = event;
        this.loadTransfers();
    }

    singleListingsBox = [
        {
            mainImg: [
                {
                    img: 'assets/img/listings/listings7.jpg'
                }
            ],
            categoryLink: 'single-listings',
            category: 'Restaurant',
            bookmarkLink: 'single-listings',
            location: 'Francisco, USA',
            title: 'The Mad Made Grill',
            price: 'Start From: $121',
            detailsLink: 'single-listings',
            authorImg: 'assets/img/user1.jpg',
            openORclose: 'Open Now',
            extraClass: 'status-open',
            authorName: 'James',
            rating: [
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                }
            ],
            ratingCount: '18'
        },
        {
            mainImg: [
                {
                    img: 'assets/img/listings/listings4.jpg'
                },
                {
                    img: 'assets/img/listings/listings2.jpg'
                }
            ],
            categoryLink: 'single-listings',
            category: 'Hotel',
            bookmarkLink: 'single-listings',
            location: 'Los Angeles, USA',
            title: 'The Beverly Hills Hotel',
            price: 'Start From: $200',
            detailsLink: 'single-listings',
            authorImg: 'assets/img/user2.jpg',
            openORclose: 'Open Now',
            extraClass: 'status-open',
            authorName: 'Sarah',
            rating: [
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bxs-star'
                },
                {
                    icon: 'bx bx-star'
                }
            ],
            ratingCount: '10'
        }
    ]

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
    singleImageBox = [
        {
            img: 'assets/img/gallery/gallery1.jpg'
        },
        {
            img: 'assets/img/gallery/gallery2.jpg'
        },
        {
            img: 'assets/img/gallery/gallery3.jpg'
        },
        {
            img: 'assets/img/gallery/gallery4.jpg'
        },
        {
            img: 'assets/img/gallery/gallery5.jpg'
        }
    ]

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
    }

    setConnected(connected: boolean) {
        this.disabled = !connected;
    }

    connect() {
        const socket = new SockJS('http://localhost:8080/chat');
        this.stompClient = Stomp.over(socket);
        const _this = this;
        this.stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            _this.loadChat();
            _this.stompClient.subscribe('/start/initial/' + _this.team.id, function(msg) {
                console.log("msg", JSON.parse(msg.body));
                console.log("before", _this.chat);
                _this.chat.messages.push(JSON.parse(msg.body));
                console.log("after", _this.chat);
            });
        });
    }

    sendMessage() {
        this.stompClient.send('/current/resume/' + this.team.id,
            {Authorization: localStorage.getItem("fantasy-token")}, 
            this.newmessage
        );
        this.newmessage = "";
      }

}
