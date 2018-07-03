///<reference path="../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  currentUrl: string;
  isLogged = false;
  constructor(private  router: Router) {
    router.events.subscribe((_: NavigationEnd) => this.currentUrl = _.url);
  }

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
