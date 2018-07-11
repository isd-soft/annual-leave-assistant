import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';
import {LeaveRequestTypeService} from '../leaveRequestType.service';
import {LeaveRequestType} from '../leaveRequestType';
import {error} from 'util';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-leave-request-types',
  templateUrl: './leave-request-types.component.html',
  styleUrls: ['./leave-request-types.component.css']
})
export class LeaveRequestTypesComponent implements OnInit {

  leaveRequestType: LeaveRequestType[];

  constructor(private http: HttpClient, private router: Router, private requestTypeService: LeaveRequestTypeService) {
  }

  ngOnInit() {

    this.requestTypeService.getLeaveRequestsTypes()
      .subscribe(data => {
        this.leaveRequestType = data;
      });
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  addRequestType(): void {
    this.router.navigate(['app-create']);
  }

  editRequestType(requestType: LeaveRequestType): void {
    localStorage.removeItem('lrtID');
    localStorage.setItem('lrtID', requestType.id.toString());
    this.router.navigate(['app-edit']);
  }

  deleteRequestType(requestType: LeaveRequestType) {
    this.requestTypeService.deleteLeaveRequestType(requestType.id)
      .subscribe(data => {
        console.log(data);
        this.leaveRequestType = this.leaveRequestType.filter(lt => lt !== requestType);
      }, error1 => console.log(error));
    window.location.reload();
  }
}
