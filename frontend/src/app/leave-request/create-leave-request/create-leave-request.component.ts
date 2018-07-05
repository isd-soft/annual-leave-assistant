import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from '../../../environments/environment';
import DateTimeFormat = Intl.DateTimeFormat;
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-create-leave-request',
  templateUrl: './create-leave-request.component.html',
  styleUrls: ['./create-leave-request.component.css']
})
export class CreateLeaveRequestComponent implements OnInit {

  constructor(private http: HttpClient) { }

  leaveRequestTypes: any;
  reqType: any;
  private daysOff: number;
  private datesDiff = 0;
  private startDate: string;
  private endDate: string;


  ngOnInit() {
    this.reloadData();
  }


  reloadData(){
    this.http.get(environment.rootUrl + '/ala/leaveRequestTypes', {observe: 'response'}).toPromise()
      .then(res => { this.leaveRequestTypes = res.body; } ).catch(err => console.log());

    // Get number of available days
    this.http.get(environment.rootUrl + '/ala/users/' + localStorage.getItem('id'), {observe: 'response'})
      .toPromise().then(res => { this.daysOff = res.body['availDays']; } ).catch(err => console.log());
  }


  dateChange() {
    if (this.startDate != null && this.endDate != null) {
      const d1 = new Date(this.startDate);
      const d2 = new Date(this.endDate);
      let diff: any;
      if (d1.getFullYear() === d2.getFullYear()){
        diff = d2.getTime() - d1.getTime();
      }
      this.datesDiff = Math.round((diff) / (86400000)) + 1;
    } else {
      this.datesDiff = 0;
    }
  }


  create() {
    let d1 = new DatePipe (this.startDate);
    let d2 = new DatePipe (this.endDate);
    console.log(d1);
    console.log(d2);
    console.log(d1 > d2);
    if (d1 > d2) {
      window.alert('Wrong dates!');
      this.startDate = null;
      this.endDate = null;
      return;
    }


    this.http.post(environment.rootUrl + '/ala/leaveRequests', {
      'leaveRequestType': {
        'id': this.reqType
      },
      'startDate': this.startDate,
      'endDate': this.endDate,
      'requestDate': new Date()
    }, {observe: 'response'}).toPromise()
      .then(res => {
        if (res.status == 201) {
          window.alert(res.body['message']);
          this.reqType = null;
          this.startDate = null;
          this.endDate = null;
          location.reload();
        } else {
          window.alert(res.body['message']);
        }
      }).catch(err => window.alert(err['message']));
  }

}
