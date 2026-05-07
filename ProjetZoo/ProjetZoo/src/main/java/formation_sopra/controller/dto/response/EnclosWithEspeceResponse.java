package formation_sopra.controller.dto.response;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;

import java.util.List;

public class EnclosWithEspeceResponse {

    private Integer numero;
    private String biome;
    private int capacite;
    private List<Espece> especes;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

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

    public List<Espece> getEspeces() {
        return especes;
    }

    public void setEspeces(List<Espece> especes) {
        this.especes = especes;
    }

    public static EnclosWithEspeceResponse convert(Enclos enclos) {
        EnclosWithEspeceResponse enclosWithEspeceResponse = new EnclosWithEspeceResponse();

        enclosWithEspeceResponse.setNumero(enclos.getNumero());
        enclosWithEspeceResponse.setBiome(enclos.getBiome());
        enclosWithEspeceResponse.setCapacite(enclos.getCapacite());
        enclosWithEspeceResponse.setEspeces(enclos.getAnimals()
                .stream()
                .map(a -> a.getEspece())
                .distinct()
                .toList());
        return enclosWithEspeceResponse;
    }
}
