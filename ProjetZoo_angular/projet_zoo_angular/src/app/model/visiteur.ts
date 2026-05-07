export interface Visiteur {
    id?: number;
    login: string;
    password: string;
    prenom: string;
    nom: string;
    dateNaissance: Date;
    cp: string;
    ville: string;
    voie: string;
    numeroVoie: string;
    // sexe: Sexe;
    // telephone: string;
    pointsFidelite?: number;

}