import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddUserComponent } from './add-user/add-user.component';
import { UserTimesComponent } from './user-times/user-times.component';
import { ChangeUserRoleComponent } from './change-user-role/change-user-role.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'projects', component: AddProjectComponent },
  { path: 'users', component: AddUserComponent },
{ path: 'times', component: UserTimesComponent }, 
{ path: 'change_status', component: ChangeUserRoleComponent }, 
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }