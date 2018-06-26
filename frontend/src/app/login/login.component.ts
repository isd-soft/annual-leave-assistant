import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { environment } from '../../environments/environment'
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  email: string;
  password: string;
  result: string;

  login(){
    console.log("Welcome!");
    console.log('Email: ' + this.email);
    console.log('Password: ' + this.password);

    this.http.post(environment.rootUrl + "/login", { "email": this.email, "password": this.password })
    .toPromise()
            .then(res =>
                  if(res.token != null){
                    console.log(res.token);
                    this.result = res.token;
                    console.log("-----------------------------");
                    console.log(this.result);
                  } else {
                    console.log(res.message);
                    this.result = res.message;
                    console.log("-----------------------------");
                    console.log(this.result);
                  })
            .catch(err => console.log('error' + err.message);

  }
}
