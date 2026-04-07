package formation_sopra.controller.dto.request;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;

import java.time.LocalDate;

public class CreateOrUpdateAnimalRequest {

    private String prenom;
    private LocalDate dateNaissance;
    private Enclos enclos;
    private Espece espece;
    
}
