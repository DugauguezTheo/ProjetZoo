export interface ReservationRequest {
  dateVisite: Date;
  dateReservation: Date;
  prix: number;
  nbPersonne: number;
  visiteurId: number;
  spectaclesIds: number[];
}