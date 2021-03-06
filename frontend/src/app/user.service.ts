import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user';
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  private baseUrl = environment.rootUrl +'/ala/users';

  getUsers() {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUserById(id: number) {
    return this.http.get<User[]>(this.baseUrl + '/' + id);
  }

  createUser(user: User) {
    return this.http.post(this.baseUrl + '/create', user);
  }

  updateUser (user: User) {
    return this.http.put(this.baseUrl + '/update/' + user.id, user);
  }

  deleteUser(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  deleteAllUsers() {
    return this.http.delete(this.baseUrl + '/delete');
  }
}
