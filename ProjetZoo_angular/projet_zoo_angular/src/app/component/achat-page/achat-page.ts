import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { VisiteurService } from '../../service/visiteur-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { CommonModule } from '@angular/common';
import { Achat } from '../../model/achat';
import { AchatService } from '../../service/achat-service';

@Component({
  selector: 'app-achat-page',
  imports: [
    CommonModule
  ],
  templateUrl: './achat-page.html',
  styleUrl: './achat-page.css',
})
export class AchatPage implements OnInit {
  private titleService: Title = inject(Title);
  private authService: AuthService = inject(AuthService);

  private refresh$: Subject<void> = new Subject<void>();

  protected achatService : AchatService = inject(AchatService);
  protected visiteurService : VisiteurService = inject(VisiteurService);
  protected visiteur$ !: Observable<VisiteurWithAchats>;
  protected achats$ !: Observable<Achat[]>
  protected role !: string;

    ngOnInit(): void {
      this.visiteur$ = this.visiteurService.getVisiteurConnecte();

      this.visiteur$.subscribe(visiteur => {
        this.titleService.setTitle(`Achats ${visiteur.prenom} - AJC-Zoo`);
      });
      this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();

      this.achats$ = this.refresh$.pipe(
            startWith(0),
            switchMap(() => this.achatService.findAllAchatsByVisiteurConnected())
      );
    }


}
