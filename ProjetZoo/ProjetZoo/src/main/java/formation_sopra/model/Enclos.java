package formation_sopra.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="enclos")
public class Enclos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    @Column(nullable = false, length = 15)
    private String biome;
    
    @Column(columnDefinition = "Decimal(2,0)")
    private int capacite;

//    @Enumerated(EnumType.STRING)
//    private Espece espece;

    @OneToMany(mappedBy = "enclos")
    private List<Animal> animals;

    @OneToMany(mappedBy = "enclos")
    private List<Spectacle> spectacles;

    public Enclos() {
    }

    public Enclos(String biome, int capacite) {
        this.biome = biome;
        this.capacite = capacite;
    }

    public Enclos(Integer numero, String biome, int capacite) {
        this.numero = numero;
        this.biome = biome;
        this.capacite = capacite;
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

//    public Espece getEspece() {
//        return espece;
//    }
//
//    public void setEspece(Espece espece) {
//        this.espece = espece;
//    }

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
                '}';
    }
}
