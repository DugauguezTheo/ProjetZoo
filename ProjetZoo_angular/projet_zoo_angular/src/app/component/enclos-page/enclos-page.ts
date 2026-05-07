import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { EnclosService } from '../../service/enclos-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { EspeceService } from '../../service/espece-service';
import { Enclos } from '../../model/enclos';
import { Espece } from '../../model/espece';

@Component({
  selector: 'app-enclos-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './enclos-page.html',
  styleUrl: './enclos-page.css',
})
export class EnclosPage implements OnInit {

  private titleService: Title = inject(Title);
  private router: Router = inject(Router);

  private enclosService: EnclosService = inject(EnclosService);
  protected especeService: EspeceService = inject(EspeceService);
  
  protected editingEnclos ?: Enclos | null;
  protected enclos$!: Observable<Enclos[]>;
  protected especes$!: Observable<string[]>;
  private refresh$: Subject<void> = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formEnclos! : FormGroup;
  protected formBiomeCtrl! : FormControl;
  protected formCapaciteCtrl! : FormControl;
  protected formEspeceCtrl! : FormControl;
  protected formAnimalCtrl! : FormControl;

  ngOnInit(): void {
    this.titleService.setTitle('Zoo AJC - Enclos');
    this.especes$ = this.especeService.findAllEspeces();
    
    this.enclos$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.enclosService.findAllEncloss())
    );

    this.especes$ = this.especeService.findAllEspeces();

    this.formBiomeCtrl = new FormControl("", Validators.required);
    this.formCapaciteCtrl = new FormControl("", Validators.required);
    this.formEspeceCtrl = new FormControl(null, Validators.required);

    this.formEnclos = this.formBuilder.group({
      biome: this.formBiomeCtrl,
      capacite: this.formCapaciteCtrl,
      espece: this.formEspeceCtrl
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdateEnclos() {
    const enclos: Enclos = {
      biome: this.formBiomeCtrl.value,
      capacite: this.formCapaciteCtrl.value,
      espece: this.formEspeceCtrl.value,
      numero: this.editingEnclos?.numero
    };

    if(this.editingEnclos) {
      this.enclosService.updateEnclos(enclos).subscribe(() => {
        this.editingEnclos = null;
        this.formEnclos.reset();
        this.reload();
      });
    }
    else {
      this.enclosService.addEnclos(enclos).subscribe(() => {
        this.formEnclos.reset();
        this.reload();
      })
    }
  }

  public editerEnclos(enclos: Enclos) {
    this.editingEnclos = enclos;
    this.formBiomeCtrl.setValue(enclos.biome);
    this.formCapaciteCtrl.setValue(enclos.capacite);
    this.formEspeceCtrl.setValue(enclos.espece);
    this.reload();
  }
}
