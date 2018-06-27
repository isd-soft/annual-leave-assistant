import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment'


@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  private isLogged: boolean = false;

  constructor() { }

  ngOnInit() {
    if(localStorage.getItem(environment.userToken))
      this.isLogged = true;
  }

  logout() {
    localStorage.clear();
    this.isLogged = false;
    console.log("Logged out.");
    location.reload();
  }
}
