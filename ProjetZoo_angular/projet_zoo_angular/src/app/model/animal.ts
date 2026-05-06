import { Enclos } from "./enclos";
import { Espece } from "./espece";

export interface Animal {
    id?: number;
    prenom: string;
    dateNaissance: Date;
    enclos: Enclos;
    espece: Espece;
    // soin?: Soin[];
}
