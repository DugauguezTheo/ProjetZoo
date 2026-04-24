package formation_sopra.controller.dto.request;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CreateOrUpdateAnimalRequest {

    @NotBlank
    private String prenom;

    @NotBlank
    private LocalDate dateNaissance;

    @NotBlank
    private Enclos enclos;

    @NotBlank
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

    public Enclos getEnclos() {
        return enclos;
    }

    public void setEnclos(Enclos enclos) {
        this.enclos = enclos;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }
}
