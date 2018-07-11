import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LeaveRequestType} from './leaveRequestType';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class LeaveRequestTypeService {

  private baseUrl = 'http://localhost:8088/ala/leaveRequestTypes';

  constructor(private http: HttpClient) {
  }

  getRequestTypeById(id: number) {
    return this.http.get<LeaveRequestType[]>(this.baseUrl + '/' + id);
  }

  getLeaveRequestsTypes() {
    return this.http.get<LeaveRequestType[]>(this.baseUrl);
  }

  createLeaveRequestType(leaverequesttype: LeaveRequestType) {
    return this.http.post(this.baseUrl + '/cr', leaverequesttype);
  }

  updateLeaveRequestType(leaverequesttype: LeaveRequestType) {
    return this.http.put(this.baseUrl + '/edit/' + leaverequesttype.id, leaverequesttype);
  }

  deleteLeaveRequestType(id: number) {
    return this.http.delete(this.baseUrl + '/delete/' + id);
  }

}
