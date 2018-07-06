import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from '../../../environments/environment';
import DateTimeFormat = Intl.DateTimeFormat;
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-holiday',
  templateUrl: './create-holiday.component.html',
  styleUrls: ['./create-holiday.component.css']
})
export class CreateHolidayComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  holidays: any;
  private date: string;
  private name: string;


  ngOnInit() {
    this.reloadData();
    console.log(localStorage.getItem('createUserId'));
  }


  reloadData() {
    }


  create() {
    // let d1 = new DatePipe (this.date);
    // let objDate = Date.now();
    // let d2 = new DatePipe(objDate.toString());
    //
    // if (d1 > d2) {
    //   window.alert('Wrong dates!');
    //   this.date = null;
    //   return;
    // }
    let body: any;

    if (localStorage.getItem('createHolidayId')) {
      body = {
        'id': { 'id': localStorage.getItem('createHolidayId')},
        'date': this.date,
        'name': this.name};
    } else {
      body = {
        'date': this.date,
        'name': this.name
      };
    }
    this.http.post(environment.rootUrl + '/ala/holidays/create', body, {observe: 'response'}).toPromise()
      .then(res => {
        if (res.status == 201) {
          window.alert("Created");
          this.router.navigate(['list-holidays']);
        } else {
          window.alert(res.body['message']);
        }
      }).catch(err => window.alert(err['message']));
  }

}
