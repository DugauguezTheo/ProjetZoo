import { Observable, Subject, switchMap } from 'rxjs';
import { AuthService } from './../../service/auth-service';
import { Component, inject, OnInit } from '@angular/core';
import { VisiteurService } from '../../service/visiteur-service';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { Veterinaire } from '../../model/veterinaire';
import { VeterinaireService } from '../../service/veterinaire-service';
import { Achat } from '../../model/achat';
import { Soin } from '../../model/soin';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carte-logged',
  imports: [
    CommonModule
  ],
  templateUrl: './carte-logged.html',
  styleUrl: './carte-logged.css',
})
export class CarteLogged implements OnInit {
  protected authService : AuthService = inject(AuthService);

  private refresh$: Subject<void> = new Subject<void>();

  protected visiteurService : VisiteurService = inject(VisiteurService);
  protected veterinaireService : VeterinaireService = inject(VeterinaireService);

  protected visiteur$ ?: Observable<VisiteurWithAchats>;
  protected dernierAchat$ ?: Observable<Achat>;
  protected veterinaire$ ?: Observable<Veterinaire>;
  protected dernierSoin$ ?: Observable<Soin>;
  protected role !: string;

  ngOnInit(): void {

    if (this.authService.isVisiteur()) {
      this.visiteur$ = this.visiteurService.getVisiteurConnecte();
      this.dernierAchat$ = this.visiteur$.pipe(
        switchMap(visiteur =>
          this.visiteurService.getLastAchat(visiteur.id)
        )
      );
    }
    else if (this.authService.isVeterinaire()){
      this.veterinaire$ = this.veterinaireService.getVeterinaireConnecte();
      this.veterinaire$.subscribe(veterinaire => {
        this.dernierSoin$ = this.veterinaireService.getDernierSoin(veterinaire.id);
      })
    }


    this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();

  }
}
