package formation_sopra.controller.dto.response;

import java.util.List;

import formation_sopra.model.Article;

public class ArticleWithVentesResponse {

    private Integer id;
    private String libelle;
    private double prix;
    private int quantiteStock;
    private List<Integer> ventesIds;

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

    public List<Integer> getVentesIds() {
        return ventesIds;
    }

    public void setVentesIds(List<Integer> ventesIds) {
        this.ventesIds = ventesIds;
    }

    public static ArticleWithVentesResponse convert(Article article){
        ArticleWithVentesResponse articleWithVentesResponse = new ArticleWithVentesResponse();

        articleWithVentesResponse.setId(article.getId());
        articleWithVentesResponse.setLibelle(article.getLibelle());
        articleWithVentesResponse.setPrix(article.getPrix());
        articleWithVentesResponse.setQuantiteStock(article.getQuantiteStock());
        articleWithVentesResponse.setVentesIds(article.getVentes().stream()
            .map(vente -> vente.getReference())
            .toList()
        );

        return articleWithVentesResponse;
    }

    
}
