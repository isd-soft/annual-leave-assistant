import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../user';
import {CompleteRegistrationService} from './complete-registration.service';
import { Cookie } from 'ng2-cookies/ng2-cookies';
import {SaveCredentialsService} from '../save-credentials.service';
import {DatePipe} from '@angular/common';
import {GetRoleService} from '../get-role.service';
import {Role} from '../role';

@Component({
  selector: 'app-complete-registration',
  templateUrl: './complete-registration.component.html',
  styleUrls: ['./complete-registration.component.css']
})
export class CompleteRegistrationComponent implements OnInit {

  constructor(private router: Router, private completeRegistrationService: CompleteRegistrationService,
              private saveCredentials: SaveCredentialsService, private getRoleService: GetRoleService) {
  }

  user: User = {};
  name: string;
  surname: string;
  date: Date;
  role: Role = {};

  ngOnInit() {
    console.log(this.saveCredentials.getEmail());
    if(this.saveCredentials.getEmail() === undefined) {
      this.router.navigate(['/registration']);
    }
    this.user.email = this.saveCredentials.getEmail();
    this.user.password = this.saveCredentials.getPassword();
    this.getRoleService.name = 'USER';
    this.getRoleService.getRole().subscribe(res => {this.role = res; console.log(res); console.log(this.role); });
    }

  completeRegistration(): void {
    // console.log(this.saveCredentials.getEmail());
    this.user.name = this.name;
    this.user.surname = this.surname;
    this.user.role = this.role;
    this.user.employment_date = this.date;
    console.log(this.user.employment_date);
    // this.user.employment_date = new Date('01/01/2000');
    // this.user.employment_date.setFullYear(new Date().getFullYear());
    // this.user.employment_date = this.datePipe.transform(this.user.employment_date, 'yyyy-MM-dd');
    this.completeRegistrationService.user = this.user;
    this.completeRegistrationService.registrate().subscribe( res => {
      this.saveCredentials.setId(res.id);
      localStorage.setItem('id', res.id);
      this.router.navigate(['/profile-page']);
      console.log(this.saveCredentials.getId());

    });

  }

}
