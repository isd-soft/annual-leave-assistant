export class User {
  private _id: number;
  private _name: string;
  private _surname: string;
  private _email: string;
  private _password: string;
  private _empDate: Date;
  private _function_: string;
  private _department: string;
  private _availDays: string;


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get surname(): string {
    return this._surname;
  }

  set surname(value: string) {
    this._surname = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get empDate(): Date {
    return this._empDate;
  }

  set empDate(value: Date) {
    this._empDate = value;
  }

  get function_(): string {
    return this._function_;
  }

  set function_(value: string) {
    this._function_ = value;
  }

  get department(): string {
    return this._department;
  }

  set department(value: string) {
    this._department = value;
  }

  get availDays(): string {
    return this._availDays;
  }

  set availDays(value: string) {
    this._availDays = value;
  }
}
