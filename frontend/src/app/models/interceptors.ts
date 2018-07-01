import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {Injectable, NgModule} from "@angular/core";
import {Observable} from "rxjs/Observable";

@Injectable()
export class Interceptors implements HttpInterceptor {
  constructor(private http: HttpClient){

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(localStorage.getItem("token")) {
      console.log(`AddTokenInterceptor - ${req.url}`);
      let jsonReq: HttpRequest<any> = req.clone({
        setHeaders: {
          Authorization: `Token ${localStorage.getItem("token")}`,
        }
      });
      return next.handle(jsonReq);
    } else {
      return next.handle(req);
    }
  }

}
