import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {until} from "selenium-webdriver";
import elementIsSelected = until.elementIsSelected;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  isLogged(): boolean {
    if(localStorage.getItem(environment.userToken))
      return true;
    else
      return false;
  }

}
