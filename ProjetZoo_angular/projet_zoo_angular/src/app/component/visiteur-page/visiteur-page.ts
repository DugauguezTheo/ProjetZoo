import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../service/auth-service';
import { Navigation } from "../navigation/navigation";

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

  protected login !: string;
  protected role !: string;

  ngOnInit(): void {
    this.titleService.setTitle('Welcome "visiteur.prenom" - AJC-Zoo');
    this.login = this.authService.login;
    this.role = this.authService.role;
  }
}
