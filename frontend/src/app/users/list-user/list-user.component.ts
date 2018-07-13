// <reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {User} from '../../user';
import {Router} from '@angular/router';
import {UserService} from '../../user.service';
import {MatDialog, MatTable} from '@angular/material';
import {Observable} from 'rxjs';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {DeleteConfirmDialogComponent} from '../../shared/delete-confirm-dialog/delete-confirm-dialog.component';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[];
  dialogResult = '';

  constructor(private router: Router, private userService: UserService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.userService.getUsers()
      .subscribe(data => {
        this.users = data;  console.log(data);
      });
  }

  addUser(): void {
    this.router.navigate(['register']);
  }

  updateUser(user: User): void {
    localStorage.removeItem('updateUserId');
    localStorage.setItem('updateUserId', user.id.toString());
    this.router.navigate(['edit-user']);
  }

  addLeaveRequest(id: any) {
    localStorage.removeItem('createUserId');
    console.log(id);
    localStorage.setItem('createUserId', id);
    this.router.navigate(['create-leave-request']);
  }

  deleteUser(user: User): void {
    this.userService.deleteUser(user.id)
      .subscribe(data => {
        this.users = this.users.filter(u => u !== user);
      });
    window.location.reload();
  }

  openDialog(user: User) {
    const dialogRef = this.dialog.open(DeleteConfirmDialogComponent,
      {
        width: '600px',
        data: 'You are sure you want to delete this user ?'
  });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog closed: ${result}`);
      this.dialogResult = result;
      if (this.dialogResult === 'Confirm') {
        this.deleteUser(user);
      } else if (this.dialogResult === 'Cancel') {
        dialogRef.close();
      }
    });
  }

  deleteAllUsers(): void {
    this.userService.deleteAllUsers()
      .subscribe(data => {
        console.log(data);
        this.ngOnInit();
        this.router.navigate(['/users']);
      });
     window.location.reload();
  }

}
