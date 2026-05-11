import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VisiteurWithAchats } from '../model/visiteur-with-achats';
import { Achat } from '../model/achat';
import { Visiteur } from '../model/visiteur';

@Injectable({
  providedIn: 'root',
})
export class VisiteurService {

  constructor(private http: HttpClient){}

  public getVisiteurConnecte() {
    return this.http.get<VisiteurWithAchats>("/visiteur/mon-compte");
  }

  public findAllVisiteur() {
    return this.http.get<Visiteur[]>("/visiteur");
  }

  public addVisiteur(visiteur: Visiteur) {
    return this.http.post<Visiteur>('/visiteur', visiteur);
  }

  public updateVisiteur(visiteur: Visiteur) {
    return this.http.put<Visiteur>(`/visiteur/${visiteur.id}`, visiteur);
  }

  public getLastAchat(id : number | undefined) {
    return this.http.get<Achat>(`/visiteur/${id}/dernier-achat`)
  }
}
