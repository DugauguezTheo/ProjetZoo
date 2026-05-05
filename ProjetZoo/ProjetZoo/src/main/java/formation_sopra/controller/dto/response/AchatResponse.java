package formation_sopra.controller.dto.response;

import java.time.LocalDate;

import formation_sopra.model.Achat;

public class AchatResponse {

    private Integer reference;
    private Integer idArticle;
    private int quantite;
    private double prixUnitaireATM;
    private LocalDate dateAchat;
    private Integer idVisiteur;

    public Integer getReference() {
        return reference;
    }
    public void setReference(Integer reference) {
        this.reference = reference;
    }
    public Integer getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getPrixUnitaireATM() {
        return prixUnitaireATM;
    }
    public void setPrixUnitaireATM(double prixUnitaireATM) {
        this.prixUnitaireATM = prixUnitaireATM;
    }
    public LocalDate getDateAchat() {
        return dateAchat;
    }
    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }
    public Integer getIdVisiteur() {
        return idVisiteur;
    }
    public void setIdVisiteur(Integer idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public static AchatResponse convert(Achat achat) {
        AchatResponse achatResponse = new AchatResponse();

        achatResponse.reference = achat.getReference();
        achatResponse.idArticle = achat.getArticle().getId();
        achatResponse.quantite = achat.getQuantite();
        achatResponse.prixUnitaireATM = achat.getPrixUnitaireATM();
        achatResponse.dateAchat = achat.getDateAchat();
        achatResponse.idVisiteur = achat.getVisiteur().getId();

        return achatResponse;
    }

}
