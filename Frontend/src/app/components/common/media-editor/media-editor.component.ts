import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { MEDIATYPE } from 'src/app/constants/mediaTypeConstant';
import { Media } from 'src/app/models/media';
import { MediaService } from 'src/app/services/media.service';
import { ToastService } from 'src/app/services/toast.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'media-editor',
  templateUrl: './media-editor.component.html',
  styleUrls: ['./media-editor.component.css']
})
export class MediaEditorComponent implements OnInit {

	@Input() entityTypeId: any;
	@Input() entityId: any;
	@Input() media: Media[];
    @Input() isMediaModeration: boolean = false;
    @Input() isMemoryUpload: boolean = false;
	@Output() getMemberMediaChange = new EventEmitter<string>();
	uploader: FileUploader;
	hasBaseDropZoneOver = false;
	baseUrl = environment.apiUrl;
	currentMain: Media;
    imageMediaType = MEDIATYPE.image;
    videoMediaType = MEDIATYPE.video;

	constructor(private toast: ToastService, private mediaService: MediaService) { }

	ngOnInit() {
        if (this.isMediaModeration == false)
		    this.initializeUploader();
	}

	fileOverBase(e: any): void {
		this.hasBaseDropZoneOver = e;
	}

	initializeUploader() {
		this.uploader = new FileUploader({
			url: this.baseUrl + 'media',
            authToken: 'Bearer ' + localStorage.getItem('fantasy-token'),
			isHTML5: true,
			allowedFileType: ['image', 'video'],
			removeAfterUpload: true,
			autoUpload: false,
			maxFileSize: 10 * 1024 * 1024,
			additionalParameter: {
				entityTypeId: this.entityTypeId,
				entityId: this.entityId,
			}
		});

		this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };

		this.uploader.onSuccessItem = (item, response, status, headers) => {
			if (response) {
				const res: Media = JSON.parse(response);
				const media = {
					id: res.id,
					url: res.url,
					isMain: res.isMain,
                    mediaTypeId: res.mediaTypeId,
                    approved: res.approved
				};
				this.media.push(media);
			}
		};
	}

	setMainMedia(media: Media) {
        let mediaData = {
            entityId: this.entityId,
            entityTypeId: this.entityTypeId
        };
		this.mediaService.setMainMedia(media.id, mediaData).subscribe(() => {
			this.currentMain = this.media.filter(p => p.isMain === true)[0]; // find the previous main media and set it to false
			this.currentMain.isMain = false;
			media.isMain = true; // set the selected main media to true
		}, () => {
			this.toast.error("Failed to set the media as main!");
		});		
	}

	deleteMedia(id: number) {		
		this.mediaService.deleteMedia(id).subscribe(() => {
			this.media.splice(this.media.findIndex(p => p.id === id), 1);
			this.toast.success('Media has been deleted');
		}, () => {
			this.toast.error('Failed to delete the media');
		});
	}

    approveMedia(media: Media) {
        this.mediaService.approveMedia(media.id).subscribe(() => {
			this.toast.success('Media has been approved');
            media.approved = true; // set the selected main media to true
		}, () => {
			this.toast.error('Failed to approve the media');
		});
    }

    disapproveMedia(media: Media) {
        this.mediaService.disapproveMedia(media.id).subscribe(() => {
			this.toast.success('Media has been disapproved');
            media.approved = false; // set the selected main media to true
		}, () => {
			this.toast.error('Failed to disapprove the media');
		});
    }

}
