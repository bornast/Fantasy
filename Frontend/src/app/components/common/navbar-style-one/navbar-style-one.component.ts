import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-navbar-style-one',
  templateUrl: './navbar-style-one.component.html',
  styleUrls: ['./navbar-style-one.component.scss']
})
export class NavbarStyleOneComponent implements OnInit {

  loginObject: any = {};

  constructor(private authService: AuthService, private toast: ToastService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
		this.authService.login(this.loginObject).subscribe(() => {
			this.toast.success('Logged in successfully');
			this.router.navigate(['/']);
		});
	}

}
