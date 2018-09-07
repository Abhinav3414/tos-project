import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'admin-roledialog',
  template: `
          <div *ngIf="data.user!==undefined" >
             <h2 *ngIf="data.user.id === ''" mat-dialog-title>Add CuUserstomer</h2>
             <h2   *ngIf="data.user.id !== ''" mat-dialog-title>Update User Role</h2>
             <mat-dialog-content>
                <mat-form-field >
                   <input matInput placeholder="Name" name="name" readonly disabled [value]="data.user.username" [(ngModel)]="data.user.username" ngModel><br>
                </mat-form-field>

                <mat-form-field *ngIf="data.roleList">
                   <mat-select [(value)]="selected" placeholder="{{data.getUserRoles(data.user)}}" multiple [(ngModel)]="data.user.roles" name="role">
                   <mat-option *ngFor="let role of data.roleList" [value]="role">
                   {{ role }}
                   </mat-option>
                   </mat-select>
                </mat-form-field>

             </mat-dialog-content>
             <mat-dialog-actions>
                <button mat-raised-button class="tos-primary-button" [mat-dialog-close]=data.user *ngIf="data.user.id !== ''">Update</button>
                <button mat-raised-button class="tos-primary-button" [mat-dialog-close]=data.user *ngIf="data.user.id === ''">Add</button>
                <button mat-button mat-dialog-close="dialogDismissed" disableRipple="true" class="tos-button">Cancel</button>
             </mat-dialog-actions>
          </div>
  `
})
export class AdminRoleDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AdminRoleDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

}
