import {Component, OnInit} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Leaverequesttype} from '../../leaverequesttype';
import {LeaverequesttypeService} from '../../leaverequesttype.service';
import {error} from 'util';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  leaveRequestType: Leaverequesttype = new Leaverequesttype();
  submitted = false;

  constructor(private leaveRequestTypeService: LeaverequesttypeService, private router: Router) {
  }

  ngOnInit() {
  }

  newRequestType(): void {
    this.submitted = false;
    this.leaveRequestType = new Leaverequesttype();
  }

  saveRequestType() {
    this.leaveRequestTypeService.createLeaveRequestType(this.leaveRequestType)
      .subscribe(data => console.log(data), error1 => console.log(error));
    this.leaveRequestType = new Leaverequesttype();
  }

  onSubmit() {
    this.submitted = true;
    this.saveRequestType();
    this.router.navigate(['leaveRequestTypes']);
  }

}
