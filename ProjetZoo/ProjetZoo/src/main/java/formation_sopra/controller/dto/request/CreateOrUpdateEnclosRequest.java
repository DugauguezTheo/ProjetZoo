package formation_sopra.controller.dto.request;

import formation_sopra.model.Espece;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateOrUpdateEnclosRequest {

    @NotBlank
    private String biome;

    @Min(1)
    @Digits(integer = 2, fraction = 0)
    @NotNull
    private int capacite;

//    @NotNull
//    private Espece espece;

    public String getBiome() {
        return biome;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

//    public Espece getEspece() {
//        return espece;
//    }
//
//    public void setEspece(Espece espece) {
//        this.espece = espece;
//    }
}
