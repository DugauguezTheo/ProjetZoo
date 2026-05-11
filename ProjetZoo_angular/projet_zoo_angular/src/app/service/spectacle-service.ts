import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Spectacle } from '../model/spectacle';
import { SpectacleRequest } from '../dto/spectacle-request';

@Injectable({
  providedIn: 'root',
})
export class SpectacleService {

  
  constructor(private http: HttpClient) { }

  public findAllSpectacles() {
    return this.http.get<Spectacle[]>('/spectacle');
  }

  public findSpectacleById(numero: number | undefined) {
    return this.http.get<Spectacle>(`/spectacle/${numero}`);
  }

  public findSpectacleByIdWithReservations(numero: number | undefined) {
    return this.http.get<Spectacle>(`/spectacle/${numero}/with-reservations`);
  }

  public findSpectaclesBetween(dateDebut: string, dateFin: string) {
    return this.http.get<Spectacle[]>(`/spectacle/between?dateDebut=${dateDebut}&dateFin=${dateFin}`);
  }

  public findSpectaclesByEnclosId(enclosId: number) {
    return this.http.get<Spectacle[]>(`/spectacle/enclos/${enclosId}`);
  }

  public findSpectaclesByEnclosIdBetween(enclosId: number, dateDebut: Date, dateFin: Date) {
    return this.http.get<Spectacle[]>(`/spectacle/enclos/${enclosId}/between?dateDebut=${dateDebut}&dateFin=${dateFin}`);
  }

  public addSpectacle(spectacle: SpectacleRequest) {
    return this.http.post<Spectacle>('/spectacle', spectacle);
  }

  public updateSpectacle(id: number | undefined, spectacle: SpectacleRequest) {
    return this.http.put<Spectacle>(`/spectacle/${id}`, spectacle);
  }

  public deleteSpectacle(id: number | undefined) {
    return this.http.delete(`/spectacle/${id}`);
  }



}
