import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Holidays} from './models/holidays';

@Injectable({
  providedIn: 'root'
})
export class HolidaysService {

  constructor(private http: HttpClient) {
  }

  baseUrl = 'http://localhost:8088/ala/holidays';

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
