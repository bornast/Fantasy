import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';
import { Referee } from '../models/referee';

@Injectable({
  providedIn: 'root'
})
export class RefereeService {

    baseUrl = environment.apiUrl + "referees/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getRefereesByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Referee[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Referee[]>>(this.baseUrl, { params });
	}

    getReferee(id) {
		return this.http.get<Referee>(this.baseUrl + id);
	}

    createReferee(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateReferee(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deleteReferee(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
