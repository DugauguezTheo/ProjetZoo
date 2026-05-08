package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CreateOrUpdateSpectacleRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate dateDebut;

    @NotNull
    private LocalTime heureDebut;

    @NotNull
    private Integer duree;
    
    @NotNull
    private Integer enclosId;

    @NotBlank
    private String titre;

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    public Integer getDuree() {
        return duree;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getEnclosId() {
        return enclosId;
    }
    public void setEnclosId(Integer enclosId) {
        this.enclosId = enclosId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
