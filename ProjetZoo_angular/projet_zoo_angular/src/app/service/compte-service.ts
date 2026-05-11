import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Compte } from '../model/compte';

@Injectable({
  providedIn: 'root',
})
export class CompteService {

  constructor(private http: HttpClient) {}

    public findAllComptes() {
      return this.http.get<Compte[]>('/compte');
    }

    public findCompteById(id: number | undefined) {
      return this.http.get<Compte>(`/compte/${id}`);
    }


    public deleteCompte(id: number | undefined) {
      return this.http.delete(`/compte/${id}`);
    }

}
