import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from "@auth0/angular-jwt";
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

	baseUrl = environment.apiUrl + 'auth/';
	jwtHelper = new JwtHelperService();
	decodedToken: any;

	constructor(private http: HttpClient) { }

	register(model: any) {
		return this.http.post(this.baseUrl + 'register', model);
	}

	login(model: any) {
		return this.http.post(this.baseUrl + 'login', model)
			.pipe(
				map((response: any) => {
					const user = response;
					if (user) {
						this.storeUserInfoToLocalStorage(user);
					}
				})
			);
	}

	loggedIn(): boolean {		
		const token = localStorage.getItem('fantasy-token');
		return token != null;
	}

	userHasRole(allowedRoles): boolean {
		let isMatch = false;
		if (this.decodedToken == null)
			return isMatch;
		const userRoles = this.decodedToken.roles as Array<string>;		
		allowedRoles.forEach(element => {
			if (userRoles.includes(element)) {
				isMatch = true;
				return;
			}
		});
		return isMatch;
	}

	logout() {		
		localStorage.removeItem('fantasy-token');
		localStorage.removeItem('fantasy-username');
		localStorage.removeItem('fantasy-userId');
		this.decodedToken = null;
	}

	private storeUserInfoToLocalStorage(tokenObject: any) {
		this.decodedToken = this.jwtHelper.decodeToken(tokenObject.token);
		localStorage.setItem('fantasy-token', tokenObject.token);
		localStorage.setItem('fantasy-userId', this.decodedToken.userId);
		localStorage.setItem('fantasy-username', this.decodedToken.username);
	}

}
