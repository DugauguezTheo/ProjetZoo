import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { InscriptionRequest } from '../../dto/inscription-request';
import { Visiteur } from '../../model/visiteur';
import { InscriptionService } from '../../service/inscription-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-compte-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './create-compte-page.html',
  styleUrl: './create-compte-page.css',
})
export class CreateComptePage {

  private inscriptionService: InscriptionService = inject(InscriptionService);

  // private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formInscription!: FormGroup;
  protected formPrenomCtrl!: FormControl;
  protected formNomCtrl!: FormControl;
  // protected formSexeCtrl!: FormControl; not in database ?
  protected formCPCtrl!: FormControl;
  protected formVoieCtrl!: FormControl;
  protected formVilleCtrl!: FormControl;
  protected formNumeroVoieCtrl!: FormControl;
  protected formDateNaissanceCtrl!: FormControl;
  protected formLoginCtrl!: FormControl;
  // protected formTelephoneCtrl!: FormControl; Not in database ??
  protected formPasswordCtrl!: FormControl;

  protected inscriptionReussie: boolean = false;
  ngOnInit(): void {

    this.formPrenomCtrl = this.formBuilder.control("", [ Validators.required ]);
    this.formNomCtrl = this.formBuilder.control("", Validators.required);
    // this.formSexeCtrl = this.formBuilder.control("", [ Validators.required ]);
    this.formCPCtrl = this.formBuilder.control("", Validators.required);
    this.formVoieCtrl = this.formBuilder.control("", Validators.required);
    this.formVilleCtrl = this.formBuilder.control("", Validators.required);
    this.formNumeroVoieCtrl = this.formBuilder.control("", Validators.required);
    this.formDateNaissanceCtrl = this.formBuilder.control("", Validators.required);
    this.formLoginCtrl = this.formBuilder.control("", [ Validators.required, Validators.email ]);
    // this.formTelephoneCtrl = this.formBuilder.control("", [ Validators.required, Validators.minLength(10), Validators.maxLength(10) ]);
    this.formPasswordCtrl = this.formBuilder.control("", Validators.required);
   

    this.formInscription = this.formBuilder.group({
      prenom: this.formPrenomCtrl,
      nom: this.formNomCtrl,
      // sexe: this.formSexeCtrl,
      cp: this.formCPCtrl,
      voie: this.formVoieCtrl,
      ville: this.formVilleCtrl,
      numeroVoie: this.formNumeroVoieCtrl,
      dateNaissance: this.formDateNaissanceCtrl,
      // telephone: this.formTelephoneCtrl,
      login: this.formLoginCtrl,
      password: this.formPasswordCtrl
    });
  }

  public inscription() {
    const visiteur: Visiteur = {
      prenom: this.formPrenomCtrl.value,
      nom: this.formNomCtrl.value,
      // sexe: this.formSexeCtrl.value,
      cp: this.formCPCtrl.value,
      voie: this.formVoieCtrl.value,
      ville: this.formVilleCtrl.value,
      numeroVoie: this.formNumeroVoieCtrl.value,
      dateNaissance: this.formDateNaissanceCtrl.value,
      // telephone: this.formTelephoneCtrl.value,
      login: this.formLoginCtrl.value,
      password: this.formPasswordCtrl.value
    };

    this.inscriptionService.inscription(visiteur).subscribe({



    next: () => {

      // affiche le message
      this.inscriptionReussie = true;

      // redirection après 2 secondes
      setTimeout(() => {
        this.router.navigate(['/connexion']);
      }, 3000);

    },

    error: (err) => {
      console.error(err);
      alert("Erreur lors de l'inscription");
    }

  });
}
}