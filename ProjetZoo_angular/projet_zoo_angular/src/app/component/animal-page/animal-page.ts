import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router, RouterLink } from '@angular/router';
import { AnimalService } from '../../service/animal-service';
import { map, Observable, startWith, Subject, switchMap } from 'rxjs';
import { Animal } from '../../model/animal';
import { EspeceService } from '../../service/espece-service';
import { Espece } from '../../model/espece';
import { Enclos } from '../../model/enclos';
import { EnclosService } from '../../service/enclos-service';
import { AuthService } from '../../service/auth-service';
import { CarteLogged } from "../carte-logged/carte-logged";

@Component({
  selector: 'app-animal-page',
  imports: [CommonModule, ReactiveFormsModule, CarteLogged, RouterLink],
  templateUrl: './animal-page.html',
  styleUrl: './animal-page.css',
})
export class AnimalPage implements OnInit {

  private titleService: Title = inject(Title);
  protected authService: AuthService = inject(AuthService);

  private animalService: AnimalService = inject(AnimalService);
  protected enclosService: EnclosService = inject(EnclosService);
  protected especeService: EspeceService = inject(EspeceService);

  protected editingAnimal ?: Animal | null;
  protected animals$!: Observable<Animal[]>;
  protected enclos$!: Observable<Enclos[]>;
  protected enclosDisponibles$ !: Observable<Enclos[]>;
  protected especes$!: Observable<string[]>;
  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formAnimal! : FormGroup;
  protected formPrenomCtrl! : FormControl;
  protected formDateNaissanceCtrl! : FormControl;
  protected formEnclosCtrl! : FormControl;
  protected formEspeceCtrl! : FormControl;
  protected formSoinCtrl! : FormControl;

  protected filteredEspeces$!: Observable<string[]>;
  protected searchEspece: string ='';

  protected especeImages: Record<string, string> = {

    Lion: 'assets/animals/lion.jpg',
    Tigre: 'assets/animals/tigre.jpg',
    Elephant: 'assets/animals/elephant.jpg',
    Rhinoceros: 'assets/animals/rhinoceros.jpg',
    Hippopotame: 'assets/animals/hippopotame.jpg',
    Girafe: 'assets/animals/girafe.jpg',
    Zebre: 'assets/animals/zebre.jpg',

    Panthere: 'assets/animals/panthere.jpg',
    Leopard: 'assets/animals/leopard.jpg',
    Guepard: 'assets/animals/guepard.jpg',
    Jaguar: 'assets/animals/jaguar.jpg',
    Couguar: 'assets/animals/cougar.jpg',
    Lynx: 'assets/animals/lynx.jpg',

    Ours_Polaire: 'assets/animals/ours_polaire.jpg',
    Ours_Brun: 'assets/animals/ours_brun.jpg',

    Panda: 'assets/animals/panda.jpg',
    Panda_Roux: 'assets/animals/panda_roux.jpg',

    Koala: 'assets/animals/koala.jpg',
    Kangourou: 'assets/animals/kangourou.jpg',

    Orang_Outan: 'assets/animals/orang_outan.jpg',
    Gorille: 'assets/animals/gorille.jpg',
    Chimpanze: 'assets/animals/chimpanze.jpg',
    Bonobo: 'assets/animals/bonobo.jpg',
    Lemurien: 'assets/animals/lemurien.jpg',

    Loup: 'assets/animals/loup.jpg',
    Loup_Artique: 'assets/animals/loup_artique.jpg',
    Hyene: 'assets/animals/hyene.jpg',

    Manchot: 'assets/animals/manchot.jpg',
    Pingouin: 'assets/animals/pingouin.jpg',

    Autruche: 'assets/animals/autruche.jpg',
    Flamant_Rose: 'assets/animals/flamant_rose.jpg',
    Perroquet: 'assets/animals/perroquet.jpg',
    Toucan: 'assets/animals/toucan.jpg',
    Aigle: 'assets/animals/aigle.jpg',
    Faucon: 'assets/animals/faucon.jpg',
    Chouette: 'assets/animals/chouette.jpg',

    Crocodile: 'assets/animals/crocodile.jpg',
    Alligator: 'assets/animals/alligator.jpg',
    Serpent_Python: 'assets/animals/python.jpg',
    Cobra: 'assets/animals/cobra.jpg',
    Varan: 'assets/animals/varan.jpg',
    Cameleon: 'assets/animals/cameleon.jpg',
    Iguane: 'assets/animals/iguane.jpg',

    Tortue_Geante: 'assets/animals/tortue_geante.jpg',

    Dauphin: 'assets/animals/dauphin.jpg',
    Otarie: 'assets/animals/otarie.jpg',
    Phoque: 'assets/animals/phoque.jpg',
    Manta: 'assets/animals/manta.jpg',
    Requin: 'assets/animals/requin.jpg',

    Suricate: 'assets/animals/suricate.jpg'
  };

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Animaux');
    this.especes$ = this.especeService.findAllEspeces();
    this.filteredEspeces$ = this.especes$;

    this.animals$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.animalService.findAllAnimals())
    );

    this.enclos$ = this.enclosService.findAllEncloss();

    this.enclosDisponibles$ = this.enclos$.pipe(
      map(enclos => enclos.filter(e => (e.animals?.length ?? 0) < e.capacite))
    )
    this.especes$ = this.especeService.findAllEspeces();

    this.formPrenomCtrl = new FormControl("", Validators.required);
    this.formDateNaissanceCtrl = new FormControl("", Validators.required);
    this.formEnclosCtrl = new FormControl(null, Validators.required);
    this.formEspeceCtrl = new FormControl(null, Validators.required);

    this.formAnimal = this.formBuilder.group({
      prenom: this.formPrenomCtrl,
      dateNaissance: this.formDateNaissanceCtrl,
      idEnclos: this.formEnclosCtrl,
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
      idEnclos: this.formEnclosCtrl.value?.numero,
      biomeEnclos: this.formEnclosCtrl.value?.biome,
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
    this.formEnclosCtrl.setValue({ numero: animal.idEnclos, biome: animal.biomeEnclos });
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

  filterEspeces(search: string): void {
  this.filteredEspeces$ = this.especes$.pipe(
    map(especes => especes.filter(e =>
      e.toLowerCase().includes(search.toLowerCase())
    ))
  );
}
}
