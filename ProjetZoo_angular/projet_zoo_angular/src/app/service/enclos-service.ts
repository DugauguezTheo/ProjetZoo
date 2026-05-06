import { Injectable } from '@angular/core';
import { Enclos } from '../model/enclos';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EnclosService {
  constructor(private http: HttpClient) { }

  public findAllEncloss() {
    return this.http.get<Enclos[]>('/enclos');
  }

  public findEnclosById(numero: number | undefined) {
    return this.http.get<Enclos>(`/enclos/${numero}`);
  }

  public addEnclos(enclos: Enclos) {
    return this.http.post<Enclos>('/enclos', enclos);
  }

  public updateEnclos(enclos: Enclos) {
    return this.http.put<Enclos>(`/enclos/${enclos.numero}`, enclos);
  }

  public deleteEnclos(numero: number | undefined) {
    return this.http.delete(`/enclos/${numero}`);
  }
}
