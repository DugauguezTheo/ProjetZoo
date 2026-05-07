import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-visiteur-page',
  imports: [
    RouterModule
  ],
  templateUrl: './visiteur-page.html',
  styleUrl: './visiteur-page.css',
})
export class VisiteurPage implements OnInit {
  private titleService: Title = inject(Title);
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);

  ngOnInit(): void {
    this.titleService.setTitle('Welcome "visiteur.prenom" - AJC-Zoo');
  }
}
