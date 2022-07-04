import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { RecordName } from '../models/recordName';
import { User } from '../models/User';

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

	deleteRole(id: any) {
		return this.http.delete(this.baseUrl + "roles/" + id);
	}

	getRole(id) {
		return this.http.get<User>(this.baseUrl + "roles/" + id);
	}

	updateRole(id, roleToUpdate) {
		return this.http.put(this.baseUrl + "roles/" + id, roleToUpdate);
	}

}
