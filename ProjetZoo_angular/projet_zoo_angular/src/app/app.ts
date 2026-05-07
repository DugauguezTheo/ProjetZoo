import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navigation } from './component/navigation/navigation';
import { LogViewer } from "./component/log-viewer/log-viewer";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navigation],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App {
}
