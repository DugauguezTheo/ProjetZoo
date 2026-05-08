package formation_sopra.controller.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import formation_sopra.model.Spectacle;

public class SpectacleResponse {

    private Integer id;
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private Integer duree;
    private Integer enclosId;
    private String enclosBiome;
    private String titre;

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public LocalDate getDateDebut(){
        return this.dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut){
        this.dateDebut = dateDebut;
    }

    public LocalTime getHeureDebut(){
        return this.heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut){
        this.heureDebut = heureDebut;
    }

    public Integer getDuree(){
        return this.duree;
    }

    public void setDuree(Integer duree){
        this.duree = duree;
    }


    public Integer getEnclosId() {
        return enclosId;
    }

    public void setEnclosId(Integer enclosId) {
        this.enclosId = enclosId;
    }

    public String getEnclosBiome() {
        return enclosBiome;
    }

    public void setEnclosBiome(String enclosBiome) {
        this.enclosBiome = enclosBiome;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public static SpectacleResponse convert(Spectacle spectacle){
        SpectacleResponse spectacleResponse = new SpectacleResponse();

        spectacleResponse.setId(spectacle.getId());
        spectacleResponse.setDateDebut(spectacle.getDateDebut());
        spectacleResponse.setHeureDebut(spectacle.getHeureDebut());
        spectacleResponse.setDuree(spectacle.getDuree());
        spectacleResponse.setEnclosId(spectacle.getEnclos().getNumero());
        spectacleResponse.setEnclosBiome(spectacle.getEnclos().getBiome());
        spectacleResponse.setTitre(spectacle.getTitre());
    
        return spectacleResponse;
    }
}
