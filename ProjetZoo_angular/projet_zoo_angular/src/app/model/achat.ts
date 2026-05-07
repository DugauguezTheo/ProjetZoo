export interface Achat {
  reference ?: number;
  idArticle : number;
  quantite : number;
  prixUnitaireATM : number;
  dateAchat : Date;
  idVisiteur : number;
}
