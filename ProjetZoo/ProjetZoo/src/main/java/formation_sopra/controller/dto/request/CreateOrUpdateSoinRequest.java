package formation_sopra.controller.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateSoinRequest(
    @NotNull
    LocalDateTime dateSoin,

    @NotNull
    Integer veterinaireId,

    @NotNull
    Integer animalId
) 
{}
