import { Enclos } from "./enclos";

export interface Spectacle {
    id?: number;
    dateDebut: Date;
    heureDebut: Date;
    duree: number;
    enclosId: number;
    enclosBiome: string;
    // reservations?: Reservation[];
}
