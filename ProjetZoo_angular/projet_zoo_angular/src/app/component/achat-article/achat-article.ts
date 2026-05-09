import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { ArticleService } from '../../service/article-service';
import { VisiteurService } from '../../service/visiteur-service';
import { Article } from '../../model/article';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Achat } from '../../model/achat';
import { AchatService } from '../../service/achat-service';
import { CommonModule } from '@angular/common';
import { CarteLogged } from "../carte-logged/carte-logged";

@Component({
  selector: 'app-achat-article',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CarteLogged
],
  templateUrl: './achat-article.html',
  styleUrl: './achat-article.css',
})
export class AchatArticle implements OnInit {
  private titleService: Title = inject(Title);
  protected authService: AuthService = inject(AuthService);
  protected achatService : AchatService = inject(AchatService);
  private router : Router = inject(Router);

  private cdr : ChangeDetectorRef = inject(ChangeDetectorRef);
  private route: ActivatedRoute = inject(ActivatedRoute);

  protected articleService : ArticleService = inject(ArticleService);
  protected visiteurService : VisiteurService = inject(VisiteurService);
  protected visiteur$ !: Observable<VisiteurWithAchats>;
  protected article$ !: Observable<Article>
  protected role !: string;

  protected achatSuccess = false;

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formAchat!: FormGroup;

  protected formIdArticleCtrl!: FormControl;
  protected formLibelleCtrl !: FormControl;
  protected formPrixCtrl!: FormControl;
  protected formQuantiteCtrl!: FormControl;
  protected formPrixTotalCtrl!: FormControl;


    ngOnInit(): void {

      if (this.authService.isVisiteur()) {
        this.visiteur$ = this.visiteurService.getVisiteurConnecte();
      }

      this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();

      const id = Number(this.route.snapshot.paramMap.get('id'));

      this.article$ = this.route.paramMap.pipe(
        switchMap(() => {
          return this.articleService.findArticleById(id);
        })
      );

      this.article$.subscribe(article => {
        this.titleService.setTitle(`${article.libelle} - AJC-Zoo`);
      });

      this.formIdArticleCtrl = new FormControl(id);
      this.formPrixCtrl = new FormControl(0);
      this.formLibelleCtrl = new FormControl("");

      this.article$.subscribe(article => {
        this.formLibelleCtrl.setValue(article.libelle);
        this.formPrixCtrl.setValue(article.prix);
      });

      this.formQuantiteCtrl = new FormControl(1);
      this.formPrixTotalCtrl = new FormControl(this.formPrixCtrl.value * this.formQuantiteCtrl.value);

      this.formAchat = this.formBuilder.group({
        idArticle: this.formIdArticleCtrl,
        articleLibelle : this.formLibelleCtrl,
        prix: this.formPrixCtrl,
        quantite: this.formQuantiteCtrl,
        prixTotal: this.formPrixTotalCtrl
      });
    }


    public addAchat() {

      this.visiteur$.subscribe(visiteur => {

        const achat: Achat = {

          idArticle: this.formIdArticleCtrl.value,

          articleLibelle : this.formLibelleCtrl.value,

          quantite: this.formQuantiteCtrl.value,

          prixUnitaireATM: this.formPrixCtrl.value,

          dateAchat: new Date(),

          idVisiteur: visiteur.id!

        };

        this.achatService.addAchat(achat).subscribe(() => {

          this.achatSuccess = true;
          this.cdr.detectChanges();

        });

      });

    }

    protected closeAchatSuccess() {
      this.achatSuccess = false;
      this.router.navigate(['/boutique']);

    }

}
