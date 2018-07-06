///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../user.service';
import {Observable} from 'rxjs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

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
    this.http.get(environment.rootUrl + '/ala/leaveRequests', { observe: 'response' })
      .toPromise().then(res => this.leaveRequests = res.body).catch(err => console.log(err));
  }


  updateLvReq(id: number){
    localStorage.removeItem('requestId');
    localStorage.setItem('requestId', id);
    localStorage.setItem('requestStartDate',);
    this.router.navigate(['create-leave-request']);
    this.http.put(environment.rootUrl + '/ala/leaveRequests' + id, {observe: 'response'})
      .toPromise().then(res => console.log(res)).catch(err => console.log(err));
  }


  deleteLvReq(id: number) {
    this.http.delete(environment.rootUrl + '/ala/leaveRequests/' + id, {observe: 'response'})
      .toPromise().then(res => console.log(res)).catch(err => console.log(err));
  }

  addLeaveRequest(){
    this.router.navigate(['create-leave-request']);
  }

}
