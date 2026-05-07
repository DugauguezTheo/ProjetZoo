import { Enclos } from "./enclos";
import { Espece } from "./espece";

export interface Animal {
    id?: number;
    prenom: string;
    dateNaissance: Date;
    idEnclos: number;
    biomeEnclos: string;
    espece: Espece;
    // soin?: Soin[];
}
