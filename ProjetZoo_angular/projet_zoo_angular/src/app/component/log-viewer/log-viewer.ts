// log-viewer.ts
import { Component } from '@angular/core';
import { LogService } from '../../service/log-service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-log-viewer',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './log-viewer.html',
  styleUrl: './log-viewer.css',
})
export class LogViewer {
  logs$;

  constructor(private logService: LogService) {
    this.logs$ = this.logService.logs$;
  }
}