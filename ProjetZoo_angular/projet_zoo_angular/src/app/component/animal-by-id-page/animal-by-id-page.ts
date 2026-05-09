import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { AnimalService } from '../../service/animal-service';
import { ActivatedRoute, Route, Router, RouterLink } from '@angular/router';
import { EnclosService } from '../../service/enclos-service';
import { EspeceService } from '../../service/espece-service';
import { map, Observable, Subject, switchMap, take } from 'rxjs';
import { Animal } from '../../model/animal';
import { Enclos } from '../../model/enclos';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CarteLogged } from "../carte-logged/carte-logged";

@Component({
  selector: 'app-animal-by-id-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    CarteLogged
],
  templateUrl: './animal-by-id-page.html',
  styleUrl: './animal-by-id-page.css',
})
export class AnimalByIdPage implements OnInit {

  private titleService : Title = inject(Title);
  private route : ActivatedRoute = inject(ActivatedRoute);
  private cdr: ChangeDetectorRef = inject(ChangeDetectorRef);

  protected authService : AuthService = inject(AuthService);
  protected animalService : AnimalService = inject(AnimalService);
  protected enclosService : EnclosService = inject(EnclosService);
  protected especeService : EspeceService = inject(EspeceService);

  protected animalsDansLeMemeEnclos$ !: Observable<Animal[] | undefined >;
  protected animal$ !: Observable<Animal>;
  protected enclos$ !: Observable<Enclos>;

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formAnimal!: FormGroup;

  protected formPrenomCtrl!: FormControl;
  protected formDateNaissanceCtrl!: FormControl;
  protected formEnclosCtrl!: FormControl;
  protected formEspeceCtrl!: FormControl;

  protected especes$!: Observable<string[]>;
  protected enclosDisponibles$!: Observable<Enclos[]>;

  protected modeEdition: boolean = false;

  protected popupVisible: boolean = false;

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

    this.especes$ = this.especeService.findAllEspeces();

    this.route.paramMap.subscribe(params => {

      const id = Number(params.get('id'));

      this.animal$ = this.animalService.findAnimalById(id);

      this.animal$.subscribe(animal => {

        this.titleService.setTitle(`${animal.prenom} - Details`);

        this.formPrenomCtrl.setValue(animal.prenom);

        this.formDateNaissanceCtrl.setValue(animal.dateNaissance);

        this.formEspeceCtrl.setValue(animal.espece);

        this.enclosService.findEnclosById(animal.idEnclos)
          .subscribe(enclos => {

            this.formEnclosCtrl.setValue(enclos);

          });

      });

      this.enclos$ = this.animal$.pipe(
        switchMap(animal =>
          this.enclosService.findEnclosById(animal.idEnclos)
        )
      );

      this.animalsDansLeMemeEnclos$ = this.enclos$.pipe(
        map(enclos =>
          (enclos.animals ?? []).filter(a => a.id != id)
        )
      );

    });

  }

  public modifierAnimal() {

    this.animal$.pipe(take(1)).subscribe(animalActuel => {

      const animal: Animal = {

        id: animalActuel.id,

        prenom: this.formPrenomCtrl.value,

        dateNaissance: this.formDateNaissanceCtrl.value,

        espece: this.formEspeceCtrl.value,

        idEnclos: this.formEnclosCtrl.value?.numero,

        biomeEnclos: this.formEnclosCtrl.value?.biome
      };

      this.animalService.updateAnimal(animal)
        .pipe(take(1))
        .subscribe(animalModifie => {

          /*
           * MAJ immédiate de la carte principale
           */
          this.animal$ = this.animalService.findAnimalById(animalActuel.id!);
          this.enclos$ = this.animal$.pipe(
            switchMap(animal =>
              this.enclosService.findEnclosById(animal.idEnclos)
            )
          );

          this.animalsDansLeMemeEnclos$ = this.enclos$.pipe(
            map(enclos =>
              (enclos.animals ?? []).filter(a => a.id != animalActuel.id)
            )
          );

          /*
           * fermeture du mode édition
           */
          this.modeEdition = false;

          /*
           * affichage popup
           */
          this.popupVisible = true;

          /*
           * force Angular à rafraîchir le template
           */
          this.cdr.detectChanges();

        });

    });

  }

  public ouvrirEdition() {
    this.modeEdition = true;
  }

  public annulerEdition() {
    this.modeEdition = false;
  }

  public fermerPopUp() {

    this.popupVisible = false;

  }
}
