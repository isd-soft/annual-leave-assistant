///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Holidays} from '../../models/holidays';
import {HolidaysService} from '../../holidays.service';
import {error} from 'util';

@Component({
  selector: 'app-list-holiday',
  templateUrl: './list-holiday.component.html',
  styleUrls: ['./list-holiday.component.css']
})
export class ListHolidayComponent implements OnInit {

  holidays: Holidays[];

  constructor(private router: Router, private http: HttpClient, private holidayService: HolidaysService) {
  }

  ngOnInit() {
    this.holidayService.getHolidays()
      .subscribe(data => {
        this.holidays = data;
      });
  }

  addHoliday() {
    this.router.navigate(['create-holiday']);
  }

  deleteHoliday(holiday: Holidays): void {
    this.holidayService.deleteHoliday(holiday.id)
      .subscribe( data => {
        console.log(data);
      }, error1 => console.log(error));
    window.location.reload();
  }

}
