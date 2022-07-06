import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { PositionService } from 'src/app/services/position.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-position-list',
  templateUrl: './position-list.component.html',
  styleUrls: ['./position-list.component.scss']
})
export class PositionListComponent implements OnInit {

    positionsForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private positionService: PositionService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadPositions();
	}

	loadPositions() {
        this.positionService.getPositionsByFilter(this.searchTxt, this.currentPage-1).subscribe((positions) => {
			this.positionsForList = positions.result;
            this.pagination = positions.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.positionService.deletePositions(id).subscribe(() => {
				this.loadPositions();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadPositions();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadPositions();
    }

}
