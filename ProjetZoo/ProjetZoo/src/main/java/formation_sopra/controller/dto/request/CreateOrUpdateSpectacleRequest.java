package formation_sopra.controller.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import formation_sopra.model.Enclos;

public class CreateOrUpdateSpectacleRequest {

    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private Integer duree;

    private List<Integer> reservationIds;

    private Enclos enclos;

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

    public Enclos getEnclos() {
        return enclos;
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

    public void setEnclos(Enclos enclos) {
        this.enclos = enclos;
    }
    
}
