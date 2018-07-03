import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  private isLogged: boolean = false;
  private user: string = ' [ Annual Leave Assistant ] ';

  constructor() { }

  ngOnInit() {
    if(localStorage.getItem("token")) {
      this.isLogged = true;
      this.user = ' [ ' + localStorage.getItem("surname") + ' ' + localStorage.getItem("name") + ' ] ';
    } else {
      this.isLogged = false;
      this.user = ' [ Annual Leave Assistant ] ';
    }
  }

  logout() {
    localStorage.clear();
    this.isLogged = false;
    console.log("Logged out.");
    location.reload();
  }
}
