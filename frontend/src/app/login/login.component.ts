import { Component, OnInit } from '@angular/core';
import { FormsModule, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
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
  result: string;

  login(){
    // console.log("Welcome!");
    // console.log('Email: ' + this.email);
    // console.log('Password: ' + this.password);
    this.http.post(environment.rootUrl + "/login", { "email": this.email, "password": this.password }, { observe: "response" })
      .toPromise()
      .then(res => {
        if(res.status == 200){
          console.log(res.body["token"]);
          this.result = res.body["token"];
          localStorage.setItem(environment.userToken, JSON.stringify({token: this.result}));
          // store other user info
          location.reload();
          this.router.navigate(['/']);
        } else {
          console.log(res.body["message"]);
          this.result = res.body["message"];
          window.alert(this.result);
          location.reload();
        }
      })
      .catch(err => console.log('error' + err));
  }
}
