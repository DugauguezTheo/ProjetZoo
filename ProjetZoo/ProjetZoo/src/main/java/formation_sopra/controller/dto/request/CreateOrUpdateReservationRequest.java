package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


public class CreateOrUpdateReservationRequest {

    @FutureOrPresent
    @NotNull(message = "La date de visite doit être renseignée")
    private LocalDate dateVisite;

    @PastOrPresent
    // On la passe en hidden côté front je suppose
    private LocalDate dateReservation;

    @NotNull
    // En disabled, le client ne choisit pas le prix
    private Double prix;

    @NotNull(message = "Veuillez saisir le nombre de personnes présentes lors de la visite")
    private Integer nbPersonne;

    @NotNull
    // Hidden
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