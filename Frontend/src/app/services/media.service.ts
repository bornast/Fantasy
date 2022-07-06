import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

    baseUrl = environment.apiUrl;

	constructor(private http: HttpClient) { }

	setMainMedia(mediaId: any, mediaData) {
		return this.http.post(this.baseUrl + "media/"+ mediaId + "/setMain", mediaData);
	}

	deleteMedia(mediaId: any) {
		return this.http.delete(this.baseUrl + "media/" + mediaId);
	}

}
