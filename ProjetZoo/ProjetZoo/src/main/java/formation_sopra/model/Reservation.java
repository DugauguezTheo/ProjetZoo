package formation_sopra.model;

import java.time.LocalDate;
import java.util.List;

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
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "date_visite")
    LocalDate dateVisite;

    @Column(name = "date_reservation")
    LocalDate dateReservation;

    @Column(nullable = false)
    Double prix;

    @Column(name = "nb_personne")
    Integer nbPersonne;

    @ManyToOne
    @JoinColumn(nullable = false, name = "visiteur_id")
    Visiteur visiteur;

    @ManyToMany(mappedBy="spectacle_reservation")
	protected List<Spectacle> spectacles;

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
