package formation_sopra.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="enclos")
public class Enclos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    @Column(nullable = false, length = 15)
    private String biome;
    //@Digits(fraction = 0) //Annotation non fonctionnelle --> ajouter une dependance maven?
    private int capacite;

    @Enumerated(EnumType.STRING)
    private Espece espece;

    @OneToMany(mappedBy = "enclos")
    private List<Animal> animals;

    @OneToMany(mappedBy = "enclos")
    @JoinColumn(name="enclos")
    private List<Spectacle> spectacles; //faire import quand c'est bon

    public Enclos() {
    }

    public Enclos(String biome, int capacite, Espece espece, List<Animal> animals, List<Spectacle> spectacles) {
        this.biome = biome;
        this.capacite = capacite;
        this.espece = espece;
        this.animals = animals;
        this.spectacles = spectacles;
    }

    public Enclos(Integer numero, String biome, int capacite, Espece espece, List<Animal> animals, List<Spectacle> spectacles) {
        this.numero = numero;
        this.biome = biome;
        this.capacite = capacite;
        this.espece = espece;
        this.animals = animals;
        this.spectacles = spectacles;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getBiome() {
        return biome;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Spectacle> getSpectacles() {
        return spectacles;
    }

    public void setSpectacles(List<Spectacle> spectacles) {
        this.spectacles = spectacles;
    }

    @Override
    public String toString() {
        return "Enclos{" +
                "numero=" + numero +
                ", biome='" + biome + '\'' +
                ", capacite=" + capacite +
                ", espece=" + espece +
                '}';
    }
}
