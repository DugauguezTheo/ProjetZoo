import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Connexion } from "./component/connexion/connexion";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Connexion],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
