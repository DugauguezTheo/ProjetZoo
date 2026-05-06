package formation_sopra.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Enclos;
import formation_sopra.model.Spectacle;

public interface IDAOSpectacle extends JpaRepository<Spectacle,Integer> {

    @Query("SELECT DISTINCT s FROM Spectacle s LEFT JOIN FETCH s.reservations")
    List<Spectacle> findAllWithReservations();
        
    @Query("SELECT DISTINCT s FROM Spectacle s LEFT JOIN FETCH s.reservations")
    Optional<Spectacle> findByIdWithReservations(@Param("id") Integer id);

    @Query("SELECT DISTINCT s FROM Spectacle s LEFT JOIN FETCH s.reservations WHERE s.enclos = :enclos")
    List<Spectacle> findAllByEnclosWithReservations(@Param("enclos") Enclos enclos);

    @Query("SELECT s FROM Spectacle s WHERE s.enclos.numero = :id")
    List<Spectacle> findAllByEnclosId(@Param("id") Integer id);
    
    @Query("SELECT s FROM Spectacle s WHERE s.dateDebut BETWEEN :debut AND :fin")
    List<Spectacle> findAllBetween(@Param("debut") LocalDate dateDebut, @Param("fin") LocalDate dateFin);

    @Query("SELECT s FROM Spectacle s WHERE s.id = :id AND s.dateDebut BETWEEN :debut AND :fin")
    List<Spectacle> findAllByEnclosIdBetween(@Param("id") Integer id, @Param("debut") LocalDate dateDebut, @Param("fin") LocalDate dateFin);
}
