import {Component, OnInit} from '@angular/core';
import {Holidays} from '../../models/holidays';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HolidaysService} from '../../holidays.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-edit-holiday',
  templateUrl: './edit-holiday.component.html',
  styleUrls: ['./edit-holiday.component.css']
})
export class EditHolidayComponent implements OnInit {

  holiday: Holidays;
  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private holidayService: HolidaysService) {
  }

  ngOnInit() {
    const holidayId = localStorage.getItem('holidayId');
    if (!holidayId) {
      alert('Invalid action.');
      this.router.navigate(['list-holidays']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      date: ['', Validators.required],
    });
    this.holidayService.getHolidayById(+holidayId)
      .subscribe(data => {
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    this.holidayService.updateHoliday(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['list-holidays']);
        },
        error => {
          alert(error);
        });
  }

}
