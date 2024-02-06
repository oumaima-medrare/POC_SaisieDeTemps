import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from '../_services/storage.service';


@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(private token: StorageService){}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.token.getToken();
    if(token != null){
    req = req.clone({
      setHeaders:{
        'Content-Type': 'application/json; charset=utf-8',
        'Accept':'application/json',
        'Authorization':`Bearer ${token}`
      },
      withCredentials: true // Include credentials
    });
  }
  console.log(req);
    return next.handle(req);
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
];

