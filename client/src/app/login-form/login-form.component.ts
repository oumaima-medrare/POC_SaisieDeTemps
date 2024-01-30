import {Component, OnInit} from '@angular/core';

import {UserService} from "../services/user.service";
import {UserAuthService} from "../services/user-auth.service";
import {Router} from "@angular/router";
import { NgModule } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})

export class LoginFormComponent implements OnInit {

  showText: Boolean = false;
  username: string = '';
  password: string = '';

  constructor(private userService:UserService, private userAuthService:UserAuthService, private router: Router) {

  }
  ngOnInit() {

  }

  login() {


  }
}
