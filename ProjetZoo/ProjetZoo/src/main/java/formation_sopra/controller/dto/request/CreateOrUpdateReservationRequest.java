package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.util.List;


public class CreateOrUpdateReservationRequest {


    private LocalDate dateVisite;
    private LocalDate dateReservation;
    private Double prix;
    private Integer nbPersonne;
    private Integer visiteur_id;

    private List<Integer> spectacleIds;
    
    public LocalDate getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(Integer nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Integer getVisiteurId() {
        return visiteur_id;
    }

    public void setVisiteurId(Integer visiteur_id) {
        this.visiteur_id = visiteur_id;
    }

    public List<Integer> getSpectacleIds() {
        return spectacleIds;
    }

    public void setSpectacleIds(List<Integer> spectacleIds) {
        this.spectacleIds = spectacleIds;
    }

}