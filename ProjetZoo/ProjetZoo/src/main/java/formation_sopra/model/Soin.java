package formation_sopra.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Soin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dateSoin;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Veterinaire veterinaire;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Animal animal;

    public Soin() {
    }

    public Soin(LocalDateTime dateSoin, Veterinaire veterinaire, Animal animal) {
        this.dateSoin = dateSoin;
        this.veterinaire = veterinaire;
        this.animal = animal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateSoin() {
        return dateSoin;
    }

    public void setDateSoin(LocalDateTime dateSoin) {
        this.dateSoin = dateSoin;
    }

    public Veterinaire getVeterinaire() {
        return veterinaire;
    }

    public void setVeterinaire(Veterinaire veterinaire) {
        this.veterinaire = veterinaire;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }


}
