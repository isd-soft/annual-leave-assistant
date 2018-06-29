import {User} from './user';

export class LeaveRequest {

  private requestType: LeaveRequestType;
  private startDay: Date;
  private  endDay: Date;
  private days: number;
  private user: User;
}
