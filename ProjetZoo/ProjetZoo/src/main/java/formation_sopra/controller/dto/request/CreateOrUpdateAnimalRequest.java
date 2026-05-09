package formation_sopra.controller.dto.request;

import formation_sopra.model.Espece;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class CreateOrUpdateAnimalRequest {

    @NotBlank
    private String prenom;

    @NotNull
    @PastOrPresent
    private LocalDate dateNaissance;

    @NotNull
    Integer idEnclos;

    @NotNull
    private Espece espece;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    public Integer getIdEnclos() {
        return idEnclos;
    }

    public void setIdEnclos(Integer idEnclos) {
        this.idEnclos = idEnclos;
    }
}
