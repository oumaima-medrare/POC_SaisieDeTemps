import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { NavbarComponent } from './navbar/navbar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { AddProjectComponent } from './add-project/add-project.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AddUserComponent } from './add-user/add-user.component';
import { UserTimesComponent } from './user-times/user-times.component';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ChangeUserRoleComponent } from './change-user-role/change-user-role.component';
import { ChangeUserManagerComponent } from './change-user-manager/change-user-manager.component';
import { ExportPdfComponent } from './export-pdf/export-pdf.component';

import { UserService } from './_services/user.service';
import { ManageTimeComponent } from './manage-time/manage-time.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    AddProjectComponent,
    AddUserComponent,
    UserTimesComponent,
    ChangeUserRoleComponent,
    ChangeUserManagerComponent,
    ExportPdfComponent,
    ManageTimeComponent,
   
  ],
  imports: [
    MatSnackBarModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatFormFieldModule,
    BrowserModule,
    ReactiveFormsModule, 
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
