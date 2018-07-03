import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';
import {toPromise} from 'rxjs-compat/operator/toPromise';

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
  private password: string;
  private role: string;
  private empDate: string;
  private token: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.completeFields();
    this.token = 'Token ' + localStorage.getItem('token');
    //console.log(this.token);
  }

  disableTextbox =  true;

  completeFields(){
    this.id = localStorage.getItem('id');
    this.surname = localStorage.getItem('surname');
    this.password = "";
    this.name = localStorage.getItem('name');
    this.email = localStorage.getItem('email');
    this.empDate = localStorage.getItem('empDate');
    this.role = localStorage.getItem('role');
  }

  toggleDisable() {
    this.disableTextbox = !this.disableTextbox;
  }

  cancel() {
    this.completeFields();
    this.toggleDisable();

  }

  update() {
    var body: any;
    if(this.surname != localStorage.getItem("surname")){
      console.log("SURNAME");
    }

    if(this.name != localStorage.getItem("name")){
      console.log("NAME");
    }

    if(this.email != localStorage.getItem("email")){
      console.log("EMAIL");
    }

    if(this.empDate != localStorage.getItem("empDate")){
      console.log("EMPDATE");
    }

    if(this.password != ""){
      body = {
        "surname": this.surname,
        "name": this.name,
        "email": this.email,
        "password": this.password,
        "empDate": this.empDate
      };
      console.log("PASSWORD");
    } else {
      body = {
        "surname": this.surname,
        "name": this.name,
        "email": this.email,
        "empDate": this.empDate
      };
    }

    this.http.put(environment.rootUrl + "/ala/users/" + this.id, body).toPromise()
      .then(res => {
        console.log(res);
        localStorage.setItem("surname", this.surname);
        localStorage.setItem("name", this.name);
        localStorage.setItem("email", this.email);
        localStorage.setItem("empDate", this.empDate);
        this.toggleDisable();
      })
      .catch(err => console.log(err));
  }
}
