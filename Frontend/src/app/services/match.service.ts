import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Match } from '../models/match';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

    baseUrl = environment.apiUrl + "matches/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getMatchesByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Match[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('teamName', name);
        
        return this.http.get<PaginatedResult<Match[]>>(this.baseUrl, { params });
	}

    getMatch(id) {
		return this.http.get<Match>(this.baseUrl + id);
	}

    createMatch(objecToCreate) {
        objecToCreate.matchDate = objecToCreate.matchDate + ":00Z";
        console.log("objecttocreate", objecToCreate);
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateMatch(id, objectToUpdate) {
        objectToUpdate.matchDate = objectToUpdate.matchDate + ":00Z";
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deleteMatch(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
