package formation_sopra.controller.dto.response;

import java.time.LocalDateTime;


import formation_sopra.model.Soin;


public record SoinResponse(
    Integer id, LocalDateTime dateSoin, String descritpion, VeterinaireResponse veterinaire, AnimalResponse animal
) {
    public static SoinResponse convert(Soin soin) {
        SoinResponse resp = new SoinResponse(
            soin.getId(), 
            soin.getDateSoin(),
            soin.getDescription(),
            VeterinaireResponse.convert(soin.getVeterinaire()),
            AnimalResponse.convert(soin.getAnimal())
        );

        return resp;
    }
}
