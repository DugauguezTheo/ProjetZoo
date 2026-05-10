import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { ArticleService } from '../../service/article-service';
import { VisiteurService } from '../../service/visiteur-service';
import { Article } from '../../model/article';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CarteLogged } from "../carte-logged/carte-logged";
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-article-page',
  imports: [
    CommonModule,
    RouterModule,
    CarteLogged,
    ReactiveFormsModule
],
  templateUrl: './article-page.html',
  styleUrl: './article-page.css',
})
export class ArticlePage implements OnInit {
  private titleService: Title = inject(Title);
  protected authService: AuthService = inject(AuthService);

  private refresh$: Subject<void> = new Subject<void>();

  protected articleService : ArticleService = inject(ArticleService);
  protected visiteurService : VisiteurService = inject(VisiteurService);
  protected visiteur$ !: Observable<VisiteurWithAchats>;
  protected articles$ !: Observable<Article[]>
  protected role !: string;

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formArticle!: FormGroup;

  protected formLibelleCtrl !: FormControl;
  protected formPrixCtrl!: FormControl;
  protected formQuantiteCtrl!: FormControl;

  protected popupSuppressionVisible: boolean = false;
  protected popupSuccesVisible: boolean = false;
  protected articleASupprimer?: Article;


    ngOnInit(): void {

      if (this.authService.isVisiteur()) {
        this.visiteur$ = this.visiteurService.getVisiteurConnecte();
      }


      this.titleService.setTitle(`Articles - AJC-Zoo`);

      this.formPrixCtrl = new FormControl(0, [ Validators.required, Validators.min(0) ]);
      this.formLibelleCtrl = new FormControl("", Validators.required);
      this.formQuantiteCtrl = new FormControl(1, [ Validators.required, Validators.min(1) ] );

      this.formArticle = this.formBuilder.group({
        articleLibelle : this.formLibelleCtrl,
        prix: this.formPrixCtrl,
        quantite: this.formQuantiteCtrl
      });

      this.articles$ = this.refresh$.pipe(
            startWith(0),
            switchMap(() => this.articleService.findAllArticles())
      );
    }

  private reload() {
      this.refresh$.next();
    }

  public addArticle() {
    const article: Article = {
      libelle: this.formLibelleCtrl.value,
      prix: this.formPrixCtrl.value,
      quantiteStock: this.formQuantiteCtrl.value
    };

    this.articleService.addArticle(article).subscribe(() => {
      this.formArticle.reset();
      this.reload();
    })
  }

  public ouvrirPopupSuppression(article : Article) {

      this.articleASupprimer = article;

      this.popupSuppressionVisible = true;

    }

    public annulerSuppression() {

      this.popupSuppressionVisible = false;

      this.articleASupprimer = undefined;

    }

    public confirmerSuppression() {

      if(!this.articleASupprimer?.id) {
        return;
      }

      this.articleService.deleteArticle(this.articleASupprimer.id)
        .subscribe(() => {

          this.popupSuppressionVisible = false;

          this.popupSuccesVisible = true;

          this.reload();

          this.articleASupprimer = undefined;

        });

    }

    public fermerPopupSucces() {

      this.popupSuccesVisible = false;

    }
}

