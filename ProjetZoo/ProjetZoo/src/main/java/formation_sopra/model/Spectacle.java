package formation_sopra.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="spectacle")
public class Spectacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "date_debut", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateDebut;

    @Column(name = "heure_debut", nullable = false)
    @DateTimeFormat(pattern = "HH-mm-ss")
    LocalTime heureDebut;

    @Column(nullable = false)
    Integer duree;

    @ManyToMany(mappedBy="spectacles")
	protected List<Reservation> reservations;

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
