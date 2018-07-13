import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';
import {LeaveRequestTypeService} from '../leaveRequestType.service';
import {LeaveRequestType} from '../leaveRequestType';
import {error} from 'util';
import {Observable} from 'rxjs';
import {MatDialog} from '@angular/material';
import {DeleteConfirmDialogComponent} from '../shared/delete-confirm-dialog/delete-confirm-dialog.component';

@Component({
  selector: 'app-leave-request-types',
  templateUrl: './leave-request-types.component.html',
  styleUrls: ['./leave-request-types.component.css']
})
export class LeaveRequestTypesComponent implements OnInit {

  leaveRequestType: LeaveRequestType[];
  dialogResult = '';

  constructor(private http: HttpClient, private router: Router, private requestTypeService: LeaveRequestTypeService, public dialog: MatDialog) {
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

  openDialog(requestType: LeaveRequestType) {
    const dialogRef = this.dialog.open(DeleteConfirmDialogComponent,
      {
        width: '600px',
        data: 'You are sure you want to delete this leave request type?'
      });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog closed: ${result}`);
      this.dialogResult = result;
      if (this.dialogResult === 'Confirm') {
        this.deleteRequestType(requestType);
      } else if (this.dialogResult === 'Cancel') {
        dialogRef.close();
      }
    });
  }
}
