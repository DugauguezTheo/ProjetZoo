package formation_sopra.controller.dto.response;

import java.time.LocalDate;

import formation_sopra.model.Animal;
import formation_sopra.model.Espece;

public class AnimalResponse {

    private Integer id;
    private String prenom;
    private Espece espece;
    private Integer idEnclos;
    private String biomeEnclos;
    private LocalDate dateNaissance;

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

    public Integer getIdEnclos() {
        return idEnclos;
    }

    public void setIdEnclos(Integer idEnclos) {
        this.idEnclos = idEnclos;
    }

    public String getBiomeEnclos() {
        return biomeEnclos;
    }

    public void setBiomeEnclos(String biomeEnclos) {
        this.biomeEnclos = biomeEnclos;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public static AnimalResponse convert(Animal animal){
        AnimalResponse animalResponse = new AnimalResponse();

        animalResponse.setId(animal.getId());
        animalResponse.setPrenom(animal.getPrenom());
        animalResponse.setEspece(animal.getEspece());
        animalResponse.setIdEnclos(animal.getEnclos().getNumero());
        animalResponse.setBiomeEnclos(animal.getEnclos().getBiome());
        animalResponse.setDateNaissance(animal.getDateNaissance());

        return animalResponse;
    }
}
