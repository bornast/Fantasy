import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { ENTITYTYPE } from 'src/app/constants/entityTypeConstant';
import { Player } from 'src/app/models/player';
import { RecordName } from 'src/app/models/recordName';
import { PlayerService } from 'src/app/services/player.service';
import { PositionService } from 'src/app/services/position.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-player-editor',
  templateUrl: './player-editor.component.html',
  styleUrls: ['./player-editor.component.scss']
})
export class PlayerEditorComponent implements OnInit {

    entityTypeId: any = ENTITYTYPE.player
	crudAction: any = CRUDACTION.create;
	player: Player;
	playerToSave: any = {
        media: []
	};

    positions: RecordName[];

	constructor(
        private playerService: PlayerService,
        private toast: ToastService, 
        private route: ActivatedRoute,
        private positionService : PositionService,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getPlayer(id);			
		}
		else {
			this.loadData();
		}		
	}

	getPlayer(id: any) {
		this.playerService.getPlayer(id).subscribe((player) => {
			this.player = player;
			this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.playerService.createPlayer(this.playerToSave).subscribe((player) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getPlayer(player["id"]);
                this.router.navigate(['/admin/player-list']);
			});
		}
		else {
			this.playerService.updatePlayer(this.player.id, this.playerToSave).subscribe((player) => {
				this.toast.success("Successfully updated!");
				this.getPlayer(player["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

	loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
        this.loadPositions();
	}

	private prepareSelectedData() {
		this.playerToSave.name = this.player.name;
        this.playerToSave.positionId = this.player.position.id;
        this.playerToSave.dateOfBirth = this.player.dateOfBirth;
        this.playerToSave.media = this.player.media.media;
        console.log("player", this.playerToSave);
	}

	private loadPositions() {
		this.positionService.getRecordNames().subscribe((positions) => {
			this.positions = positions;
		});
	}

}
