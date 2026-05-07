import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Article } from '../model/article';
import { ArticleWithVentes } from '../model/article-with-ventes';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {

  constructor(private http: HttpClient) { }

  public findAllArticles() {
    return this.http.get<Article[]>('/article');
  }

  public findArticleById(id: number | undefined) {
      return this.http.get<Article>(`/article/${id}`);
  }

  public findArticlesByLibelleContaining(libelle : string) {
    return this.http.get<Article>(`/article/by-libelle/${libelle}`);
  }

  public findArticleByIdWithVentes(id : number | undefined){
    return this.http.get<ArticleWithVentes>(`/article/${id}/ventes`);
  }

  public findArticlesWithVentesByLibelleContaining(libelle : string) {
    return this.http.get<ArticleWithVentes>(`/article/by-libelle/${libelle}/ventes`);
  }

  public addArticle(article: Article) {
    return this.http.post<Article>('/article', article);
  }

  public updateArticle(article: Article) {
    return this.http.put<Article>(`/article/${article.id}`, article);
  }

  public deleteArticle(id: number | undefined) {
    return this.http.delete(`/article/${id}`);
  }

}
