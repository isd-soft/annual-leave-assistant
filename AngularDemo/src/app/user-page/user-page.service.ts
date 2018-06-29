import { Injectable } from '@angular/core';
import {User} from '../user';
import {HttpClient} from '@angular/common/http';
import {SaveCredentialsService} from '../save-credentials.service';
import {DatePipe} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class UserPageService {

  constructor(private http: HttpClient, private saveCredentials: SaveCredentialsService, private datePipe: DatePipe) { }

  user: User;

  getUserData() {
    console.log('//localhost:8080/user/' + localStorage.getItem('id'));
    return this.http.get('//localhost:8080/user/' + localStorage.getItem('id'));
  }

  editUserData() {
    return this.http.put('//localhost:8080/user/' + localStorage.getItem('id'), this.user);
  }

  verifyEmail() {
    return this.http.get('//localhost:8080/verifyEmail/' + this.user.email);
  }
}
