package formation_sopra.dao;

import formation_sopra.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDAOAnimal extends JpaRepository<Animal,Integer> {
}
