export interface ArticleWithVentes {
  id ?: number;
  libelle : string;
  prix : number;
  quantiteStock : number;
  ventesIds : number[];
}
