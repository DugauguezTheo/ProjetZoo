package formation_sopra.controller.dto.response;

import formation_sopra.model.Article;

public class ArticleResponse {

    private Integer id;
    private String libelle;
    private double prix;
    private int quantiteStock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public static ArticleResponse convert(Article article){
        ArticleResponse articleResponse = new ArticleResponse();

        articleResponse.setId(article.getId());
        articleResponse.setLibelle(article.getLibelle());
        articleResponse.setPrix(article.getPrix());
        articleResponse.setQuantiteStock(article.getQuantiteStock());

        return articleResponse;
    }
}
