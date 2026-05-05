package formation_sopra.dao;

import formation_sopra.model.Animal;
import formation_sopra.model.Enclos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IDAOEnclos extends JpaRepository<Enclos,Integer> {

    @Query("SELECT e FROM Enclos e LEFT JOIN FETCH e.animals WHERE e.numero = :numero")
    Optional<Enclos> findByIdWithAnimal(@Param("numero") Integer numero);

    @Query("SELECT e FROM Enclos e LEFT JOIN FETCH e.spectacles WHERE e.numero = :numero")
    Optional<Enclos> findByIdWithSpectacle(@Param("numero") Integer numero);
}
