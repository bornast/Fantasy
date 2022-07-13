import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    isLoggedIn: boolean;
    loginObject: any = {};
    registerObject: any = {};
    
    constructor(private authService: AuthService, private toast: ToastService, private router: Router) { }

    ngOnInit(): void {
        this.isLoggedIn = localStorage.getItem('fantasy-userId') != null;
        console.log("isloggedin", this.isLoggedIn);
    }

    login() {
        this.authService.login(this.loginObject).subscribe(() => {
            this.toast.success('Logged in successfully');
            this.router.navigate(['/team-picker']);
        }, () => {
            this.loginObject.username = "";
            this.loginObject.password = "";
        });
	}

    register() {
        this.authService.register(this.registerObject).subscribe(() => {
            this.toast.success('Registered successfully');
            this.clearRegistrationData();
        }, () => {
            this.clearRegistrationData();
        });
	}

    private clearRegistrationData() {
        this.registerObject.username = "";
        this.registerObject.password = "";
        this.registerObject.firstName = "";
        this.registerObject.lastName = "";
    }

    pageTitleContent = [
        {
            title: 'How Fantasy works',
            backgroundImage: 'https://geniussports.com/media/vkbhvw25/header-media.png'
        }
    ]

    timelineContent = [
        {
            number: '1',
            title: 'Register',
            description: 'Register your account using username and password only.',
            buttonText: 'Submit Now',
            buttonLink: '#'
        },
        {
            number: '2',
            title: 'Login to My Account',
            description: 'Login to explore and use Fantasy application.',
            buttonText: 'Choose Now',
            buttonLink: '#'
        },
        {
            number: '3',
            title: 'Pick your favourite teams',
            description: 'Find teams from different leagues to follow. Examine their latest results, players, upcoming matches and rate players.',
            buttonText: 'Team Picker',
            buttonLink: 'team-picker'
        },
        {
            number: '4',
            title: 'Share memories',
            description: 'Explore your favourite team results. Upload image and video memories for any match. Share your memories with other users. Explore other user uploaded match memories. ',
            buttonText: 'Choose Now',
            buttonLink: '#'
        },
        {
            number: '5',
            title: 'Chat',
            description: 'Communicate with users that share the same interests as you.',
            buttonText: 'Browse Now',
            buttonLink: '#'
        }
    ]

}
