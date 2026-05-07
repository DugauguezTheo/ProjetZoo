import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../service/auth-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { ArticleService } from '../../service/article-service';
import { VisiteurService } from '../../service/visiteur-service';
import { Article } from '../../model/article';
import { VisiteurWithAchats } from '../../model/visiteur-with-achats';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-article-page',
  imports: [
    CommonModule
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

    ngOnInit(): void {

      if (this.authService.isVisiteur()) {
        this.visiteur$ = this.visiteurService.getVisiteurConnecte();
      }


      this.titleService.setTitle(`Articles - AJC-Zoo`);

      this.role = this.authService.role.substring(5, 6) + this.authService.role.substring(6).toLowerCase();

      this.articles$ = this.refresh$.pipe(
            startWith(0),
            switchMap(() => this.articleService.findAllArticles())
      );
    }

}

