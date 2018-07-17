import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Holidays} from './models/holidays';
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class HolidaysService {

  constructor(private http: HttpClient) {
  }

  baseUrl = environment.rootUrl +'/ala/holidays';

  getHolidays() {
    return this.http.get<Holidays[]>(this.baseUrl);
  }

  getHolidayById(id: number) {
    return this.http.get<Holidays>(this.baseUrl + '/' + id);
  }

  deleteHoliday(id: number) {
    return this.http.delete(this.baseUrl + '/delete/' + id);
  }

  updateHoliday(holiday: Holidays) {
    return this.http.put(this.baseUrl + '/update/' + holiday.id, holiday);
  }

}
