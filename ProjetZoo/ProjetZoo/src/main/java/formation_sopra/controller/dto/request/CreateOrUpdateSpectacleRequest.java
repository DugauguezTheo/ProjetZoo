package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class CreateOrUpdateSpectacleRequest {

    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private Integer duree;

    private List<Integer> reservationIds;

    private Integer enclos_id;

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    public Integer getDuree() {
        return duree;
    }
    public List<Integer> getReservationIds() {
        return reservationIds;
    }

    public Integer getEnclosId() {
        return enclos_id;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    public void setDuree(Integer duree) {
        this.duree = duree;
    }
    public void setReservationIds(List<Integer> reservationIds) {
        this.reservationIds = reservationIds;
    }

    public void setEnclosId(Integer enclos_id) {
        this.enclos_id = enclos_id;
    }
    
}
