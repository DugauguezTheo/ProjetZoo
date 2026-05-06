package formation_sopra.controller.dto.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateSoinRequest(
    @NotNull
    LocalDateTime dateSoin,

    @NotBlank
    @Length(max = 200)
    String description,

    @NotNull
    Integer veterinaireId,

    @NotNull
    Integer animalId
) 
{}
