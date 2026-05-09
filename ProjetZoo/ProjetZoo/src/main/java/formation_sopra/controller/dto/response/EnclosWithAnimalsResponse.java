package formation_sopra.controller.dto.response;

import java.util.List;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;

public class EnclosWithAnimalsResponse {

    private Integer numero;
    private String biome;
    private int capacite;
    private List<Espece> especes;
    private List<AnimalResponse> animals;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public String getBiome() {
        return biome;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    public List<AnimalResponse> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalResponse> animals) {
        this.animals = animals;
    }

    public static EnclosWithAnimalsResponse convert(Enclos enclos){
        EnclosWithAnimalsResponse enclosWithAnimals = new EnclosWithAnimalsResponse();

        enclosWithAnimals.setNumero(enclos.getNumero());
        enclosWithAnimals.setBiome(enclos.getBiome());
        enclosWithAnimals.setCapacite(enclos.getCapacite());
        enclosWithAnimals.setEspeces(enclos.getAnimals().stream()
            .map(a -> a.getEspece())
            .distinct()
            .toList()
        );
        enclosWithAnimals.setAnimals(enclos.getAnimals().stream()
            .map(animal -> AnimalResponse.convert(animal))
            .toList()
        );


        return enclosWithAnimals;
    }

}
