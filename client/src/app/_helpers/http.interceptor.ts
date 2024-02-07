import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { StorageService } from '../_services/storage.service';
import { Router } from '@angular/router';


@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {


  constructor(private tokenStorage: StorageService, private router:Router){}



  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(req.headers.get('noauth')=== 'true'){
      return next.handle(req.clone());
  }

  const token = this.tokenStorage.getToken();
  console.log(token);
  req=this.addToken(req,token);
  console.log(req);

  return next.handle(req).pipe(
      catchError(
          (err:HttpErrorResponse)=>{
              console.log(err.status);
              if(err.status===401){
                  this.router.navigate(['/login']);
              }
              return throwError(err);
          }
      )

      
  );
  
}

private addToken(request: HttpRequest<any>, token: string) {
  // Remove line breaks and spaces from the token
  return request.clone({
    setHeaders: {
      Authorization: `Bearer ${JSON.parse(token)}`
    }
  });
}
}
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
];

