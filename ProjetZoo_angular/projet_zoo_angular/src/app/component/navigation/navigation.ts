// navbar.component.ts

import { Component } from '@angular/core';
import { AuthService } from '../../service/auth-service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './navigation.html',
  styleUrls: ['./navigation.css']
})
export class Navigation {

  menuOpen = false;

  constructor(public authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }
}
