package formation_sopra.controller.dto.response;

import java.util.List;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;

public class EnclosWithAnimalsResponse {

    private Integer numero;
    private int capacite;
//    private Espece espece;
    private List<Integer> animalsIds;

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

//    public Espece getEspece() {
//        return espece;
//    }
//
//    public void setEspece(Espece espece) {
//        this.espece = espece;
//    }

    public List<Integer> getAnimalsIds() {
        return animalsIds;
    }

    public void setAnimalsIds(List<Integer> animalsIds) {
        this.animalsIds = animalsIds;
    }

    public static EnclosWithAnimalsResponse convert(Enclos enclos){
        EnclosWithAnimalsResponse enclosWithAnimals = new EnclosWithAnimalsResponse();

        enclosWithAnimals.setNumero(enclos.getNumero());
        enclosWithAnimals.setCapacite(enclos.getCapacite());
//        enclosWithAnimals.setEspece(enclos.getEspece());
        enclosWithAnimals.setAnimalsIds(enclos.getAnimals().stream()
            .map(animal -> animal.getId())
            .toList()
        );


        return enclosWithAnimals;
    }
}
