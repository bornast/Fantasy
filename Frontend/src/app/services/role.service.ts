import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
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
