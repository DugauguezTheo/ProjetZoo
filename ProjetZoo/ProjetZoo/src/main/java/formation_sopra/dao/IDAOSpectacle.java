package formation_sopra.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Enclos;
import formation_sopra.model.Spectacle;

public interface IDAOSpectacle extends JpaRepository<Spectacle,Integer> {

    @Query("SELECT DISTINCT s FROM Spectacle s LEFT JOIN FETCH s.reservations")
    List<Spectacle> findAllWithReservation();
        
    @Query("SELECT DISTINCT s FROM Spectacle s LEFT JOIN FETCH s.reservations")
    Optional<Spectacle> findByIdWithReservation(@Param("id") Integer id);

    @Query("SELECT DISTINCT s FROM Spectacle s LEFT JOIN FETCH s.reservations WHERE s.enclos = :enclos")
    List<Spectacle> findAllByEnclosWithReservation(@Param("enclos") Enclos enclos);

    @Query("SELECT s FROM Spectacle s WHERE s.enclos.numero = :id")
    List<Spectacle> findAllByEnclosId(@Param("id") Integer id);
    
}
