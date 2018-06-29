import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {UserRegistrationService} from './user-registration.service';
import { Cookie } from 'ng2-cookies/ng2-cookies';
import {SaveCredentialsService} from '../save-credentials.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  constructor(private userRegistration: UserRegistrationService, private router: Router, private saveCredentials: SaveCredentialsService) { }

  email: string;
  password: string;
  request_message: string;
  verifyEmail: string;

  ngOnInit() {
    localStorage.clear();
    this.verifyEmail = 'email !== \'\'';
  }

  registrateUser(ngForm: NgForm): void {
    console.log(this.email);
    console.log(this.password);
    if (ngForm.valid) {
    this.userRegistration.email = this.email;
    this.userRegistration.password = this.password;
    this.userRegistration.registration().subscribe(res => {
      console.log(res.status);
      switch (res.status) {
        case 'OK':
          this.saveCredentials.setEmail(this.email);
          this.saveCredentials.setPassword(this.password);
          this.router.navigate(['/completeRegistration']);
          break;

        case 'FOUND':
          this.request_message = 'This email is already registred!';
          this.email = '';
          this.password = '';
      }
    });
  } else {
      this.request_message = 'Complete all forms';
    }
  }
}
