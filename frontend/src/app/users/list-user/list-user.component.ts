//<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {User} from '../../user';
import {Router} from '@angular/router';
import {UserService} from '../../user.service';
import {Observable} from 'rxjs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: any;

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers()
      .subscribe(data => {
        this.users = data;
      });
  }

  addUser(): void {
    this.router.navigate(['add-user']);
  }

  updateUser(user: User): void {
    localStorage.removeItem('updateUserId');
    localStorage.setItem('updateUserId', user.id.toString());
    this.router.navigate(['edit-user']);
  }

  deleteUser(user: User): void {
    this.userService.deleteUser(user.id)
      .toPromise().then(res => { this.users = res; }).catch(err => console.log(err));
    this.reloadData();
  }

  deleteAllUsers(): void {
    this.userService.deleteAllUsers()
      .subscribe(data => {
        console.log(data);
      });
  }

  reloadData() {
    this.userService.getUsers()
      .subscribe(data => {
        this.users = data;
      });
  }
}
