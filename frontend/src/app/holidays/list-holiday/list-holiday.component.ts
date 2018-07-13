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
import {DeleteConfirmDialogComponent} from '../../shared/delete-confirm-dialog/delete-confirm-dialog.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-list-holiday',
  templateUrl: './list-holiday.component.html',
  styleUrls: ['./list-holiday.component.css']
})
export class ListHolidayComponent implements OnInit {

  holidays: Holidays[];
  dialogResult = '';

  constructor(private router: Router, private http: HttpClient, private holidayService: HolidaysService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.holidayService.getHolidays()
      .subscribe(data => {
        this.holidays = data;
      });
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }


  addHoliday() {
    this.router.navigate(['create-holiday']);
  }

  editHoliay(holiday: Holidays) {
    localStorage.removeItem('holidayId');
    localStorage.setItem('holidayId', holiday.id.toString());
    this.router.navigate(['edit-holiday']);
  }

  deleteHoliday(holiday: Holidays): void {
    this.holidayService.deleteHoliday(holiday.id)
      .subscribe(data => {
        console.log(data);
      }, error1 => console.log(error));
    window.location.reload();
  }

  openDialog(holiday: Holidays) {
    const dialogRef = this.dialog.open(DeleteConfirmDialogComponent,
      {
        width: '600px',
        data: 'You are sure you want to delete this holiday?'
      });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog closed: ${result}`);
      this.dialogResult = result;
      if (this.dialogResult === 'Confirm') {
        this.deleteHoliday(holiday);
      } else if (this.dialogResult === 'Cancel') {
        dialogRef.close();
      }
    });
  }

}
