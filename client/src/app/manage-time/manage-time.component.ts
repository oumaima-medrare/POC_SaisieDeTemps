// manage-time.component.ts
import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-manage-time',
  templateUrl: './manage-time.component.html',
  styleUrls: ['./manage-time.component.css']
})
export class ManageTimeComponent implements OnInit {
  times: any[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllTimes();
  }

  getAllTimes() {
    this.userService.getAllTimes().subscribe(
      (response: any) => {
        this.times = response;
        console.log(response);
      },
      (error: any) => {
        console.error('Error occurred while fetching times:', error);
      }
    );
  }

  updateTime(time: any) {
    this.userService.updateTime(time.id, time).subscribe(
      () => {
        console.log('Time updated successfully');
      },
      (error: any) => {
        console.error('Error occurred while updating time:', error);
      }
    );
  }

  deleteTime(timeId: number) {
    this.userService.deleteTime(timeId).subscribe(
      () => {
        console.log('Time deleted successfully');
        // Remove the deleted time entry from the array
        this.times = this.times.filter(time => time.id !== timeId);
      },
      (error: any) => {
        console.error('Error occurred while deleting time:', error);
      }
    );
  }
}
