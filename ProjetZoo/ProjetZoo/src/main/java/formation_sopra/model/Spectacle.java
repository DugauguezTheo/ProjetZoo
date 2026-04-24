package formation_sopra.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="spectacle")
public class Spectacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "date_debut", nullable = false)
    LocalDate dateDebut;

    @Column(name = "heure_debut", nullable = false)
    LocalTime heureDebut;

    @Column(nullable = false)
    Integer duree;

	@ManyToMany
	@JoinTable(
            name="spectacle_reservation",
            joinColumns = @JoinColumn(name = "spectacle_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"spectacle_id", "reservation_id"}))
	private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(nullable = false, name = "enclos_id")
    Enclos enclos;

    public Spectacle() {}
    
    public Spectacle(LocalDate dateDebut, LocalTime heureDebut, Integer duree, Enclos enclos) {
        this.dateDebut = dateDebut;
        this.heureDebut = heureDebut;
        this.duree = duree;
        this.enclos = enclos;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Enclos getEnclos() {
        return enclos;
    }

    public void setEnclos(Enclos enclos) {
        this.enclos = enclos;
    }

}
