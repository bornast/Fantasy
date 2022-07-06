import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';
import { Season } from '../models/season';

@Injectable({
  providedIn: 'root'
})
export class SeasonService {

    baseUrl = environment.apiUrl;

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "seasons/record-names");
	}

    getSeasonsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Season[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Season[]>>(this.baseUrl + "seasons/", { params });
	}

    getSeason(id) {
		return this.http.get<Season>(this.baseUrl + "seasons/" + id);
	}

    createSeason(seasonToCreate) {
		return this.http.post(this.baseUrl + "seasons/", seasonToCreate);
	}

    updateSeasons(id, seasonToUpdate) {
		return this.http.put(this.baseUrl + "seasons/" + id, seasonToUpdate);
	}

	deleteSeasons(id: any) {
		return this.http.delete(this.baseUrl + "seasons/" + id);
	}	

}
