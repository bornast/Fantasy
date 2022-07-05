import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

    baseUrl = environment.apiUrl;

	constructor(private http: HttpClient) { }


    // TODO: add ge
    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "roles/");
	}

    getRolesByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<RecordName[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<RecordName[]>>(this.baseUrl + "roles/", { params });
	}

    getRole(id) {
		return this.http.get<RecordName>(this.baseUrl + "roles/" + id);
	}

    createRole(roleToCreate) {
		return this.http.post(this.baseUrl + "roles/", roleToCreate);
	}

    updateRole(id, roleToUpdate) {
		return this.http.put(this.baseUrl + "roles/" + id, roleToUpdate);
	}

	deleteRole(id: any) {
		return this.http.delete(this.baseUrl + "roles/" + id);
	}	

}
