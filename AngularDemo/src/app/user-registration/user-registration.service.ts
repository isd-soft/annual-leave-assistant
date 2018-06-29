import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  constructor(private http: HttpClient) { }

  email: string;
  password: string;

  registration() {
    const body = {email: this.email, password: this.password};
    return this.http.post('//localhost:8080/registration', body);
  }
}

