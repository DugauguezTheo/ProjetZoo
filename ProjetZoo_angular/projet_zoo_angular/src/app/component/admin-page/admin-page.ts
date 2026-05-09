import { Component } from '@angular/core';
import { CarteLogged } from "../carte-logged/carte-logged";

@Component({
  selector: 'app-admin-page',
  imports: [CarteLogged],
  templateUrl: './admin-page.html',
  styleUrl: './admin-page.css',
})
export class AdminPage {}
