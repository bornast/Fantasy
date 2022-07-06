import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class PositionService {

    baseUrl = environment.apiUrl + "positions/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getPositionsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<RecordName[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<RecordName[]>>(this.baseUrl, { params });
	}

    getPosition(id) {
		return this.http.get<RecordName>(this.baseUrl + id);
	}

    createPosition(positionToCreate) {
		return this.http.post(this.baseUrl, positionToCreate);
	}

    updatePositions(id, positionToUpdate) {
		return this.http.put(this.baseUrl + id, positionToUpdate);
	}

	deletePositions(id: any) {
		return this.http.delete(this.baseUrl + id);
	}	

}
