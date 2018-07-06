import {Component, OnInit} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {LeaveRequestType} from '../../leaveRequestType';
import {LeaveRequestTypeService} from '../../leaveRequestType.service';
import {error} from 'util';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  leaveRequestType: LeaveRequestType = new LeaveRequestType();
  submitted = false;

  constructor(private leaveRequestTypeService: LeaveRequestTypeService, private router: Router) {
  }

  ngOnInit() {
  }

  saveRequestType() {
    this.leaveRequestTypeService.createLeaveRequestType(this.leaveRequestType)
      .subscribe(data => console.log(data), error1 => console.log(error));
    this.leaveRequestType = new LeaveRequestType();
  }

  onSubmit() {
    this.submitted = true;
    this.saveRequestType();
    this.router.navigate(['leaveRequestTypes']);
  }

}
