import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../_services/user.service'; // Import your UserService
import { StorageService } from '../_services/storage.service'; // Import your TokenService

@Component({
  selector: 'app-export-pdf',
  templateUrl: './export-pdf.component.html',
  styleUrls: ['./export-pdf.component.css']
})
export class ExportPdfComponent {
  startDate: string ='';
  endDate: string ='';

  constructor(
    private http: HttpClient,
    private userService: UserService,
    private tokenService: StorageService
  ) { }

  exportPDF() {
    // Get the userId from the token
    const userId = this.tokenService.getUser().id;
    
    // Call the backend API to get the PDF using the UserService
    this.userService.exportToPdf(userId, this.startDate, this.endDate).subscribe((pdfBlob: Blob) => {
      const downloadURL = window.URL.createObjectURL(pdfBlob);
      const link = document.createElement('a');
      link.href = downloadURL;
      link.download = "times-report.pdf";
      link.click();
    });
  }
}
