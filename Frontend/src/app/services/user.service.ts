import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    baseUrl = environment.apiUrl;

	constructor(private http: HttpClient) { }

    getUsersByFilter(username?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<User[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (username != null)
			params = params.append('username', username);
        
        return this.http.get<PaginatedResult<User[]>>(this.baseUrl + "users/", { params });
	}

    getUser(id) {
		return this.http.get<User>(this.baseUrl + "users/" + id);
	}

    createUser(userToCreate) {
		return this.http.post(this.baseUrl + "users", userToCreate);
	}

    updateUser(id, userToUpdate) {
		return this.http.put(this.baseUrl + "users/" + id, userToUpdate);
	}

	deleteUser(id: any) {
		return this.http.delete(this.baseUrl + "users/" + id);
	}

}
