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
  userRequest : any;

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
    console.log('id'+this.token.getUser().id);
    this.userRequest = {
      username: this.user.username,
      firstname: this.user.firstname,
      lastname: this.user.lastname,
      email: this.user.email,
      password: this.user.password,
      role: "USER",
      managerId: this.token.getUser().id
    };
   


    console.log(this.userRequest);
    this.userService.createUser(this.userRequest).subscribe(
      (data) => {
        console.log('User created successfully:', data);
        // window.location.reload();
        // Optionally, redirect to another page or show a success message
      },
      (error) => {
        console.error('Error creating user:', error);
        // Handle error, show error message, etc.
      }
      
    );
  }

}
