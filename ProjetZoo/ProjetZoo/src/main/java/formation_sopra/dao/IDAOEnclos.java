package formation_sopra.dao;

import formation_sopra.model.Animal;
import formation_sopra.model.Enclos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IDAOEnclos extends JpaRepository<Enclos,Integer> {

    @Query("SELECT e FROM Enclos e LEFT JOIN FETCH e.animals WHERE e.id = :id")
    Optional<Enclos> findByIdWithAnimal(@Param("id") Integer id);

    @Query("SELECT e FROM Enclos e LEFT JOIN FETCH e.spectacles WHERE e.id = :id")
    Optional<Enclos> findByIdWithSpectacle(@Param("id") Integer id);
}
