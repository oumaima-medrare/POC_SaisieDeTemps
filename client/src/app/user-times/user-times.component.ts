import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

interface Project {
  id: number;
  title: string;

}

interface Time {
  project: string;
  startTime: Date;
  endTime: Date;
}

@Component({
  selector: 'app-user-times',
  templateUrl: './user-times.component.html',
  styleUrls: ['./user-times.component.css']
})
export class UserTimesComponent implements OnInit {
  selectedProject: number = 0;
  startTime: string = '';
  endTime: string = '';
  projects: Project[] = [];
  times: Time[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadProjects();
    this.loadTimes();
  }

  loadProjects(): void {
    this.userService.getProjectsOfManager().subscribe((projects: any) => {
      this.projects = projects;
    });
  }

  loadTimes(): void {
    this.userService.getCurrentUserTime().subscribe((times: any) => { 
      this.times = times;
      console.log(this.times)
    });
  }

  createNewTime(): void {
    if (this.selectedProject === 0 || !this.startTime || !this.endTime) {
      // Validation failed
      return;
    }

    const newTimeRequest = {
      dateStart: new Date(this.startTime).toISOString(),
      dateEnd: new Date(this.endTime).toISOString(),
      projectId: this.selectedProject
    };

    this.userService.createTime(newTimeRequest).subscribe((response: any) => {
      const newTime: Time = {
        project: this.projects.find(project => project.id === this.selectedProject)?.title|| '',
        startTime: new Date(this.startTime),
        endTime: new Date(this.endTime)
      };
      this.times.push(newTime);

      // Reset form fields
      this.selectedProject = 0;
      this.startTime = '';
      this.endTime = '';
    });
  }
}
