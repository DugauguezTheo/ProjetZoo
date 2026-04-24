package formation_sopra.controller.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import formation_sopra.model.Reservation;
import formation_sopra.model.Spectacle;

public class SpectacleResponse {

    private Integer id;
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private Integer duree;

    private List<Integer> reservationIds;

    private Integer enclos_id;

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

    public List<Integer> getReservationIds(){
        return this.reservationIds;
    }

    public void setReservationIds(List<Integer> reservationIds){
        this.reservationIds = reservationIds;
    }

    public Integer getEnclosId(){
        return this.enclos_id;
    }
    
    public void setEnclosId(Integer enclos_id){
        this.enclos_id = enclos_id;
    }


    public static SpectacleResponse convert(Spectacle spectacle){
        SpectacleResponse spectacleResponse = new SpectacleResponse();

        spectacleResponse.setId(spectacle.getId());
        spectacleResponse.setDateDebut(spectacle.getDateDebut());
        spectacleResponse.setHeureDebut(spectacle.getHeureDebut());
        spectacleResponse.setDuree(spectacle.getDuree());
        spectacleResponse.setEnclosId(spectacle.getEnclos().getNumero());
        spectacleResponse.setReservationIds(
            spectacle.getReservations()
                .stream()
                .map(Reservation::getId)
                .toList()
        );

        return spectacleResponse;
    }
}
