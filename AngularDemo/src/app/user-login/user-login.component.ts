import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {Router} from '@angular/router';
import {UserLoginServiceService} from './user-login-service.service';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
})
export class UserLoginComponent implements OnInit {

  email: string;
  password: string;
  request_message = '';
  constructor(private userLoginService: UserLoginServiceService, private router: Router) {
  }

  ngOnInit() {
    localStorage.clear();
  }

  loginUser(): void {
    this.userLoginService.email = this.email;
    this.userLoginService.password = this.password;
    console.log(this.email + this.password);
    this.userLoginService.login().subscribe(res => {
      //console.log(res.id);
        switch (res.id) {
          case 'Such user was not found!':
            this.request_message = 'Such user was not found!';
            this.email = '';
            this.password = '';
            break;

          case 'Wrong password!':
            this.request_message = 'Wrong password!';
            this.password = '';
            break;

          default:
            localStorage.setItem('id', res.id);
            this.router.navigate(['/profile-page']);
            break;
        }
      },
      (err: HttpErrorResponse) => {
      });
}

registrate(): void {
    this.router.navigate(['/registration']);
}

}
