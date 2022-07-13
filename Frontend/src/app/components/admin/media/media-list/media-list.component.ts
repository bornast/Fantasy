import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pagination } from 'src/app/models/pagination';
import { MediaService } from 'src/app/services/media.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-media-list',
  templateUrl: './media-list.component.html',
  styleUrls: ['./media-list.component.scss']
})
export class MediaListComponent implements OnInit {

    mediaForList: any[];
	pagination: Pagination;
    currentPage: number = 1;

    // TODO:
    entityTypeId = 357;
    entityId = 3;

	constructor(private mediaService: MediaService, private route: ActivatedRoute, private toast: ToastService) { }

	ngOnInit() {
		this.loadMedia();
	}

	loadMedia() {
        this.mediaService.getAllMedia(this.currentPage-1).subscribe((media) => {
			this.mediaForList = media.result;
            this.pagination = media.pagination;
            this.pagination.currentPage += 1;
		});
	}

    changePage(event: any) {
        this.currentPage = event;
        this.loadMedia();
    }

}
