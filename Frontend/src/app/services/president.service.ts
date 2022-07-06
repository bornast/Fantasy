import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { President } from '../models/president';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class PresidentService {

    baseUrl = environment.apiUrl + "presidents/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getPresidentsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<President[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<President[]>>(this.baseUrl, { params });
	}

    getPresident(id) {
		return this.http.get<President>(this.baseUrl + id);
	}

    createPresident(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updatePresident(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deletePresident(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
