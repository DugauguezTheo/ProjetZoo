import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Veterinaire } from '../model/veterinaire';
import { Soin } from '../model/soin';

@Injectable({
  providedIn: 'root',
})
export class VeterinaireService {

  constructor(private http: HttpClient){}

  public getVeterinaireConnecte() {
    return this.http.get<Veterinaire>("/veterinaire/mon-compte");
  }

  public getDernierSoin(id : number | undefined) {
    return this.http.get<Soin>(`/veterinaire/${id}/dernier-soin`)
  }

  public getAllVeterinaires() {
    return this.http.get<Veterinaire[]>("/veterinaire")
  }
}
