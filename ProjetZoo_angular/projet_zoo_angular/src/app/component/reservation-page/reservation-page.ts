import { Component, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Reservation } from '../../model/reservation';
import { ReservationService } from '../../service/reservation-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservationRequest } from '../../dto/reservation-request';
import { AuthService } from '../../service/auth-service';
import { VisiteurService } from '../../service/visiteur-service';
import { Visiteur } from '../../model/visiteur';
import { SpectacleService } from '../../service/spectacle-service';
import { Spectacle } from '../../model/spectacle';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';

@Component({
  selector: 'app-reservation-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reservation-page.html',
  styleUrl: './reservation-page.css',
})
export class ReservationPage {

  //Vérification de la date de naissance (past or present)
  futureDateValidator: ValidatorFn =
  (control: AbstractControl): ValidationErrors | null => {


    const inputDate = new Date(control.get('dateVisite')?.value);
    const today = new Date();
  
    return inputDate < today
      ? { pastDate: true }
      : null;
  };

  protected authService: AuthService = inject(AuthService);
  private titleService: Title = inject(Title);
  private router: Router = inject(Router);

  private reservationService: ReservationService = inject(ReservationService);
  private visiteurService: VisiteurService = inject(VisiteurService);
  private spectacleService: SpectacleService = inject(SpectacleService);

  protected editingReservation?: Reservation | null;
  protected reservations$!: Observable<Reservation[]>;

  protected visiteurs$!: Observable<Visiteur[]>;
  protected visiteurConnected$!: Observable<VisiteurWithAchats>;

  protected isVisiteur: boolean = false;


  protected spectacles$!: Observable<Spectacle[]>;
  protected spectaclesOfDay$!: Observable<Spectacle[]>;
  protected spectaclesMap = new Map<number, string>();

  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formReservation!: FormGroup;

  protected formVisiteurCtrl!: FormControl;
  protected formDateVisiteCtrl!: FormControl;
  protected formDateReservationCtrl!: FormControl;
  protected formPrixCtrl!: FormControl;
  protected formNbPersonneCtrl!: FormControl;
  protected formSpectaclesCtrl!: FormControl;

  protected prixVisite: number = 20;

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Reservation');
    this.visiteurs$ = this.visiteurService.findAllVisiteur();
    this.isVisiteur = this.authService.isVisiteur();
    
    this.spectacles$ = this.spectacleService.findAllSpectacles();

    
    if (this.isVisiteur) {
      //Recherche des réservations
      this.reservations$ = 
          this.refresh$.pipe(
            startWith(0),
            switchMap(() =>
              this.reservationService.findMesReservations()
            )
          )
      //Affectation de l'id dans le formulaire
      this.visiteurService.getVisiteurConnecte().subscribe(v => {
        this.formReservation.patchValue({
          visiteur: v.id
        });
      });
    }
    //Si pas visiteur, recherche toutes les réservations
    else {

      this.reservations$ = this.refresh$.pipe(
        startWith(0),
        switchMap(() =>
          this.reservationService.findAllReservations()
        )
      );
    }

    //Validators du formulaire
    this.formVisiteurCtrl = this.formBuilder.control('', Validators.required);
    this.formDateVisiteCtrl = this.formBuilder.control('', Validators.required);
    // this.formDateReservationCtrl = this.formBuilder.control('', Validators.required);
    this.formPrixCtrl = this.formBuilder.control('', Validators.required);
    this.formNbPersonneCtrl = this.formBuilder.control('', Validators.required);
    this.formSpectaclesCtrl = this.formBuilder.control([]);

    //Groupe du formulaire
    this.formReservation = this.formBuilder.group({
      dateVisite: this.formDateVisiteCtrl,
      // dateReservation: this.formDateReservationCtrl,
      prix: this.formPrixCtrl,
      nbPersonne: this.formNbPersonneCtrl,
      visiteur: this.formVisiteurCtrl,
      spectaclesIds: this.formSpectaclesCtrl
    }, { validators: [ this.futureDateValidator ] });

    //Calcul automatique du prix si on modifie le nombre de personne en tant que visiteur
    this.formNbPersonneCtrl.valueChanges.subscribe(value => {

      if (this.isVisiteur) {

        this.formPrixCtrl.setValue((value || 0) * 20);

      }

    });

    //Filtre des spectacles du jours choisis dans la réservation
    this.spectaclesOfDay$ = this.formDateVisiteCtrl.valueChanges.pipe(
      startWith(this.formDateVisiteCtrl.value),
      switchMap(date => {
        if (!date) return [];

      const formattedDate = date;

        return this.spectacleService.findSpectaclesBetween(formattedDate, formattedDate);
      })
    );

    //Récupération des titres des spectacles
    this.spectacles$.subscribe(data => {
      data.forEach(s => {
        this.spectaclesMap.set(s.id!, s.titre);
      });
    });
  }

  

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdateReservation() {
    //Si ivisiteur, calcul du prix automatique
    if (this.isVisiteur) {

      const nbPersonne = this.formReservation.value.nbPersonne;

      this.formReservation.patchValue({
        prix: nbPersonne * this.prixVisite
      });
    }
    
    const reservation: ReservationRequest = {
      visiteurId: this.formVisiteurCtrl.value,
      dateVisite: this.formDateVisiteCtrl.value,
      dateReservation: new Date(), //Ability to change date ?
      prix: this.formPrixCtrl.value,
      nbPersonne: this.formNbPersonneCtrl.value,
      spectaclesIds: this.formSpectaclesCtrl.value
    };

    
    if (this.editingReservation) {
      this.reservationService.updateReservation(this.editingReservation.id, reservation).subscribe(() => {
        this.editingReservation = null;
        this.formReservation.reset();
        this.reload();
      });
    }
    else {
      this.reservationService.addReservation(reservation).subscribe(() => {
        this.formReservation.reset();
        this.reload();
      })
    }
  }

  public editerReservation(reservation: Reservation) {
    this.editingReservation = reservation;

    this.formVisiteurCtrl.setValue(reservation.visiteurId);
    this.formDateVisiteCtrl.setValue(reservation.dateVisite);
    // this.formDateReservationCtrl.setValue(new Date());
    this.formPrixCtrl.setValue(reservation.prix);
    this.formNbPersonneCtrl.setValue(reservation.nbPersonne);
    this.formSpectaclesCtrl.setValue(reservation.spectaclesIds);

    this.reload();
  }

  // public compareEnclos(e1: Enclos, e2: Enclos): boolean {
  //   return e1 && e2 ? e1.numero === e2.numero : e1 === e2;
  // }

  public deleteReservation(reservation: Reservation) {
    this.reservationService.deleteReservation(reservation.id).subscribe(() => {
      this.reload();
    });
  }

  // public chargerListeEspeces(numero: number): void {
  //   this.especes$ = this.reservationService.getAllEspecesInReservation(numero).pipe(
  //     map(reservation => (reservation.especes ?? []) as string[])
  //   );
  // }

  getSpectacleTitles(ids: number[] | undefined): string {
    if (!ids || !this.spectacles$) return '';

    return ids
      .map(id => this.spectaclesMap.get(id))
      .filter(Boolean)
      .join(', ');
  }

}
