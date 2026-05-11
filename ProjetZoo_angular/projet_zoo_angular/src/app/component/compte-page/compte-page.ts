import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AuthService } from '../../service/auth-service';
import { Compte } from '../../model/compte';
import { CompteService } from '../../service/compte-service';
import { VeterinaireService } from '../../service/veterinaire-service';
import { VisiteurService } from '../../service/visiteur-service';
import { preserveWhitespacesDefault } from '@angular/compiler';

@Component({
  selector: 'app-compte-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './compte-page.html',
  styleUrl: './compte-page.css',
})
export class ComptePage implements OnInit {

  private titleService: Title = inject(Title);
  protected authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);

  protected compteService: CompteService = inject(CompteService);
  protected visiteurService: VisiteurService = inject(VisiteurService);
  protected veterinaireService: VeterinaireService = inject(VeterinaireService);

  protected editingCompte?: Compte | null;
  protected compte$!: Observable<Compte[]>;

  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formCompte!: FormGroup;

  protected formLoginCtrl!: FormControl;
  protected formPasswordCtrl!: FormControl;

  // Nouveau champ rôle
  protected formRoleCtrl!: FormControl;

  // Liste des rôles disponibles
  protected roles: string[] = ['ADMIN', 'VETERINAIRE', 'VISITEUR'];

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Comptes');

    this.compte$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.compteService.findAllComptes())
    );

    this.formLoginCtrl = new FormControl('', Validators.required);
    this.formPasswordCtrl = new FormControl('', Validators.required);

    // Initialisation du rôle
    this.formRoleCtrl = new FormControl('VISITEUR', Validators.required);

    this.formCompte = this.formBuilder.group({
      login: this.formLoginCtrl,
      password: this.formPasswordCtrl,
      role: this.formRoleCtrl
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdateCompte() {

    const compte: Compte = {
      id: this.editingCompte?.id,

      login: this.formLoginCtrl.value,
      password: this.formPasswordCtrl.value,

      // Nouveau champ rôle
      role: this.formRoleCtrl.value
    };

    if (compte.role === 'ADMIN') {
      alert("Vous ne pouvez pas créer ou modifier un compte ADMIN.");
      return;
    } else if (compte.role === 'VETERINAIRE') {
      const veterinaireData = {
        id: compte.id,
        login: compte.login,
        password: compte.password,
        soins: []
      };
      this.veterinaireService.addVeterinaire(veterinaireData).subscribe(() => {

        this.formCompte.reset();

        // Valeur par défaut après reset
        this.formRoleCtrl.setValue('VISITEUR');

        this.reload();
      });
      return;
    } else if (compte.role === 'VISITEUR') {
      alert("Veuillez utiliser la page d'inscription pour créer un compte VISITEUR.");
      return;

    }
  }

  public deleteCompte(compte: Compte) {

    if (compte.role === 'ADMIN') {
      alert("Vous ne pouvez pas supprimer un compte ADMIN.");
      return;
    } else if (compte.role === 'VETERINAIRE') {
      this.veterinaireService.deleteVeterinaire(compte.id).subscribe(() => {
        this.reload();
      });
      return;
    } else if (compte.role === 'VISITEUR') {
      this.visiteurService.deleteVisiteur(compte.id).subscribe(() => {
        this.reload();
      });
      return;
    }
  }

}