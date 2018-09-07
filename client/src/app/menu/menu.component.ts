import { Component } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { UtilityService } from '../services/utility.service';
import { BreadCrumb } from '../menu/breadCrumb';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'menu',
  templateUrl: './menu.html'
})
export class MenuComponent {
  items: any[];
  title = 'Team Operations System';
  titleXS = 'Team Operations';
  bread: BreadCrumb;
  userRoles: any[];
  isUserAdmin: boolean = false;
  isComponentReady: boolean = false;

  constructor(private utilityService: UtilityService, private router: Router, private route: ActivatedRoute,
                    private localStorageService: LocalStorageService) { }

  ngOnInit() {

    if(this.localStorageService.getUserDataValueFromLocalStorage()) {
      this.userRoles = this.localStorageService.getUserDataValueFromLocalStorage().roles;
      this.userRoles.forEach(r => {
        if(r.name === 'ADMIN') {
          this.isUserAdmin = true;
        }
        this.isComponentReady = true;
      })
    }

    this.utilityService.currentBreadCrumb.subscribe(bread => this.bread = bread);
    /*
      this.items = [
          {
              label: 'Home',
              icon: 'fa-home',
              command: (onclick)=> {this.navigateHome()}
          }
      ];
      */
  }

  navigateHome() {
    this.utilityService.addBreadCrumb(0, 'None', '/', -1, 'none', '');
    this.router.navigate(['/']);
  }

  navigateCustomers() {
    this.utilityService.addBreadCrumb(1, 'Customers', 'view/customers', 0, 'entities', '');
    this.router.navigate(['view/customers'], { skipLocationChange: false });
  }

  navigateEmployeeHome() {
    this.utilityService.addBreadCrumb(1, 'Employees', 'view/employees', 0, 'entities', '');
    this.router.navigate(['view/employees'], { skipLocationChange: false });
  }

  navigateAdmin() {
    this.utilityService.addBreadCrumb(1, 'Admin', 'view/admin', 0, 'single', '');
    this.router.navigate(['view/admin'], { skipLocationChange: false });
  }

  navigateAbout() {
    this.utilityService.addBreadCrumb(1, 'About', 'view/about', 0, 'single', '');
    this.router.navigate(['view/about'], { skipLocationChange: false });
  }

  navigateProfile() {
    this.utilityService.addBreadCrumb(1, 'Profile', 'view/profile', 0, 'single', '');
    this.router.navigate(['view/profile'], { skipLocationChange: false });
  }

  logout() {
    localStorage.clear();
    this.utilityService.addTokenSubject('');
    this.router.navigate(['view/login']);
  }

}
