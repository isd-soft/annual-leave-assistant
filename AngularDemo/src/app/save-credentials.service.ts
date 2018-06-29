import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SaveCredentialsService {

  constructor() { }

  private _id: string;
  private _email: string;
  private _password: string;


  setEmail(value: string): void {
    this._email = value;
  }
  setPassword(value: string): void {
    this._password = value;
  }
  setId(value: string): void {
    this._id = value;
  }
  getEmail(): string {
    return this._email;
  }
  getPassword(): string {
    return this._password;
  }
  getId(): string {
    return this._id;
  }
}
