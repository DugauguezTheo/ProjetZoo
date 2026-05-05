package formation_sopra.controller.dto.request;




public class CreateOrUpdateArticleRequest {

    private String libelle;
    private double prix;
    private int quantiteStock;

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

    
    
}
