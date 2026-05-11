import { Component, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Spectacle } from '../../model/spectacle';
import { SpectacleService } from '../../service/spectacle-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Enclos } from '../../model/enclos';
import { EnclosService } from '../../service/enclos-service';
import { SpectacleRequest } from '../../dto/spectacle-request';
import { AuthService } from '../../service/auth-service';
import { CarteLogged } from '../carte-logged/carte-logged';

@Component({
  selector: 'app-spectacle-page',
  imports: [CommonModule, ReactiveFormsModule, CarteLogged],
  templateUrl: './spectacle-page.html',
  styleUrl: './spectacle-page.css',
})
export class SpectaclePage {

  protected authService: AuthService = inject(AuthService);
  private titleService: Title = inject(Title);
  private router: Router = inject(Router);

  private spectacleService: SpectacleService = inject(SpectacleService);
  private enclosService: EnclosService = inject(EnclosService);
  // private reservationService: ReservationService = inject(ReservationService);

  protected editingSpectacle?: Spectacle | null;
  protected spectacles$!: Observable<Spectacle[]>;

  protected titresSpectacles: string[] = [
    'Le Réveil des Lions',
    'Danse des Dauphins',
    'Vol des Rapaces',
    'Parade des Éléphants',
    'Le Royaume des Tigres',
    'Aventure en Jungle',
    'Les Singes Acrobates',
    'Nuit des Prédateurs',
    'Le Monde Marin',
    'Safari Tropical'
  ];

  protected enclos!: Observable<Enclos[]>;

  // protected reservations$!: Observable<Reservation[]>;

  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formSpectacle!: FormGroup;
  
  protected formDateCtrl!: FormControl;
  protected formHeureCtrl!: FormControl;
  protected formDureeCtrl!: FormControl;
  protected formEnclosCtrl!: FormControl;
  protected formTitreCtrl!: FormControl;

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Spectacle');
    this.enclos = this.enclosService.findAllEncloss();

    // this.reservations$ = this.reservationService.findAllEspeces();

    this.spectacles$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.spectacleService.findAllSpectacles())
    );

    this.formDateCtrl = this.formBuilder.control('', Validators.required);
    this.formHeureCtrl = this.formBuilder.control('', Validators.required);
    this.formDureeCtrl = this.formBuilder.control('', Validators.required);
    this.formEnclosCtrl = this.formBuilder.control('', Validators.required);
    this.formTitreCtrl = this.formBuilder.control('', Validators.required);

    this.formSpectacle = this.formBuilder.group({
      date: this.formDateCtrl,
      heure: this.formHeureCtrl,
      duree: this.formDureeCtrl,
      enclos: this.formEnclosCtrl,
      titre: this.formTitreCtrl
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdateSpectacle() {
    const spectacle: SpectacleRequest = {
      dateDebut: this.formDateCtrl.value,
      heureDebut: this.formHeureCtrl.value,
      duree: this.formDureeCtrl.value,
      enclosId: this.formEnclosCtrl.value,
      titre: this.formTitreCtrl.value
    };
    
    if (this.editingSpectacle) {
      this.spectacleService.updateSpectacle(this.editingSpectacle.id, spectacle).subscribe(() => {
        this.editingSpectacle = null;
        this.formSpectacle.reset();
        this.reload();
      });
    }
    else {
      this.spectacleService.addSpectacle(spectacle).subscribe(() => {
        this.formSpectacle.reset();
        this.reload();
      })
    }
  }

  public editerSpectacle(spectacle: Spectacle) {
    this.editingSpectacle = spectacle;

    this.formDateCtrl.setValue(spectacle.dateDebut);
    this.formHeureCtrl.setValue(spectacle.heureDebut);
    this.formDureeCtrl.setValue(spectacle.duree);
    this.formEnclosCtrl.setValue(spectacle.enclosId);
    this.formTitreCtrl.setValue(spectacle.titre);
    this.reload();
  }

  // public compareEnclos(e1: Enclos, e2: Enclos): boolean {
  //   return e1 && e2 ? e1.numero === e2.numero : e1 === e2;
  // }

  public deleteSpectacle(spectacle: Spectacle) {
    this.spectacleService.deleteSpectacle(spectacle.id).subscribe(() => {
      this.reload();
    });
  }

  // public chargerListeEspeces(numero: number): void {
  //   this.especes$ = this.spectacleService.getAllEspecesInSpectacle(numero).pipe(
  //     map(spectacle => (spectacle.especes ?? []) as string[])
  //   );
  // }



}
