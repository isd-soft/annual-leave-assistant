// <reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {User} from '../../user';
import {Router} from '@angular/router';
import {UserService} from '../../user.service';
import {MatTable} from '@angular/material';
import {Observable} from 'rxjs';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[];

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers()
      .subscribe(data => {
        this.users = data;
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
  }

  deleteAllUsers(): void {
    this.userService.deleteAllUsers()
      .subscribe(data => {
        console.log(data);
        this.ngOnInit();
        this.router.navigate(['/users']);
      });
    // window.location.reload();
  }

}
