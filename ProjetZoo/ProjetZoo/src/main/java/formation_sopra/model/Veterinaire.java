package formation_sopra.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="veterinaire")
public class Veterinaire extends Compte {

    @OneToMany(mappedBy = "veterinaire")
    private List<Soin> soins;

    public Veterinaire() {
    }

    public List<Soin> getSoins() {
        return soins;
    }


    public void setSoins(List<Soin> soins) {
        this.soins = soins;
    }

}
