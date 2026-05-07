export interface VisiteurWithAchats {

  id?: number;

  login: string;

  prenom: string;
  nom: string;

  dateNaissance: Date;

  cp: string;
  ville: string;
  voie: string;
  numeroVoie: string;

  pointsFidelite?: number;

  achatsIds: number[];
}
