package formation_sopra.controller.dto.response;

import formation_sopra.model.Animal;
import formation_sopra.model.Espece;

public class AnimalResponse {

    private Integer id;
    private String prenom;
    private Espece espece;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    public static AnimalResponse convert(Animal animal){
        AnimalResponse animalResponse = new AnimalResponse();

        animalResponse.setId(animal.getId());
        animalResponse.setPrenom(animal.getPrenom());
        animalResponse.setEspece(animal.getEspece());

        return animalResponse;
    }
}
