export interface Achat {
  reference ?: number;
  idArticle : number;
  articleLibelle : string;
  quantite : number;
  prixUnitaireATM : number;
  dateAchat : Date;
  idVisiteur : number;
}
