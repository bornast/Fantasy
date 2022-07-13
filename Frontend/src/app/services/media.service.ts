import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Media } from '../models/media';
import { PaginatedResult } from '../models/pagination';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

    baseUrl = environment.apiUrl;

	constructor(private http: HttpClient) { }

    getApprovedMediaByFilter(matchId?: number, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Media[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (matchId != null)
			params = params.append('matchId', matchId);
        
        return this.http.get<PaginatedResult<Media[]>>(this.baseUrl + "media/approved", { params });
	}

    getAllMedia(pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Media[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
        return this.http.get<PaginatedResult<Media[]>>(this.baseUrl + "media", { params });
	}
    
    getPersonalMedia(id) {
		return this.http.get<Media>(this.baseUrl + "media/personal");
	}

	setMainMedia(mediaId: any, mediaData) {
		return this.http.post(this.baseUrl + "media/"+ mediaId + "/setMain", mediaData);
	}

    approveMedia(mediaId: any) {
		return this.http.post(this.baseUrl + "media/"+ mediaId + "/approve", {});
	}

    disapproveMedia(mediaId: any) {
		return this.http.post(this.baseUrl + "media/"+ mediaId + "/disapprove", {});
	}

	deleteMedia(mediaId: any) {
		return this.http.delete(this.baseUrl + "media/" + mediaId);
	}

}
