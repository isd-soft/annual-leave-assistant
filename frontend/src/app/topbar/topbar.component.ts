import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  isLogged: boolean = false;

  constructor() { }

  ngOnInit() {
    if(localStorage.getItem("token"))
      this.isLogged = true;
  }

  logout() {
    localStorage.clear();
    this.isLogged = false;
    console.log("Logged out.");
    location.reload();
  }
}
