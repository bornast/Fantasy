import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { Player } from '../models/player';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

    baseUrl = environment.apiUrl + "players/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getPlayersByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Player[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Player[]>>(this.baseUrl, { params });
	}

    getPlayer(id) {
		return this.http.get<Player>(this.baseUrl + id);
	}

    createPlayer(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updatePlayer(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deletePlayer(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
