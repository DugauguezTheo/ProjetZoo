package formation_sopra.controller.dto.response;

import java.time.LocalDateTime;

import formation_sopra.model.Soin;

public record SoinResponse(
    Integer id, LocalDateTime dateSoin, Integer veterinaireId, Integer animalId
) {
    public static SoinResponse convert(Soin soin) {
        SoinResponse resp = new SoinResponse(
            soin.getId(), 
            soin.getDateSoin(),
            soin.getVeterinaire().getId(), 
            soin.getAnimal().getId()
        );

        return resp;
    }
}
