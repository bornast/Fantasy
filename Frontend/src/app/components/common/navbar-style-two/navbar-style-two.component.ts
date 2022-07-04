import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar-style-two',
  templateUrl: './navbar-style-two.component.html',
  styleUrls: ['./navbar-style-two.component.scss']
})
export class NavbarStyleTwoComponent implements OnInit {

  loginObject: any = {};

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  login() {
		this.loginObject = {
			username: "admin",
			password: "admin"
		}
		this.authService.login(this.loginObject).subscribe(() => {
			console.log("successfull login!");
			// this.toast.success('Logged in successfully');
			// this.router.navigate(['/home']);
		});
	}

}
