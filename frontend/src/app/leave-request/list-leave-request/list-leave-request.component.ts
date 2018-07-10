
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import * as FileSaver from 'file-saver';
import {User} from '../../user';
import {LeaveRequestType} from '../../leaveRequestType';

@Component({
  selector: 'app-list-leave-request',
  templateUrl: './list-leave-request.component.html',
  styleUrls: ['./list-leave-request.component.css']
})
export class ListLeaveRequestComponent implements OnInit {

  leaveRequests: any;
  surname: string;
  user: User[];

  constructor(private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    this.reloadData();
    this.surname = null;
  }

    isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  updateLvReq(id: any, user_id: any, reqType: any, startDate: any, endDate: any) {
    localStorage.removeItem('requestId');
    localStorage.removeItem('requestType');
    localStorage.removeItem('requestUserId');
    localStorage.removeItem('requestStartDate');
    localStorage.removeItem('requestEndDate');
    localStorage.removeItem('requestType');
    localStorage.setItem('requestId', id);
    localStorage.setItem('requestUserId', user_id);
    localStorage.setItem('requestType', reqType);
    localStorage.setItem('requestStartDate', startDate);
    localStorage.setItem('requestEndDate', endDate);
    this.router.navigate(['edit-leave-request']);
  }


  deleteLvReq(id: number) {
    this.http.delete(environment.rootUrl + '/ala/leaveRequests/' + id, {observe: 'response'})
      .toPromise().then(res => console.log(res)).catch(err => console.log(err));
    window.location.reload();
  }


  addLeaveRequest(){
    localStorage.removeItem('createUserId');
    this.router.navigate(['create-leave-request']);
  }


  approve(id: any, user_id: any, reqType: any, startDate: any, endDate: any) {
    const body = {
      'user': {'id': localStorage.getItem('requestUserId')},
      'leaveRequestType': {
        'id': reqType
      },
      'startDate': startDate,
      'endDate': endDate,
      'requestDate': new Date(),
      'status': {
        'id': '1'
      }
    };

    this.http.put(environment.rootUrl + '/ala/leaveRequests/' + id, body, {observe: 'response'})
      .toPromise().then(res => {
      window.alert('Leave request approved');
      window.location.reload();

    }).catch(err => window.alert(err['message']));
  }

  reloadData() {
    this.http.get(environment.rootUrl + '/ala/leaveRequests', {observe: 'response'})
      .toPromise().then(res => this.leaveRequests = res.body).catch(err => console.log(err));
  }

  download(id: any, user: User, requestType: LeaveRequestType) {
    this.http.get(environment.rootUrl + '/ala/utility/word/' + id, {responseType: 'blob' as 'json'}).toPromise()
      .then(data => {
        console.log(user);
        console.log(requestType);
        console.log(data);
        const blob = new Blob([data], {type: 'application/octet-stream'});
        FileSaver.saveAs(blob,
           user.toString() + ' ' + requestType.toString() + '.docx');
      });
  }

}
