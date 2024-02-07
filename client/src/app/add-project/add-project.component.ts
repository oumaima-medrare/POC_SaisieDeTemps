import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '../_services/user.service';

import { MatSnackBar } from '@angular/material/snack-bar';
import { StorageService } from '../_services/storage.service';


@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent {

  createProjectForm: FormGroup = new FormGroup({
    title: new FormControl(''),
    description: new FormControl(''),
  });

  constructor(private userService: UserService, private token: StorageService, private _snackBar: MatSnackBar){

  }

  createProject(){
    let userID = this.token.getUser().id;
    let username = this.token.getUser().username;
    const userObject = {userID}
    let request = {
      "title": this.createProjectForm.value.title,
      "description": this.createProjectForm.value.description,
  
    }

    console.log(request);
    
    this.userService.createProject( request).subscribe((response:any) => {
      this.openSnackBar(response);
    },
    (error)=>{
      console.log(error);
      //this.router.navigate(['/landing-page']);
      //add error message here
    })
  }

  openSnackBar(response: any){
    if (response){
      this._snackBar.open("Project successfully created !", "Nice !");
      this.createProjectForm.reset();
    }
  }

}