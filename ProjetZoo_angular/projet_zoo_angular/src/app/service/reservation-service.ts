import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reservation } from '../model/reservation';
import { ReservationRequest } from '../dto/reservation-request';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  
  constructor(private http: HttpClient) { }

  public findAllReservations() {
    return this.http.get<Reservation[]>('/reservation');
  }

  public findReservationById(id: number | undefined) {
    return this.http.get<Reservation>(`/reservation/${id}`);
  }

  public findAllBySpectacleId(id: number | undefined) {
    return this.http.get<Reservation[]>(`/reservation/spectacle/${id}`);
  }

  public findAllByVisiteurId(id: number | undefined) {
    return this.http.get<Reservation[]>(`/reservation/visiteur/${id}`);
  }

  public findMesReservations(){
    return  this.http.get<Reservation[]>('/reservation/mes-reservations');
  }

  public addReservation(reservation: ReservationRequest) {
    return this.http.post<Reservation>('/reservation', reservation);
  }

  public updateReservation(id: number | undefined, reservation: ReservationRequest) {
    return this.http.put<Reservation>(`/reservation/${id}`, reservation);
  }

  public deleteReservation(id: number | undefined) {
    return this.http.delete(`/reservation/${id}`);
  }



}
