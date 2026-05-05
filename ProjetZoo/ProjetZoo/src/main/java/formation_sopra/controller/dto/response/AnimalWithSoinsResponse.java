package formation_sopra.controller.dto.response;

import java.time.LocalDate;
import java.util.List;

import formation_sopra.model.Animal;
import formation_sopra.model.Espece;

public class AnimalWithSoinsResponse {

    private Integer id;
    private String prenom;
    private Espece espece;
    private Integer idEnclos;
    private LocalDate dateNaissance;
    private List<Integer> soinsIds;

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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Integer> getSoinsIds() {
        return soinsIds;
    }

    public void setSoinsIds(List<Integer> soinsIds) {
        this.soinsIds = soinsIds;
    }

    public static AnimalWithSoinsResponse convert(Animal animal){
        AnimalWithSoinsResponse animalResponse = new AnimalWithSoinsResponse();

        animalResponse.setId(animal.getId());
        animalResponse.setPrenom(animal.getPrenom());
        animalResponse.setEspece(animal.getEspece());
        animalResponse.setIdEnclos(animal.getEnclos().getNumero());
        animalResponse.setDateNaissance(animal.getDateNaissance());
        animalResponse.setSoinsIds(animal.getSoins().stream()
            .map(soin -> soin.getId())
            .toList()
        );

        return animalResponse;
    }
    
}
