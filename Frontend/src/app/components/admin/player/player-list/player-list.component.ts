import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { Player } from 'src/app/models/player';
import { PlayerService } from 'src/app/services/player.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.scss']
})
export class PlayerListComponent implements OnInit {

    playersForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private playerService: PlayerService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadPlayers();
	}

	loadPlayers() {
        this.playerService.getPlayersByFilter(this.searchTxt, this.currentPage-1).subscribe((players) => {
			this.playersForList = players.result;
            this.pagination = players.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.playerService.deletePlayer(id).subscribe(() => {
				this.loadPlayers();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadPlayers();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadPlayers();
    }

}
