import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PATH_OF_API = "/api/v1";

  constructor(private httpclient: HttpClient, private router: Router) {

  }

  public getUsersOfManager(manager_id: number){
    return this.httpclient.get(this.PATH_OF_API + "/users/manager/" + manager_id)
  }
  
  public getProjectsOfManager(){
    return this.httpclient.get(this.PATH_OF_API + "/projects")
  }

  public createTime(request: object){
    return this.httpclient.post(this.PATH_OF_API + "/times/", request);
  }

  public getTimesOfUser(user_id: string | number | null){
    return this.httpclient.get(this.PATH_OF_API + "/times/" + user_id)
  }

  public getCurrentUserTime()
  {
    return  this.httpclient.get(this.PATH_OF_API + "/times/current" );
  }

  public exportToPdf(userID: number, date: string){
    return this.httpclient.get(this.PATH_OF_API + "/times/" + userID + "/date/" + date + "/export/pdf", { responseType: 'blob' })
  }

  public createUser( request: object){
    return this.httpclient.post(this.PATH_OF_API + "/users/" , request);
  }

  public createProject( request: object){
    return this.httpclient.post(this.PATH_OF_API + "/projects/" , request);
  }
}