import {DatePipe} from '@angular/common';
import {Role} from './role';

export class User {
   name: string;
   surname: string;
   email: string;
   password: string;
   role: Role;
   employment_date: Date;


}
