import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpErrorResponse, HTTP_INTERCEPTORS, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { catchError, switchMap, filter, take, finalize } from 'rxjs/operators';
import { throwError, Observable, BehaviorSubject, of } from 'rxjs';
import { Router } from "@angular/router";
import { ToastService } from "../services/toast.service";
import { AuthService } from "../services/auth.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

	private AUTH_HEADER = "Authorization";
	private refreshTokenInProgress = false;
	private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

	constructor(private toast: ToastService, private authService: AuthService, private router: Router) { }

	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		return next.handle(req).pipe(
			catchError((error: HttpErrorResponse) => {
                this.handleErrors(error);
                return throwError(error);
			})
		) as Observable<HttpEvent<any>>;
	}

	handleErrors(error: HttpErrorResponse) {
        console.log(error);
		if (error.status === 0) {
			this.toast.error("Server is not responding!");
		}
		else if (error.status === 500) {
			this.toast.error("Server error!");
		}
		else if (error.status === 403) {
			this.toast.error("Forbidden!");
		}
		else if (error.status === 401) {
			this.toast.error("Unauthorized!");
			this.router.navigate(['/session/login']);
		}
		else if (error.status === 404) {
            this.toast.error("Not found!");
            this.handleErrorMessages(error.error);
		}
		else if (error instanceof HttpErrorResponse) {
			this.handleErrorMessages(error.error);
		}
	}

	handleErrorMessages(serverErrors: any) {
		if (serverErrors != null && serverErrors.errors != null) {
			for (const key in serverErrors.errors) {			
                this.toast.error(serverErrors.errors[key], key);
			}
		}
	}

}

export const ErrorInterceptorProvider = {
	provide: HTTP_INTERCEPTORS,
	useClass: ErrorInterceptor,
	multi: true
}