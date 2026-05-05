package formation_sopra.dao;

import formation_sopra.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDAOAnimal extends JpaRepository<Animal,Integer> {

    @Query("SELECT a FROM Animal a LEFT JOIN FETCH a.soins WHERE a.id = :id")
    Optional<Animal> findByIdWithSoins(@Param("id") Integer id);

    @Query("SELECT a FROM Animal a WHERE a.enclos.numero = :id")
    List<Animal> findAllByEnclosId(@Param("id") Integer id);
}
