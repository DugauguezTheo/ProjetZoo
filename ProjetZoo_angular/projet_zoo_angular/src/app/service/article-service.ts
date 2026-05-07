import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Article } from '../model/article';

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
