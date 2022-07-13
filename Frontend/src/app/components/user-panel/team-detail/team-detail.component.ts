import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { Pagination } from 'src/app/models/pagination';
import { Player } from 'src/app/models/player';
import { Team } from 'src/app/models/team';
import { PlayerService } from 'src/app/services/player.service';
import { TeamService } from 'src/app/services/team.service';

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

    constructor(private temaService: TeamService, private route: ActivatedRoute, private router: Router) { }

    ngOnInit(): void {
        let id = this.route.snapshot.params['id'];
		if (id) {
			this.getTeam(id);			
		} else {
            this.router.navigate(['/home'])
        }
    }

    getTeam(id: any) {
		this.temaService.getTeam(id).subscribe((team) => {
			this.team = team;
            this.loadData();    
		});
	}

    loadData() {
        this.loadResults();
        this.loadTransfers();
        this.loadPlayers();
    }

    loadResults() {
        this.temaService.getTeamResultsByFilter(this.team.id, this.currentResultPage-1).subscribe((results) => {
			this.results = results.result;
            this.resultPagination = results.pagination;
            this.resultPagination.currentPage += 1;
		});
    }

    loadTransfers() {
        this.temaService.getTeamTransfersByFilter(this.team.id, this.currentTransferPage-1).subscribe((transfers) => {
			this.transfers = transfers.result;
            this.transferPagination = transfers.pagination;
            this.transferPagination.currentPage += 1;
		});
    }

    loadPlayers() {
        this.temaService.getTeamPlayersStatistics(this.team.id).subscribe((players) => {
			this.players = players;
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

}
