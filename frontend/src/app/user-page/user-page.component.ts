import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import {toPromise} from "rxjs-compat/operator/toPromise";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  private id: string;
  private surname: string;
  private name: string;
  private email: string;
  private password  : string;
  private role  : string;
  private empDate: string;
  private token: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.completeFields();
    this.token = "Token " + localStorage.getItem("token");
    console.log(this.token);
  }

  disableTextbox =  true;

  completeFields(){
    this.id = localStorage.getItem("id");
    this.surname = localStorage.getItem("surname");
    this.password = "";
    this.name = localStorage.getItem("name");
    this.email = localStorage.getItem("email");
    this.empDate = localStorage.getItem("empDate");
    this.role = localStorage.getItem("role");
  }

  toggleDisable() {
    this.disableTextbox = !this.disableTextbox;
  }

  cancel(){
    this.completeFields();
    this.toggleDisable();

  }

  update() {
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Content-Type':  'application/json',
    //     'Authorization': this.token,
    //     'responseType': 'text'
    //   })
    // };

      this.http.put(environment.rootUrl + "/ala/users/" + this.id, {
        "surname": this.surname,
        "name": this.name,
        "email": this.email,
        "password": this.password
        //"empDate": this.empDate
      });
    }
}
