import {Component, OnInit} from '@angular/core';
import {LeaveRequestTypeService} from '../../leaveRequestType.service';
import {Router} from '@angular/router';
import {LeaveRequestType} from '../../leaveRequestType';
import {FormBuilder, FormGroup} from '@angular/forms';
import {first} from 'rxjs/operators';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  requestType: LeaveRequestType;
  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private requestTpeService: LeaveRequestTypeService) {
  }

  ngOnInit() {
    const lrtID = localStorage.getItem('lrtID');
    if (!lrtID) {
      alert('Invalid action.');
      this.router.navigate(['leave-request-types']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [],
      name: [],
      description: []
    });
    this.requestTpeService.getRequestTypeById(+lrtID)
      .subscribe( data => {
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    this.requestTpeService.updateLeaveRequestType(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['leave-request-types']);
        },
        error => {
          alert(error);
        }
      );
  }


}
