package formation_sopra.controller.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record CreateOrUpdateVisiteurRequest(
    @NotBlank
    @Email
    String login, 

    @NotBlank
    String password, 

    @NotBlank
    String nom,

    @NotBlank
    String prenom, 

    @PastOrPresent
    @NotNull
    LocalDate dateNaissance,

    @Min(0)
    Integer pointsFidelite,

    @NotBlank
    String numeroVoie,

    @NotBlank
    String voie,

    @NotBlank
    String ville,

    @NotBlank
    String cp
) {

}
