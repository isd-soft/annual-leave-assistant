import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from '../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class DataService {


  constructor(private http: HttpClient) { }

  getUsers() {
     return this.http.get(environment.rootUrl +'/ala/users');
    // return this.http.get('https://jsonplaceholder.typicode.com/users');
  }
  getUser(userId) {
    return this.http.get(environment.rootUrl +'/leaveRequestTypes/all' + userId, {observe: 'response'});
  }

  getLeaveRequestTypes() {
    return this.http.get(environment.rootUrl +'/leaveRequestTypes/all');
  }

  getLeaveRequestType(leaveRequestTypeId) {
    return this.http.get(environment.rootUrl +'/leaveRequestTypes/all/' + leaveRequestTypeId, {observe: 'response'});
  }
}
