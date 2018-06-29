import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GetRoleService {

  name: string;

  constructor( private http: HttpClient) { }

  getRole() {
    return this.http.get('//localhost:8080/role/' +  this.name);
  }
}
