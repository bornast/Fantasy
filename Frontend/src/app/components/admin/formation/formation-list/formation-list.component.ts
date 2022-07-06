import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { FormationService } from 'src/app/services/formation.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-formation-list',
  templateUrl: './formation-list.component.html',
  styleUrls: ['./formation-list.component.scss']
})
export class FormationListComponent implements OnInit {

    formationsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private formationService: FormationService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadFormations();
	}

	loadFormations() {
        this.formationService.getFormationsByFilter(this.searchTxt, this.currentPage-1).subscribe((formations) => {
			this.formationsForList = formations.result;
            this.pagination = formations.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.formationService.deleteFormation(id).subscribe(() => {
				this.loadFormations();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadFormations();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadFormations();
    }

}
