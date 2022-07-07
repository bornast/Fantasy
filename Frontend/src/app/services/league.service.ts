import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { League } from '../models/league';
import { PaginatedResult } from '../models/pagination';

@Injectable({
  providedIn: 'root'
})
export class LeagueService {

    baseUrl = environment.apiUrl + "leagues/";

	constructor(private http: HttpClient) { }

    getLeaguesByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<League[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<League[]>>(this.baseUrl, { params });
	}

    getLeague(id) {
		return this.http.get<League>(this.baseUrl + id);
	}

    createLeague(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateLeague(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deleteLeague(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
