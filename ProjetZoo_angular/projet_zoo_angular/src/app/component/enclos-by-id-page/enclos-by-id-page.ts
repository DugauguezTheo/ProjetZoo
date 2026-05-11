import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { AnimalService } from '../../service/animal-service';
import { EnclosService } from '../../service/enclos-service';
import { EspeceService } from '../../service/espece-service';
import { map, Observable } from 'rxjs';
import { Espece } from '../../model/espece';
import { Animal } from '../../model/animal';
import { Enclos } from '../../model/enclos';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CarteLogged } from "../carte-logged/carte-logged";

@Component({
  selector: 'app-enclos-by-id-page',
  imports: [
    RouterLink,
    CommonModule,
    CarteLogged
],
  templateUrl: './enclos-by-id-page.html',
  styleUrl: './enclos-by-id-page.css',
})
export class EnclosByIdPage implements OnInit{

  private titleService: Title = inject(Title);
  private route : ActivatedRoute = inject(ActivatedRoute);

  protected authService: AuthService = inject(AuthService);

  private animalService: AnimalService = inject(AnimalService);
  protected enclosService: EnclosService = inject(EnclosService);
  protected especeService: EspeceService = inject(EspeceService);

  protected especesDansLEnclos$ ?: Observable<Espece[]>;
  protected animals$ ?: Observable<Animal[]>;
  protected enclos$ ?: Observable<Enclos>;

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

      this.route.paramMap.subscribe(params => {
        const id = Number(params.get('id'));

        this.enclos$ = this.enclosService.findEnclosById(id);
        this.animals$ = this.animalService.findAllByEnclosId(id);
        this.especesDansLEnclos$ = this.animals$.pipe(
          map(animals => [...new Set(animals.map(animal => animal.espece))]
          )
        );
      })
    }
}
