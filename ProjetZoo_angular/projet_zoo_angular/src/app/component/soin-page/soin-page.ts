import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { SoinService } from '../../service/soin-service';
import { VeterinaireService } from '../../service/veterinaire-service';
import { AnimalService } from '../../service/animal-service';
import { map, Observable, startWith, Subject, switchMap } from 'rxjs';
import { AuthService } from '../../service/auth-service';
import { Soin } from '../../model/soin';
import { Animal } from '../../model/animal';
import { Veterinaire } from '../../model/veterinaire';

@Component({
  selector: 'app-soin-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './soin-page.html',
  styleUrl: './soin-page.css',
})
export class SoinPage implements OnInit {

  private titleService: Title = inject(Title);
  protected authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);

  protected veterinaireService: VeterinaireService = inject(VeterinaireService);
  protected animalService: AnimalService = inject(AnimalService)
  protected soinService: SoinService = inject(SoinService);

  protected editingSoin?: Soin | null;
  protected soin$!: Observable<Soin[]>;
  protected veterinaire$!: Observable<Veterinaire[]>;
  protected animal$!: Observable<Animal[]>;
  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formSoin!: FormGroup;
  protected formDateSoinCtrl!: FormControl;
  protected formDescriptionCtrl!: FormControl;
  protected formVeterinaireCtrl!: FormControl;
  protected formAnimalCtrl!: FormControl;

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Soin');
    this.veterinaire$ = this.veterinaireService.getAllVeterinaires();
    this.animal$ = this.animalService.findAllAnimals();

    this.soin$ = this.soinService.findAllSoin();



    this.formDateSoinCtrl = new FormControl("", Validators.required);
    this.formDescriptionCtrl = new FormControl("", Validators.required);
    this.formVeterinaireCtrl = new FormControl(null, Validators.required);
    this.formAnimalCtrl = new FormControl(null, Validators.required);

    this.formSoin = this.formBuilder.group({
      dateSoin: this.formDateSoinCtrl,
      description: this.formDescriptionCtrl,
      idVeterinaire: this.formVeterinaireCtrl,
      animalId: this.formAnimalCtrl
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdateSoin() {
    const soin: Soin = {
      dateSoin: this.formDateSoinCtrl.value,
      description: this.formDescriptionCtrl.value,
      veterinaireId: this.formVeterinaireCtrl.value,
      animalId: this.formAnimalCtrl.value
    };

    if (this.editingSoin) {
      this.soinService.updateSoin(soin).subscribe(() => {
        this.editingSoin = null;
        this.formSoin.reset();
        this.reload();
      });
    }
    else {
      this.soinService.addSoin(soin).subscribe(() => {
        this.formSoin.reset();
        this.reload();
      })
    }
  }

  public editerSoin(soin: Soin) {
    this.editingSoin = soin;
    this.formDateSoinCtrl.setValue(soin.dateSoin);
    this.formDescriptionCtrl.setValue(soin.description);
    this.formVeterinaireCtrl.setValue(soin.veterinaireId);
    this.formAnimalCtrl.setValue(soin.animalId);
    this.reload();
  }

  public deleteSoin(soin: Soin) {
    this.soinService.deleteSoin(soin.id).subscribe(() => {
      this.reload();
    });
  }
}
