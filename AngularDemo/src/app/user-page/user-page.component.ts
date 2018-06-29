import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../user';
import {UserPageService} from './user-page.service';
import {DatePipe} from '@angular/common';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(private router: Router, private userPageService: UserPageService, private datePipe: DatePipe) { }

  user: User = {};
  edit: boolean;
  hideProperty: boolean;
  editButton: string;
  role: string;
  sameEmail: string;
  emailValidationResponse: string;
  emailValidationMessage = '';

  getData(): void {
    this.datePipe = new DatePipe('en-US');
    this.userPageService.getUserData().subscribe(res => {
      this.user = res;
      this.role = this.user.role.role;
      console.log(this.user);
      console.log(this.user.email);
      console.log(this.user.employment_date);
      // this.user.employment_date = this.datePipe.transform(this.user.employment_date, 'yyyy-MM-dd');
    });
  }

  enableEdit(): void {
    this.edit = !this.edit;
    this.hideProperty = !this.hideProperty;
    if(!this.edit) {
      this.editButton = 'Back';
    } else {
      this.editButton = 'Edit';
    }
    this.sameEmail = this.user.email;
  }

  editData(ngForm: NgForm): void {
    if(ngForm.valid) {
      this.user.role.role = this.role;
      // this.user.employment_date = this.datePipe.transform(this.user.employment_date, 'yyyy-MM-dd');
      this.userPageService.user = this.user;
      if(this.sameEmail === this.user.email) {
        this.userPageService.editUserData().subscribe(res => {
          this.user = res;
          // this.user.employment_date = this.datePipe.transform(this.user.employment_date, 'yyyy-MM-yyyy');
        });
      } else {
        this.userPageService.verifyEmail().subscribe( res => {
          this.emailValidationResponse = res.status;
        });
          switch (this.emailValidationResponse) {
            case 'OK':
              this.userPageService.editUserData().subscribe( res => {
                this.user = res;
              });
              break;

            case 'FOUND':
              this.emailValidationMessage = 'This email is already taken!';
              break;
          }

      }
    } else {
      alert('Complete all fields');
    }
  }

  logout(): void {
    this.router.navigate(['/login']);
  }

  ngOnInit() {
    if(localStorage.getItem('id') == null) {
      this.router.navigate(['/login']);
    } else {
      this.getData();
      this.edit = true;
      this.hideProperty = false;
      this.editButton = 'Edit';
    }
  }

}
