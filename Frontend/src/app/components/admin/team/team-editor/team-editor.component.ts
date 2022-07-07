import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { ENTITYTYPE } from 'src/app/constants/entityTypeConstant';
import { RecordName } from 'src/app/models/recordName';
import { Team } from 'src/app/models/team';
import { CoachService } from 'src/app/services/coach.service';
import { PresidentService } from 'src/app/services/president.service';
import { StadiumService } from 'src/app/services/stadium.service';
import { TeamService } from 'src/app/services/team.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-team-editor',
  templateUrl: './team-editor.component.html',
  styleUrls: ['./team-editor.component.scss']
})
export class TeamEditorComponent implements OnInit {

    entityTypeId: any = ENTITYTYPE.team
	crudAction: any = CRUDACTION.create;
	team: Team;
	teamToSave: any = {
        media: []
	};

    presidents: RecordName[];
    coaches: RecordName[];
    stadiums: RecordName[];

	constructor(
        private teamService: TeamService,
        private toast: ToastService, 
        private route: ActivatedRoute,
        private presidentService : PresidentService,
        private coachService : CoachService,
        private stadiumService : StadiumService,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getTeam(id);			
		}
		else {
			this.loadData();
		}		
	}

	getTeam(id: any) {
		this.teamService.getTeam(id).subscribe((team) => {
			this.team = team;
			this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.teamService.createTeam(this.teamToSave).subscribe((team) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getTeam(team["id"]);
                this.router.navigate(['/admin/team-list']);
			});
		}
		else {
			this.teamService.updateTeam(this.team.id, this.teamToSave).subscribe((team) => {
				this.toast.success("Successfully updated!");
				this.getTeam(team["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

	loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
        this.loadPresidents();
        this.loadCoaches();
        this.loadStadiums();
	}

	private prepareSelectedData() {
		this.teamToSave.name = this.team.name;
        this.teamToSave.presidentId = this.team.president.id;
        this.teamToSave.coachId = this.team.coach.id;
        this.teamToSave.stadiumId = this.team.stadium.id;
        this.teamToSave.dateOfEstablishment = this.team.dateOfEstablishment;
        this.teamToSave.media = this.team.media.media;
        console.log("team", this.teamToSave);
	}

	private loadPresidents() {
		this.presidentService.getRecordNames().subscribe((presidents) => {
			this.presidents = presidents;
		});
	}

    private loadCoaches() {
		this.coachService.getRecordNames().subscribe((coaches) => {
			this.coaches = coaches;
		});
	}

    private loadStadiums() {
		this.stadiumService.getRecordNames().subscribe((stadiums) => {
			this.stadiums = stadiums;
		});
	}

}
