package formation_sopra.controller.dto.response;

import java.util.List;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;

public class EnclosWithSpectaclesResponse {

    private Integer numero;
    private int capacite;
//    private Espece espece;
    private List<Integer> spectaclesIds;

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

    public List<Integer> getSpectaclesIds() {
        return spectaclesIds;
    }

    public void setSpectaclesIds(List<Integer> spectaclesIds) {
        this.spectaclesIds = spectaclesIds;
    }

    public static EnclosWithSpectaclesResponse convert(Enclos enclos){
        EnclosWithSpectaclesResponse enclosWithSpectaclesResponse = new EnclosWithSpectaclesResponse();

        enclosWithSpectaclesResponse.setNumero(enclos.getNumero());
        enclosWithSpectaclesResponse.setCapacite(enclos.getCapacite());
        enclosWithSpectaclesResponse.setSpectaclesIds(enclos.getSpectacles().stream()
            .map(spectacle -> spectacle.getId())
            .toList()
        );


        return enclosWithSpectaclesResponse;
    }
}
