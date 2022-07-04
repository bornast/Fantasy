import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';

import {Location} from '@angular/common';
import { AuthService } from '../services/auth.service';
import { ToastService } from '../services/toast.service';

@Injectable({
	providedIn: 'root'
})
export class AuthGuard implements CanActivate {
	constructor(private authService: AuthService, private router: Router, private toast: ToastService, private _location: Location) {}

	canActivate(next: ActivatedRouteSnapshot): boolean {
		let roles: string[];
			
        roles = next.data['roles'] as Array<string>;

		if (roles) {
			const match = this.authService.userHasRole(roles);
			if (match) {
				return true;
			} else {
				return this.handleUnauthorizedAccess();
			}
		}
		if (this.authService.loggedIn()) {
			return true;
		}
		else {
			return this.handleUnauthorizedAccess();
		}
	}

	private handleUnauthorizedAccess(): boolean {
		this.toast.error('You are not authorized to access this area');
		this.router.navigate(['home']);
		return false;
	}

}
