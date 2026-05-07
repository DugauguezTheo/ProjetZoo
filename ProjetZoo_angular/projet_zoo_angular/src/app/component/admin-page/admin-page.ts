import { Component } from '@angular/core';
import { LogViewer } from '../log-viewer/log-viewer';

@Component({
  selector: 'app-admin-page',
  imports: [LogViewer],
  templateUrl: './admin-page.html',
  styleUrl: './admin-page.css',
})
export class AdminPage {}
