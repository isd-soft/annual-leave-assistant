import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Leaverequesttype} from './leaverequesttype';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class LeaverequesttypeService {

  private baseUrl = 'http://localhost:8088/ala/leaveRequestTypes';

  constructor(private http: HttpClient) {
  }

  getLeaveRequestsTypes() {
    return this.http.get<Leaverequesttype[]>(this.baseUrl);
  }

  createLeaveRequestType(leaverequesttype: Leaverequesttype) {
    return this.http.post(this.baseUrl + '/create', leaverequesttype);
  }

  updateLeaveRequestType(leaverequesttype: Leaverequesttype) {
    return this.http.put(this.baseUrl + '/' + leaverequesttype.id, leaverequesttype);
  }

  deleteLeaveRequestType(id: number) {
    return this.http.delete(this.baseUrl + '/delete/' + id);
  }
}
