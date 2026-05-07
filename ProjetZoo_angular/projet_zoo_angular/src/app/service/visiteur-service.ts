import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VisiteurWithAchats } from '../model/visiteur-with-achats';

@Injectable({
  providedIn: 'root',
})
export class VisiteurService {

  constructor(private http: HttpClient){}

  public getVisiteurConnecte() {
    return this.http.get<VisiteurWithAchats>("/visiteur/mon-compte");
  }

}
