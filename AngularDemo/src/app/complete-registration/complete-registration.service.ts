import { Injectable } from '@angular/core';
import {User} from '../user';
import {HttpClient} from '@angular/common/http';
import {DatePipe} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class CompleteRegistrationService {

  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  user: User;

  registrate() {
    return this.http.post('//localhost:8080/completeRegistration', this.user);
  }
}
