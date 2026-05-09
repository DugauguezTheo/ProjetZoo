import { Enclos } from "./enclos";
import { Spectacle } from "./spectacle";

export interface Reservation {
    id?: number;
    dateVisite: Date;
    dateReservation: Date;
    prix: number;
    nbPersonne: number;
    visiteurId: number;
    
    visiteurNom: string;

    spectaclesIds?: number[];
}
