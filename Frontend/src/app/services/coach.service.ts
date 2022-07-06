import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Coach } from '../models/coach';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class CoachService {

    baseUrl = environment.apiUrl + "coaches/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getCoachesByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Coach[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Coach[]>>(this.baseUrl, { params });
	}

    getCoach(id) {
		return this.http.get<Coach>(this.baseUrl + id);
	}

    createCoach(coachToCreate) {
		return this.http.post(this.baseUrl, coachToCreate);
	}

    updateCoach(id, coachToUpdate) {
		return this.http.put(this.baseUrl + id, coachToUpdate);
	}

	deleteCoach(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
