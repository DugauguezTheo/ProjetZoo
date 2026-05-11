import { AdminPage } from './../admin-page/admin-page';
import { AnimalService } from './../../service/animal-service';
import { map, Observable, Subject, switchMap } from 'rxjs';
import { AuthService } from './../../service/auth-service';
import { Component, inject, OnInit } from '@angular/core';
import { VisiteurService } from '../../service/visiteur-service';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { Veterinaire } from '../../model/veterinaire';
import { VeterinaireService } from '../../service/veterinaire-service';
import { Achat } from '../../model/achat';
import { Soin } from '../../model/soin';
import { CommonModule } from '@angular/common';
import { ReservationService } from '../../service/reservation-service';
import { Reservation } from '../../model/reservation';
import { EspeceService } from '../../service/espece-service';
import { AchatService } from '../../service/achat-service';

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

  protected visiteurService : VisiteurService = inject(VisiteurService);
  protected veterinaireService : VeterinaireService = inject(VeterinaireService);
  protected reservationService : ReservationService = inject(ReservationService);
  protected animalService : AnimalService = inject(AnimalService);
  protected especeService : EspeceService = inject(EspeceService);
  protected achatService : AchatService = inject(AchatService);

  protected visiteur$ ?: Observable<VisiteurWithAchats>;
  protected dernierAchat$ ?: Observable<Achat>;
  protected derniereVisite$ ?: Observable<Reservation | undefined>;
  protected veterinaire$ ?: Observable<Veterinaire>;
  protected dernierSoin$ ?: Observable<Soin>;
  protected role !: string;

  // Admin
  protected nombreAnimaux$ ?: Observable<number>;
  protected nombreEspeces$ ?: Observable<number>;
  protected nombreVisites$ ?: Observable<number>;
  protected recettes$ ?: Observable<number>;
  protected nombreEmployes$ ?: Observable<number>;

  ngOnInit(): void {

    if (this.authService.isVisiteur()) {
      this.visiteur$ = this.visiteurService.getVisiteurConnecte();
      this.visiteur$.pipe(
        switchMap(visiteur =>  this.dernierAchat$ = this.visiteurService.getLastAchat(visiteur.id))
      );

      this.derniereVisite$ = this.visiteur$.pipe(
        switchMap(visiteur => this.reservationService.findAllByVisiteurId(visiteur.id)),
        map(reservations =>
          reservations.sort((a, b) => {
            const diffDate = new Date(b.dateReservation).getTime() - new Date(a.dateReservation).getTime();

            if (diffDate !== 0) {
              return diffDate;
            }

            return (b.id ?? 0) - (a.id ?? 0);
          })
          .filter(r => new Date(r.dateReservation).getTime() <= new Date().getTime())
          [0]
        )
      );

      this.dernierAchat$ = this.visiteur$.pipe(
        switchMap(visiteur =>
          this.visiteurService.getLastAchat(visiteur.id)
        )
      );

      this.nombreVisites$ = this.visiteur$.pipe(
        switchMap(visiteur => this.reservationService.findAllByVisiteurId(visiteur.id)),
        map(reservations => reservations.filter(r => new Date(r.dateReservation).getTime() <= new Date().getTime()).length
        )
      );

    }
    else if (this.authService.isVeterinaire()){
      this.veterinaire$ = this.veterinaireService.getVeterinaireConnecte();
      this.veterinaire$.subscribe(veterinaire => {
        this.dernierSoin$ = this.veterinaireService.getDernierSoin(veterinaire.id);
      })
    }

    else if (this.authService.isAdmin()) {
      this.nombreAnimaux$ = this.animalService.findAllAnimals().pipe(
        map(animaux => animaux.length)
      );
      this.nombreEspeces$ = this.especeService.findAllEspeces().pipe(
        map(especes => especes.length)
      );

      this.nombreVisites$ = this.reservationService.findAllReservations().pipe(
        map(reservations => reservations
          .filter(r =>
            new Date(r.dateReservation).getTime() <= new Date().getTime()
            && new Date(r.dateReservation).getFullYear() >= new Date().getFullYear()
          )
          .length
      )
      );
      this.recettes$ = this.achatService.findAllAchats().pipe(
        map(achats => achats.reduce(
          (total, achat) => total + (achat.quantite * achat.prixUnitaireATM),0
        ))
      );
      this.nombreEmployes$ = this.veterinaireService.getAllVeterinaires().pipe(
        map(veterinaires => veterinaires.length)
      );
    }


    this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();

  }
}
