

import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';


@Component({
  selector: 'app-change-manager',
  templateUrl: './change-user-manager.component.html',
  styleUrls: ['./change-user-manager.component.css']
})
export class ChangeUserManagerComponent implements OnInit {
  users: any = [];
  managers: any = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
    this.loadManagers();
  }

  loadUsers() {
 
  }

  loadManagers() {
  
  }

  saveChanges() {
   
  }
}
