import { Component } from '@angular/core';
import { UserService } from '../_services/user.service';
import { StorageService } from '../_services/storage.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {
  user: any = {};
  users: any[] = [];

  constructor(private userService: UserService, private token :StorageService) { }
  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsersOfManager(this.token.getUser().id).subscribe(
      (data: any) => {
        this.users = data;
      },
      (error) => {
        console.error('Error loading users:', error);
       
      }
    );
  }
  onSubmit() {
    this.user.managerId= this.token.getUser().id;
    this.user.role= "USER";
    this.userService.createUser(this.user).subscribe(
      (data) => {
        console.log('User created successfully:', data);
        window.location.reload();
        // Optionally, redirect to another page or show a success message
      },
      (error) => {
        console.error('Error creating user:', error);
        // Handle error, show error message, etc.
      }
      
    );
  }

}
