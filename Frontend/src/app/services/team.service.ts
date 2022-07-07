import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';
import { Team } from '../models/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

    baseUrl = environment.apiUrl + "teams/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getTeamsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Team[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Team[]>>(this.baseUrl, { params });
	}

    getTeam(id) {
		return this.http.get<Team>(this.baseUrl + id);
	}

    createTeam(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateTeam(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deleteTeam(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
