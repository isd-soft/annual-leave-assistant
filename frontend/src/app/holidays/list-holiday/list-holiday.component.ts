///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-list-holiday',
  templateUrl: './list-holiday.component.html',
  styleUrls: ['./list-holiday.component.css']
})
export class ListHolidayComponent implements OnInit {

  holidays: any;

  constructor(private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get(environment.rootUrl + '/ala/holidays', { observe: 'response' })
      .toPromise().then(res => { this.holidays = res.body; console.log(res);}).catch(err => console.log(err));
  }



  deleteHoliday(id: number) {
    console.log('ID: ' + id);
    this.http.delete(environment.rootUrl + '/ala/holidays/' + id, {observe: 'response'})
      .toPromise().then(res => console.log(res)).catch(err => console.log(err));
  }

}
