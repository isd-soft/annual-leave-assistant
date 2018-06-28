import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  private surname: string;
  private name: string;
  private email: string;
  private password  : string;
  private empDate: Date;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  disableTextbox =  true;

  toggleDisable() {
    this.disableTextbox = !this.disableTextbox;
  }

  update() {

  }

}
