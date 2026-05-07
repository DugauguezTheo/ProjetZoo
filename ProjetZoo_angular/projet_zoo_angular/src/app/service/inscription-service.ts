import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Visiteur } from '../model/visiteur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class InscriptionService {

    constructor(private http: HttpClient) {
   }

  public inscription(visiteur: Visiteur) : Observable<Visiteur> {
    return this.http.post<Visiteur>("/visiteur/inscription", visiteur);
  }
}
