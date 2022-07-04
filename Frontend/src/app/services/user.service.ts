import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    baseUrl = environment.apiUrl;

	constructor(private http: HttpClient) { }

	// getUsersByFilter(name?: string, pageNumber: any = 1, itemsPerPage: any = 5): Observable<PaginatedResult<User[]>> {

	// 	const paginatedResult: PaginatedResult<User[]> = new PaginatedResult<User[]>();

	// 	let params = new HttpParams();
	// 	params = params.append('pageSize', itemsPerPage);
	// 	params = params.append('pageNumber', pageNumber);
	// 	if (name != null)
	// 		params = params.append('name', name);

	// 	return this.http.get<User[]>(this.baseUrl + "user/", { observe: 'response', params })
	// 		.pipe(
	// 			map(response => {
	// 				paginatedResult.result = response.body;
	// 				if (response.headers.get('Pagination') != null) {
	// 					paginatedResult.pagination = JSON.parse(response.headers.get('Pagination'));
	// 				}
	// 				return paginatedResult;
	// 			})
	// 		);
	// }

    getUsersByFilter(name?: string, pageNumber: any = 1, itemsPerPage: any = 5): Observable<User[]> {
		return this.http.get<User[]>(this.baseUrl + "users/", { observe: 'response'})
			.pipe(
				map(response => {
                    console.log(response);
					return response.body;
				})
			);
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
