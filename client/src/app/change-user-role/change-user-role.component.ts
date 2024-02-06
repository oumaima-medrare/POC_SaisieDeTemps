import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';


@Component({
  selector: 'app-change-user-role',
  templateUrl: './change-user-role.component.html',
  styleUrls: ['./change-user-role.component.css']
})
export class ChangeUserRoleComponent implements OnInit {
  users: any[] = [];
  selectedUserId: number = 0;
  selectedRole: string = '';
  roles: string[] = ['ADMIN', 'MANAGER', 'USER'];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe((users: any) => {
      this.users = users;
    });
  }

  changeUserRole(): void {
    if (this.selectedUserId === 0 || !this.selectedRole) {
      alert('Please select a user and a role.');
      return;
    }
    const request = {
      userId: this.selectedUserId,
      role: this.selectedRole
    };
    this.userService.changeUserRole(request).subscribe(
      (response: any) => {
        alert('User role changed successfully.');
      },
      (error: any) => {
        alert('An error occurred while changing user role.');
        console.error(error);
      }
    );
  }
}
