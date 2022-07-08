import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { ToastService } from 'src/app/services/toast.service';
import { TransferService } from 'src/app/services/transfer.service';

@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.scss']
})
export class TransferListComponent implements OnInit {

    transfersForList: any[];
	pagination: Pagination;
	searchTxt: string;
    currentPage: number = 1;

	constructor(private transferService: TransferService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadTransfers();
	}

	loadTransfers() {
        this.transferService.getTransfersByFilter(this.searchTxt, this.currentPage-1).subscribe((transfers) => {
			this.transfersForList = transfers.result;
            this.pagination = transfers.pagination;
            this.pagination.currentPage += 1;
		});
	}

	delete(id: any) {
		if (confirm("Are you sure you want to delete this record?")) {
			this.transferService.deleteTransfer(id).subscribe(() => {
				this.loadTransfers();
				this.toast.success("Successfully delete!");
			}, () => {
				this.toast.error("Failed to delete!");
			});
		}
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadTransfers();
    }

    search(event: any) {
        this.searchTxt = event;
        this.loadTransfers();
    }

}
