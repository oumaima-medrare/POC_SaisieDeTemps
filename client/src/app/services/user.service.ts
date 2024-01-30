import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = "http://localhost:3000/api/v1/auth";
  requestHeader = new HttpHeaders(
    { "noauth": "true" }
  );
  constructor(private httpclient: HttpClient, private userAuthService:UserAuthService, private router: Router) {
  }
  public login(loginData: any) {
    return this.httpclient.post(this.PATH_OF_API + "/authentificate", loginData, { headers: this.requestHeader })
  }

  public logout() {
    this.userAuthService.clear();
    // this.router.navigate(['/landing-page']);//TODO
  }
}
