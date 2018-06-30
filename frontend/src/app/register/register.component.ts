import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { FormsModule, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  surname: string;
  name: string;
  email: string;
  password: string;
  empDate: string;

  register(){
    console.log(this.empDate);

    this.http.post(environment.rootUrl + "/register",
        { "surname": this.surname,
          "name": this.name,
          "email": this.email,
          "password": this.password,
          "empDate": this.empDate
        },
        {observe: 'response'})
          .toPromise()
                .then(res => {
                    if(res.status == 201){
                      window.alert(res.body["message"]);
                      this.router.navigate(['/login']);
                    } else {
                      window.alert(res.body["message"]);
                      location.reload();
                    }
                 })
                .catch(err => {
                        console.log('error' + err);
                        window.alert("User with this email already exists!");
                        });
  }
 }

