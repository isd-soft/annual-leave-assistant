///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {renderComponent} from '@angular/core/src/render3';
import {renderTemplate} from '@angular/core/src/render3/instructions';

@Component({
  selector: 'app-list-leave-request',
  templateUrl: './list-leave-request.component.html',
  styleUrls: ['./list-leave-request.component.css']
})
export class ListLeaveRequestComponent implements OnInit {

  leaveRequests: any;

  constructor(private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    this.reloadData();
  }


  updateLvReq(id: any, user_id: any, startDate: any, endDate: any, reqType: any){
    localStorage.removeItem('requestId');
    localStorage.removeItem('requestUserId');
    localStorage.removeItem('requestStartDate');
    localStorage.removeItem('requestEndDate');
    localStorage.removeItem('requestType');
    localStorage.setItem('requestId', id);
    localStorage.setItem('requestUserId', user_id);
    localStorage.setItem('requestStartDate', startDate);
    localStorage.setItem('requestEndDate', endDate);
    localStorage.setItem('requestType', reqType);
    this.router.navigate(['edit-leave-request']);
  }


  deleteLvReq(id: number) {
    this.http.delete(environment.rootUrl + '/ala/leaveRequests/' + id, {observe: 'response'})
      .toPromise().then(res => console.log(res)).catch(err => console.log(err));
    window.location.reload();
  }

  addLeaveRequest(){
    this.router.navigate(['create-leave-request']);
  }



  approve(id: any, user_id: any, startDate: any, endDate: any, reqType: any){
    let  body = {
      'user': { 'id': localStorage.getItem('requestUserId')},
      'leaveRequestType': {
        'id': this.reqType
      },
      'startDate': this.startDate,
      'endDate': this.endDate,
      'requestDate': new Date(),
      'status':{
        'id': '1'
      }
    };

    this.http.put(environment.rootUrl + '/ala/leaveRequests/' + id, body, {observe: 'response'})
      .toPromise().then(res => {
      window.alert('Leave request approved');
      window.location.reload();

    }).catch(err => window.alert(err['message']));


  }


  reloadData(){
    this.http.get(environment.rootUrl + '/ala/leaveRequests', { observe: 'response' })
      .toPromise().then(res => this.leaveRequests = res.body).catch(err => console.log(err));
  }

}
