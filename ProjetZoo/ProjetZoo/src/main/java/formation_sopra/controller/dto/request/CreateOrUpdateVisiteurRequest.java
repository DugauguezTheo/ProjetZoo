package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateVisiteurRequest(
    @NotBlank
    String login, 

    @NotBlank
    String password, 

    @NotBlank
    String nom,

    @NotBlank
    String prenom, 

    @NotBlank
    LocalDate dateNaissance,

    @Min(0)
    Integer pointsFidelite,
    
    List<Integer> achatsIds,
    String numeroVoie,
    String voie,
    String ville,
    String cp
) {

}
