import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';

@Component({
  selector: 'app-edit-leave-request',
  templateUrl: './edit-leave-request.component.html',
  styleUrls: ['./edit-leave-request.component.css']
})
export class EditLeaveRequestComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  leaveRequestTypes: any[];
  reqType: any;
  type: string;
  private daysOff: number;
  private datesDiff = 0;
  private startDate: string;
  private endDate: string;


  ngOnInit() {
    this.reloadData();
  }

  update() {
    const id = localStorage.getItem('requestId');
    let body: any;

    console.log('user: ' + localStorage.getItem('requestUserId'));

    if(localStorage.getItem('requestUserId') && localStorage.getItem('role') === 'ADMIN') {
      body = {
        'user': { 'id': localStorage.getItem('requestUserId')},
        'leaveRequestType': {
          'id': this.reqType
        },
        'startDate': this.startDate,
        'endDate': this.endDate,
        'requestDate': new Date(),
        'status':{
          'id': '2'
        }
      };
    } else {
      body = {
        'leaveRequestType': {
          'id': this.reqType
        },
        'startDate': this.startDate,
        'endDate': this.endDate,
        'requestDate': new Date(),
        'status':{
          'id': '2'
        }
      };
    }

    this.http.put(environment.rootUrl + '/ala/leaveRequests/' + id, body, {observe: 'response'})
      .toPromise().then(res => {
      window.alert(res.body['message']);
        this.router.navigate(['leaveRequests']);

    }).catch(err => window.alert(err['message']));
  }



  dateChange() {
    if (this.startDate != null && this.endDate != null) {
      const d1 = new Date(this.startDate);
      const d2 = new Date(this.endDate);
      let diff: any;
      if (d1.getFullYear() === d2.getFullYear()) {
        diff = d2.getTime() - d1.getTime();
      }
      this.datesDiff = Math.round((diff) / (86400000)) + 1;
    } else {
      this.datesDiff = 0;
    }
  }




  reloadData() {
    this.startDate = localStorage.getItem('requestStartDate');
    this.endDate = localStorage.getItem('requestEndDate');
    this.type = localStorage.getItem('requestType');

    this.http.get<any[]>(environment.rootUrl + '/ala/leaveRequestTypes', {observe: 'response'}).toPromise()
      .then(res => {
        this.leaveRequestTypes = res.body;
      } ).catch(err => console.log());

    // Get number of available days
    this.http.get(environment.rootUrl + '/ala/users/' + localStorage.getItem('id'), {observe: 'response'})
      .toPromise().then(res => { this.daysOff = res.body['availDays']; } ).catch(err => console.log());
  }
}
