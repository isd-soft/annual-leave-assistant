import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';
import {toPromise} from 'rxjs-compat/operator/toPromise';
import { ListUserComponent } from '../list-user/list-user.component';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private id: string;
  private surname: string;
  private name: string;
  private email: string;
  private password: string;
  private role: string;
  private empDate: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.completeFields();
    //console.log(this.token);
  }

  disableTextbox =  true;

  completeFields(){
    this.http.get(environment.rootUrl + '/ala/users/' + localStorage.getItem('updateUserId'), {observe: 'response'}).toPromise()
      .then( res => {
        this.id = res.body['id'];
        this.surname = res.body['surname'];
        this.name = res.body['name'];
        this.email = res.body['email'];
        this.password = "";
        this.empDate = res.body['empDate'];
        this.role = res.body['role'];
      }).catch(err => console.log(err.body['message']));
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
        this.toggleDisable();
      })
      .catch(err => console.log(err));
  }
}
