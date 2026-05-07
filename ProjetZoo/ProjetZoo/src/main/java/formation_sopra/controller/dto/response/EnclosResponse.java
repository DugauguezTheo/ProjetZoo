package formation_sopra.controller.dto.response;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;

public class EnclosResponse {

    private Integer numero;
    private int capacite;
//    private Espece espece;

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

    public static EnclosResponse convert(Enclos enclos){
        EnclosResponse enclosResponse = new EnclosResponse();

        enclosResponse.setNumero(enclos.getNumero());
        enclosResponse.setCapacite(enclos.getCapacite());
//        enclosResponse.setEspece(enclos.getEspece());

        return enclosResponse;
    }
}
