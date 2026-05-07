import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Achat } from '../model/achat';

@Injectable({
  providedIn: 'root',
})
export class AchatService {

  constructor(private http: HttpClient) { }

  public findAllAchats() {
    return this.http.get<Achat[]>('/achat');
  }

  public findAllAchatsByVisiteurConnected() {
    return this.http.get<Achat[]>("/achat/visiteur");
  }

  public findAllAchatsByIdVisiteur(idVisiteur : number) {
    return this.http.get<Achat[]>(`/achat/visiteur/${idVisiteur}`);
  }

  public findAchatById(id: number | undefined) {
    return this.http.get<Achat>(`/achat/${id}`);
  }

  public addAchat(achat: Achat) {
    return this.http.post<Achat>('/achat', achat);
  }

  public updateAchat(achat: Achat) {
    return this.http.put<Achat>(`/achat/${achat.reference}`, achat);
  }

  public deleteAchat(id: number | undefined) {
    return this.http.delete(`/achat/${id}`);
  }

}
