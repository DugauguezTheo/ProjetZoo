package formation_sopra.controller.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import formation_sopra.model.Reservation;
import formation_sopra.model.Spectacle;
import formation_sopra.model.Visiteur;

public class ReservationResponse {

    private Integer id;
    private LocalDate dateReservation;
    private LocalDate dateVisite;
    private Double prix;
    private Integer nbPersonne;
    private Integer visiteur_id;

    private List<Integer> spectacleIds;

    

    public Integer getId() {
        return id;
    }



    public LocalDate getDateReservation() {
        return dateReservation;
    }



    public LocalDate getDateVisite() {
        return dateVisite;
    }



    public Double getPrix() {
        return prix;
    }



    public Integer getNbPersonne() {
        return nbPersonne;
    }



    public Integer getVisiteurId() {
        return visiteur_id;
    }

    public List<Integer> getSpectacleIds() {
        return spectacleIds;
    }


    public void setId(Integer id) {
        this.id = id;
    }



    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }



    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }



    public void setPrix(Double prix) {
        this.prix = prix;
    }



    public void setNbPersonne(Integer nbPersonne) {
        this.nbPersonne = nbPersonne;
    }



    public void setVisiteurId(Integer visiteur_id) {
        this.visiteur_id = visiteur_id;
    }
    
    public void setSpectacleIds(List<Integer> spectacleIds) {
        this.spectacleIds = spectacleIds;
    }


    public static ReservationResponse convert(Reservation reservation){
        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse.setId(reservation.getId());
        reservationResponse.setDateReservation(reservation.getDateReservation());
        reservationResponse.setDateVisite(reservation.getDateVisite());
        reservationResponse.setPrix(reservation.getPrix());
        reservationResponse.setNbPersonne(reservation.getNbPersonne());
        reservationResponse.setVisiteurId(reservation.getVisiteur().getId());
        reservationResponse.setSpectacleIds(
        
        reservation.getSpectacles()
            .stream()
            .map(Spectacle::getId)
            .toList()
        );


        return reservationResponse;
    }
}
