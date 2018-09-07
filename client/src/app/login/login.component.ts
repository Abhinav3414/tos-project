import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { UtilityService } from '../services/utility.service';
import { AuthService } from '../services/auth.service';
import { LocalStorageService } from '../services/localStorage.service';
import { DataService } from '../services/data.service';
import { UrlService } from '../services/url.service';

import { Token } from '../token';

@Component({
  selector: 'login',
  templateUrl: './login.html'
})
export class LoginComponent {
  hide: boolean = true;

  user = {
    username: '',
    password: '',
    role: ''
  };

  userRoles: Array<String>;
  wrongCreds: string = undefined;

  constructor(private router: Router, private authService: AuthService,
    private localStorageService: LocalStorageService, private utilityService: UtilityService,
    private dataService: DataService, private urlService: UrlService) { }

  ngOnInit() {
    this.userRoles = ['admin', 'manager', 'employee'];
  }

  login(usercreds) {
    this.authService.login(usercreds)
      .then((data) => {
        this.localStorageService.setAuthorizationData(data);


          this.authService.getCurrentUser(usercreds.username)
            .then((data) => {
                  this.localStorageService.setUserData(data);
            });

          setTimeout(() => {
            this.utilityService.addTokenSubject(data.access_token);
            this.router.navigate(['/home']);
          }, 2000);

      },
      (err) => {
        this.wrongCreds = "Invalid Credentials"
      }
      );
  }

  onKeydown(event) {
    if (event.key === "Enter") {
      this.login(this.user);
    }
  }

}
