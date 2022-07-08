import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { RecordName } from 'src/app/models/recordName';
import { Transfer } from 'src/app/models/transfer';
import { PlayerService } from 'src/app/services/player.service';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';
import { TransferService } from 'src/app/services/transfer.service';

@Component({
  selector: 'app-transfer-editor',
  templateUrl: './transfer-editor.component.html',
  styleUrls: ['./transfer-editor.component.scss']
})
export class TransferEditorComponent implements OnInit {

	crudAction: any = CRUDACTION.create;
	transfer: Transfer;
	transferToSave: any = {
	};

    players: RecordName[];
    teams: RecordName[];

	constructor(
        private transferService: TransferService,
        private toast: ToastService, 
        private route: ActivatedRoute,
        private playerService : PlayerService,
        private teamService : TeamService,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getTransfer(id);			
		}
		else {
			this.loadData();
		}		
	}

	getTransfer(id: any) {
		this.transferService.getTransfer(id).subscribe((transfer) => {
			this.transfer = transfer;
			this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.transferService.createTransfer(this.transferToSave).subscribe((transfer) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getTransfer(transfer["id"]);
                this.router.navigate(['/admin/transfer-list']);
			});
		}
		else {
			this.transferService.updateTransfer(this.transfer.id, this.transferToSave).subscribe((transfer) => {
				this.toast.success("Successfully updated!");
				this.getTransfer(transfer["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

	loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
        this.loadPlayers();
        this.loadTeams();
	}

	private prepareSelectedData() {
        this.transferToSave.playerId = this.transfer.player.id;
        this.transferToSave.fromTeamId = this.transfer.fromTeam != null ? this.transfer.fromTeam.id : null;
        this.transferToSave.toTeamId = this.transfer.toTeam.id;
        this.transferToSave.transferDate = this.transfer.transferDate;
        console.log("transfer", this.transferToSave);
	}

	private loadPlayers() {
		this.playerService.getRecordNames().subscribe((players) => {
			this.players = players;
		});
	}

    private loadTeams() {
		this.teamService.getRecordNames().subscribe((teams) => {
			this.teams = teams;
            this.teams.push({id: null, name:""});
		});
	}

}
