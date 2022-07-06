import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CRUDACTION } from 'src/app/constants/crudActionConstant';
import { RecordName } from 'src/app/models/recordName';
import { Season } from 'src/app/models/season';
import { SeasonService } from 'src/app/services/season.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-season-editor',
  templateUrl: './season-editor.component.html',
  styleUrls: ['./season-editor.component.scss']
})
export class SeasonEditorComponent implements OnInit {

    crudAction: any = CRUDACTION.create;
	season: Season;
	seasonToSave: any = {
	};

	constructor(
        private seasonService: SeasonService, 
        private toast: ToastService, 
        private route: ActivatedRoute,
        private router: Router) { }

	ngOnInit() {
		let id = this.route.snapshot.params['id'];
		if (id) {
			this.crudAction = CRUDACTION.update;
			this.getSeason(id);			
		}
	}

	getSeason(id: any) {
		this.seasonService.getSeason(id).subscribe((season) => {
			this.season = season;
            this.loadData();
		});
	}

	save() {
		if (this.crudAction == CRUDACTION.create) {
			this.seasonService.createSeason(this.seasonToSave).subscribe((season) => {
				this.toast.success("Successfully created!");
				this.crudAction = CRUDACTION.update;
				this.getSeason(season["id"]);
                this.router.navigate(['/admin/season-list']);
			});
		}
		else {
			this.seasonService.updateSeasons(this.season.id, this.seasonToSave).subscribe((season) => {
				this.toast.success("Successfully updated!");
				this.getSeason(season["id"]);
			}, (error) => {
				this.toast.error("Failed to update!");
			});
		}		
	}

    loadData() {
		if (this.crudAction == CRUDACTION.update) 
			this.prepareSelectedData();
	}

	private prepareSelectedData() {
		this.seasonToSave.name = this.season.name;
        this.seasonToSave.startDate = this.season.startDate;
        this.seasonToSave.endDate = this.season.endDate;
	}

}
