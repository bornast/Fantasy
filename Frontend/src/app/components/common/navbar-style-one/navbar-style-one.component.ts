import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-navbar-style-one',
  templateUrl: './navbar-style-one.component.html',
  styleUrls: ['./navbar-style-one.component.scss']
})
export class NavbarStyleOneComponent implements OnInit {

  loginObject: any = {};

  constructor(private authService: AuthService, private toast: ToastService) { }

  ngOnInit(): void {
  }

  login() {
		this.loginObject = {
			username: "admin",
			password: "admin"
		}
		this.authService.login(this.loginObject).subscribe(() => {
			console.log('Logged in successfully');
			this.toast.success('Logged in successfully');
			// this.router.navigate(['/home']);
		});
	}

}
