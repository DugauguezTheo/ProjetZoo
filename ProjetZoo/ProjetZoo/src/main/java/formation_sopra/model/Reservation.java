package formation_sopra.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_visite")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisite;

    @Column(name = "date_reservation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReservation;

    @Column(nullable = false)
    private Double prix;

    @Column(name = "nb_personne")
    private Integer nbPersonne;

    @ManyToOne
    @JoinColumn(nullable = false, name = "visiteur_id")
    Visiteur visiteur;

   
    @ManyToMany
	@JoinTable(
            name="reservation_spectacle",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "spectacle_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"reservation_id", "spectacle_id"}))
	private List<Spectacle> spectacles;

    public Reservation() {}
    
    public Reservation(Integer id, LocalDate dateVisite, LocalDate dateReservation, Double prix, Integer nbPersonne,
            Visiteur visiteur) {
        this.id = id;
        this.dateVisite = dateVisite;
        this.dateReservation = dateReservation;
        this.prix = prix;
        this.nbPersonne = nbPersonne;
        this.visiteur = visiteur;
    }



    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setNbPersonne(Integer nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public void setSpectacles(List<Spectacle> spectacles) {
        this.spectacles = spectacles;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public LocalDate getDateVisite() {
        return dateVisite;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public Double getPrix() {
        return prix;
    }

    public Integer getNbPersonne() {
        return nbPersonne;
    }    

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public List<Spectacle> getSpectacles() {
        return spectacles;
    }
}
