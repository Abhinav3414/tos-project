import { trigger, state, style, animate, transition } from '@angular/animations';
import { DataService } from '../services/data.service';
import { Component } from '@angular/core';

import { AdminRoleDialogComponent } from './admin-roledialog.component';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

class UserRolePlaceholderObj {
  userId: string;
  roles: string;
}

@Component({
  selector: 'about',
  templateUrl: './admin-view.html',
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),
      transition(':enter, :leave', [
        animate('200ms ease-in')
      ])
    ])
  ]
})
export class AdminViewComponent {
  paginators: Array<any> = [1, 2, 3];
  activePage: number = 1;
  firstVisibleIndex: number = 1;
  lastVisibleIndex: number = 5;

  roleList: string[] = ['ADMIN', 'CUSTOMER', 'EMPLOYEE', 'GUEST', 'MANAGER'];
  users = [];
  user: any;
  userRoles = [];
  userRolesFromDialog = [];
  userRolePlaceholder: string;
  optionsSelect: Array<any>;
  userRolePlaceholders = [];

  constructor(private dataService: DataService, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.dataService.getUsers()
      .then((resUserData) => {
        resUserData.forEach(u => this.users.push(u));

        this.users.forEach(u => {
          var userRolePlaceholderObj = new UserRolePlaceholderObj();
          userRolePlaceholderObj.userId = u.id;
          userRolePlaceholderObj.roles = this.getUserRoles(u);
          this.userRolePlaceholders.push(userRolePlaceholderObj);
        })
      },
      (err) => console.log("users could not be fetched :" + err)
      );

  }

  getUserRoles(user: any) {
    let str = '';
    if (user.roles.length !== 0 && user.roles[0].name === undefined) {
      user.roles.forEach(r => { str = str + r + ", " });
    } else {
      user.roles.forEach(r => { str = str + r.name + ", " });
    }
    str = str.slice(0, -2);
    this.userRolePlaceholder = str;
    return this.userRolePlaceholder;
  }

  openDialog(id: number): void {

    const index = this.users.findIndex(c => c.id === id);
    this.user = this.users[index];
    var userCopy = Object.assign({}, this.user);

    this.user.roles.forEach(r => this.userRoles.push(r.name));
    var roleLength = this.user.roles.length;

    this.getUserRoles(this.user);
    let dialogRef = this.dialog.open(AdminRoleDialogComponent, {
      data: this
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result !== 'dialogDismissed' && result !== undefined) {

        roleLength = (this.user.roles.length > roleLength) ? this.user.roles.length : roleLength;

        for (var i = 0; i < roleLength; i++) {
          if (this.user.roles.indexOf(this.userRoles[i]) == -1) {

            let index = this.userRolePlaceholders.findIndex(ur => ur.userId === this.user.id);
            this.userRolePlaceholders[index].roles = this.getUserRoles(this.user);

            this.userRolePlaceholders.forEach(u => console.log(u))
            console.log("roles have changed");
            this.updateRoles(this.user.id);
            break;
          }
        }
      }
    });

  }

  updateRoles(id: number) {
    this.dataService.updateUserRoles('users', id, this.user);
  }

  changePage(event: any) {
    if (event.target.text >= 1 && event.target.text <= 3) {
      this.activePage = +event.target.text;
      this.firstVisibleIndex = this.activePage * 5 - 5 + 1;
      this.lastVisibleIndex = this.activePage * 5;
    }
  }

  nextPage(event: any) {
    this.activePage += 1;
    this.firstVisibleIndex = this.activePage * 5 - 5 + 1;
    this.lastVisibleIndex = this.activePage * 5;
  }
  previousPage(event: any) {
    this.activePage -= 1;
    this.firstVisibleIndex = this.activePage * 5 - 5 + 1;
    this.lastVisibleIndex = this.activePage * 5;
  }

  firstPage() {
    this.activePage = 1;
    this.firstVisibleIndex = this.activePage * 5 - 5 + 1;
    this.lastVisibleIndex = this.activePage * 5;
  }

  lastPage() {
    this.activePage = 3;
    this.firstVisibleIndex = this.activePage * 5 - 5 + 1;
    this.lastVisibleIndex = this.activePage * 5;
  }

  remove(id: any) {
    //   this.awaitingPersonList.push(this.personList[id]);
    this.dataService.delelteUserEntity('users', id)
      .then((resCustomerData) => {
        this.users.splice(this.users.findIndex(function(i) {
          return i.id === id;
        }), 1);
      },
      (err) => console.log('users' + " could not be deleted :" + err)
      );
    this.users.splice(id, 1);
  }



  add() {
  }

}
