import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AnimalService } from '../../service/animal-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Animal } from '../../model/animal';
import { EspeceService } from '../../service/espece-service';
import { Espece } from '../../model/espece';
import { Enclos } from '../../model/enclos';
import { EnclosService } from '../../service/enclos-service';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-animal-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './animal-page.html',
  styleUrl: './animal-page.css',
})
export class AnimalPage implements OnInit {

  private titleService: Title = inject(Title);
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);

  private animalService: AnimalService = inject(AnimalService);
  protected enclosService: EnclosService = inject(EnclosService);
  protected especeService: EspeceService = inject(EspeceService);

  protected editingAnimal ?: Animal | null;
  protected animals$!: Observable<Animal[]>;
  protected enclos!: Observable<Enclos[]>;
  protected especes!: Observable<Espece[]>;
  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formAnimal! : FormGroup;
  protected formPrenomCtrl! : FormControl;
  protected formDateNaissanceCtrl! : FormControl;
  protected formEnclosCtrl! : FormControl;
  protected formEspeceCtrl! : FormControl;
  protected formSoinCtrl! : FormControl;

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Animaux');
    
    this.animals$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.animalService.findAllAnimals())
    );

    this.enclos = this.enclosService.findAllEncloss();
    this.especes = this.especeService.findAllEspeces();

    this.formPrenomCtrl = new FormControl("", Validators.required);
    this.formDateNaissanceCtrl = new FormControl("", Validators.required);
    this.formEnclosCtrl = new FormControl(null, Validators.required);
    this.formEspeceCtrl = new FormControl(null, Validators.required);
  
    this.formAnimal = this.formBuilder.group({
      prenom: this.formPrenomCtrl,
      dateNaissance: this.formDateNaissanceCtrl,
      enclos: this.formEnclosCtrl,
      espece: this.formEspeceCtrl
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdateAnimal() {
    const animal: Animal = {
      prenom: this.formPrenomCtrl.value,
      dateNaissance: this.formDateNaissanceCtrl.value,
      enclos: this.formEnclosCtrl.value,
      espece: this.formEspeceCtrl.value,
      id: this.editingAnimal?.id
    };

    if(this.editingAnimal) {
      this.animalService.updateAnimal(animal).subscribe(() => {
        this.editingAnimal = null;
        this.formAnimal.reset();
        this.reload();
      });
    }
    else {
      this.animalService.addAnimal(animal).subscribe(() => {
        this.formAnimal.reset();
        this.reload();
      })
    }
  }

  public editerAnimal(animal : Animal) {
    this.editingAnimal = animal;
    this.formPrenomCtrl.setValue(animal.prenom);
    this.formDateNaissanceCtrl.setValue(animal.dateNaissance);
    this.formEnclosCtrl.setValue(animal.enclos);
    this.formEspeceCtrl.setValue(animal.espece);
    this.reload();
  }

  public compareEnclos(e1: Enclos, e2: Enclos): boolean {
    return e1 && e2 ? e1.numero === e2.numero : e1 === e2;
  }

  public deleteAnimal(animal : Animal) {
    this.animalService.deleteAnimal(animal.id).subscribe(() => {
      this.reload();
    });
  }
}
