import { Component, OnInit } from '@angular/core';
import { FormsModule, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import {stringify} from "querystring";
//import 'rxjs/add/operator/map';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  email: string;
  password: string;
  result: any;

  year: number;
  month: number;
  day: number;


  decodeToken(token: string): any{
    let jwtData = token.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    return JSON.parse(decodedJwtJsonData);
  }


  login(){
    // console.log("Welcome!");
    // console.log('Email: ' + this.email);
    // console.log('Password: ' + this.password);
    this.http.post(environment.rootUrl + "/login", { "email": this.email, "password": this.password }, { observe: "response" })
      .toPromise()
      .then(res => {
        if(res.status == 200) {
          console.log(res.body["token"]);
          this.result = this.decodeToken(res.body["token"]);
          localStorage.setItem("token", res.body["token"]);
          localStorage.setItem("id", this.result.sub);
          localStorage.setItem("surname", this.result.surname);
          localStorage.setItem("name", this.result.name);
          localStorage.setItem("email", this.result.email);
          localStorage.setItem("role", this.result.role);

          let dateString = this.result.empDate.year + "-" + this.result.empDate.monthValue + "-" + this.result.empDate.dayOfMonth;
          localStorage.setItem("empDate", dateString);

          location.reload();
          this.router.navigate(['/']);
        } else {
          console.log(res.body["message"]);
          window.alert(res.body["message"]);
          location.reload();
        }
      })
      .catch(err => console.log('error' + err));
  }


}
