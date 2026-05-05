package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.util.List;

public record CreateOrUpdateVisiteurRequest(
    String login, 
    String password, 
    String nom, 
    String prenom, 
    LocalDate dateNaissance,
    Integer pointsFidelite,
    List<Integer> achatsIds,
    String numeroVoie,
    String voie,
    String ville,
    String cp
) {

}
