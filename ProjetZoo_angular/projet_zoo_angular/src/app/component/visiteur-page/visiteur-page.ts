import { VisiteurService } from './../../service/visiteur-service';
import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { CarteLogged } from "../carte-logged/carte-logged";

@Component({
  selector: 'app-visiteur-page',
  imports: [
    RouterModule,
    CommonModule,
    CarteLogged
],
  templateUrl: './visiteur-page.html',
  styleUrl: './visiteur-page.css',
})
export class VisiteurPage implements OnInit {
  private titleService: Title = inject(Title);
  private authService: AuthService = inject(AuthService);


  protected visiteurService : VisiteurService = inject(VisiteurService);
  protected visiteur$ !: Observable<VisiteurWithAchats>;
  protected role !: string;

  ngOnInit(): void {
    this.visiteur$ = this.visiteurService.getVisiteurConnecte();

    this.visiteur$.subscribe(visiteur => {
      this.titleService.setTitle(`Welcome ${visiteur.prenom} - AJC-Zoo`);
    });
    this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();
  }

}
