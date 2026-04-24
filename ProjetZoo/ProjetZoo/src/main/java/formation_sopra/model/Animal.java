package formation_sopra.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 15)
    private String prenom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_naissance")
    private LocalDate dateNaissance;

    @OneToOne
    private Enclos enclos;

    @Enumerated(EnumType.STRING)
    @OneToOne //besoin de preciser pour un enum?
    private Espece espece;

    @ManyToOne
    @JoinColumn(name = "veterinaire")
    private Veterinaire veterinaire;//faire import quand c'est bon

    @OneToMany(mappedBy = "soin")
    @JoinColumn(name="soin")
    private List<Soin>soins; //faire import quand c'est bon

    public Animal() {
    }

    public Animal(String prenom, LocalDate dateNaissance, Enclos enclos, Espece espece, Veterinaire veterinaire, List<Soin> soins) {
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.enclos = enclos;
        this.espece = espece;
        this.veterinaire = veterinaire;
        this.soins = soins;
    }

    public Animal(Integer id, String prenom, LocalDate dateNaissance, Enclos enclos, Espece espece, Veterinaire veterinaire, List<Soin> soins) {
        this.id = id;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.enclos = enclos;
        this.espece = espece;
        this.veterinaire = veterinaire;
        this.soins = soins;
    }

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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Enclos getEnclos() {
        return enclos;
    }

    public void setEnclos(Enclos enclos) {
        this.enclos = enclos;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    public Veterinaire getVeterinaire() {
        return veterinaire;
    }

    public void setVeterinaire(Veterinaire veterinaire) {
        this.veterinaire = veterinaire;
    }

    public List<Soin> getSoins() {
        return soins;
    }

    public void setSoins(List<Soin> soins) {
        this.soins = soins;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", enclos=" + enclos +
                ", espece=" + espece +
                ", veterinaire=" + veterinaire +
                '}';
    }
}
