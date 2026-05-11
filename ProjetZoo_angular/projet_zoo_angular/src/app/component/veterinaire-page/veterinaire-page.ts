
import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { CarteLogged } from "../carte-logged/carte-logged";
import { VeterinaireService } from '../../service/veterinaire-service';
import { Veterinaire } from '../../model/veterinaire';
import { MapZoo } from '../map-zoo/map-zoo';

@Component({
  selector: 'app-veterinaire-page',
  imports: [
    RouterModule,
    CommonModule,
    CarteLogged,
    MapZoo
],
  templateUrl: './veterinaire-page.html',
  styleUrl: './veterinaire-page.css',
})
export class VeterinairePage implements OnInit {
  private titleService: Title = inject(Title);
  private authService: AuthService = inject(AuthService);


  protected veterinaireService : VeterinaireService = inject(VeterinaireService);
  protected veterinaire$ !: Observable<Veterinaire>;
  protected role !: string;

  ngOnInit(): void {
    this.veterinaire$ = this.veterinaireService.getVeterinaireConnecte();

    this.veterinaire$.subscribe(veterinaire => {
      this.titleService.setTitle(`Welcome ${veterinaire.login} - AJC-Zoo`);
    });
    this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();
  }

}

