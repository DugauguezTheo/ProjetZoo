package formation_sopra.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_naissance")
    private LocalDate dateNaissance;

    @OneToOne
    private Enclos enclos;

    @Enumerated(EnumType.STRING)
    private Espece espece;

    @OneToMany(mappedBy = "animal")
    private List<Soin> soins; 

    public Animal() {
    }

    public Animal(String prenom, LocalDate dateNaissance, Enclos enclos, Espece espece) {
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.enclos = enclos;
        this.espece = espece;
    }

    public Animal(Integer id, String prenom, LocalDate dateNaissance, Enclos enclos, Espece espece) {
        this.id = id;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.enclos = enclos;
        this.espece = espece;
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
                '}';
    }
}
