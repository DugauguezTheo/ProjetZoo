import { Component } from '@angular/core';
import { CarteLogged } from "../carte-logged/carte-logged";
import { MapZoo } from '../map-zoo/map-zoo';

@Component({
  selector: 'app-admin-page',
  imports: [CarteLogged, MapZoo],
  templateUrl: './admin-page.html',
  styleUrl: './admin-page.css',
})
export class AdminPage {}

