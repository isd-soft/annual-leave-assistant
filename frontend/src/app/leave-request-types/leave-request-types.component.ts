import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-leave-request-types',
  templateUrl: './leave-request-types.component.html',
  styleUrls: ['./leave-request-types.component.css']
})
export class LeaveRequestTypesComponent implements OnInit {

  private list: any;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get(environment.rootUrl+"/ala/leaveRequestTypes", {observe: "response"}).toPromise()
      .then(res => { this.list = res.body } ).catch(err => console.log());
  }

}
