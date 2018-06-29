import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserLoginServiceService {

  constructor(private http: HttpClient, private route: Router) {
  }
  email: string;
  password: string;

  login() {
    const body = {email: this.email, password: this.password};

   return this.http.post('//localhost:8080/login', body );
  }
}
