package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


public class CreateOrUpdateReservationAsAdminRequest {

    @NotNull(message = "La date de visite doit être renseignée")
    private LocalDate dateVisite;

    private LocalDate dateReservation;

    @NotNull
    // En disabled, le client ne choisit pas le prix
    private Double prix;

    @NotNull(message = "Veuillez saisir le nombre de personnes présentes lors de la visite")
    private Integer nbPersonne;

    @NotNull
    // Hidden
    private Integer visiteurId;

    private List<Integer> spectaclesIds;
    
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
        return visiteurId;
    }

    public void setVisiteurId(Integer visiteurId) {
        this.visiteurId = visiteurId;
    }

    public List<Integer> getSpectaclesIds() {
        return spectaclesIds;
    }

    public void setSpectaclesIds(List<Integer> spectaclesIds) {
        this.spectaclesIds = spectaclesIds;
    }

}