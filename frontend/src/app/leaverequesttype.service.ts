import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Leaverequesttype} from './leaverequesttype';

@Injectable({
  providedIn: 'root'
})
export class LeaverequesttypeService {

  private baseUrl = 'http://localhost:8088/ala/leaveRequestTypes';

  constructor(private http: HttpClient) {
  }

  createLeaveRequestType(leaverequesttype: Leaverequesttype) {
    return this.http.post(this.baseUrl + '/create', leaverequesttype);
  }

  updateLeaveRequestType(leaverequesttype: Leaverequesttype) {
    return this.http.put(this.baseUrl + '/' + leaverequesttype.id, leaverequesttype);
  }

  deleteLeaveRequestType(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
