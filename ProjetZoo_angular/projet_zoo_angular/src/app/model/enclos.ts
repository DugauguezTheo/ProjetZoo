import { Animal } from "./animal";
import { Espece } from "./espece";

export interface Enclos {
    numero?: number;
    biome: string;
    capacite: number;
    especes?: Espece[];
    animal ?: Animal[];
    // spectacle?: Spectacle[];
}
