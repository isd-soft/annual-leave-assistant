import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';
import {LeaveRequestTypeService} from '../leaveRequestType.service';
import {LeaveRequestType} from '../leaveRequestType';
import {error} from 'util';

@Component({
  selector: 'app-leave-request-types',
  templateUrl: './leave-request-types.component.html',
  styleUrls: ['./leave-request-types.component.css']
})
export class LeaveRequestTypesComponent implements OnInit {

  list: any;

  constructor(private http: HttpClient, private router: Router, private requestTypeService: LeaveRequestTypeService) {
  }

  ngOnInit() {
    this.http.get(environment.rootUrl + '/ala/leaveRequestTypes', {observe: 'response'}).toPromise()
      .then(res => {
        this.list = res.body;
      }).catch(err => console.log());
  }

  addRequestType(): void {
    this.router.navigate(['app-create']);
  }

  deleteRequestType(requestType: LeaveRequestType) {
    this.requestTypeService.deleteLeaveRequestType(requestType.id)
      .subscribe(data => console.log(data), error1 => console.log(error));
  }

}
