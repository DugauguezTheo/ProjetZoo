import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Soin } from '../model/soin';

@Injectable({
  providedIn: 'root',
})
export class SoinService {
  constructor(private http: HttpClient){}

  public findAllSoin() {
    return this.http.get<Soin[]>('/soin');
  }

  public addSoin(soin: Soin) {
    const soinToAdd = {
      dateSoin: soin.dateSoin,
      description: soin.description,
      veterinaireId: soin.veterinaire,
      animalId: soin.animal
    };

    return this.http.post<Soin>('/soin', soinToAdd);
  }

  public updateSoin(soin: Soin) {
    const soinToUpdate = {
      dateSoin: soin.dateSoin,
      description: soin.description,
      veterinaireId: soin.veterinaire,
      animalId: soin.animal
    };
    return this.http.put<Soin>(`/soin/${soin.id}`, soinToUpdate);
  }

  public deleteSoin(id: number | undefined) {
    return this.http.delete(`/soin/${id}`);
  }
}

