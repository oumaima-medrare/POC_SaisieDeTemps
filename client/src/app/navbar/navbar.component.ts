// navbar.component.ts
import { Component, OnInit } from '@angular/core';
import { StorageService } from '../_services/storage.service';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
 
  constructor(private storageService: StorageService, private authService: AuthService) { }
  
  ngOnInit(): void {
   
  }

  isLoggedIn(): boolean {
    return this.storageService.isLoggedIn();
  }

  isUser(): boolean {
    return this.storageService.getRole() === "USER";
  }
  isManager(): boolean {
    return this.storageService.getRole() === "MANAGER";
  }
  isAdmin(): boolean {
    return this.storageService.getRole() === "ADMIN";
  }
  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
