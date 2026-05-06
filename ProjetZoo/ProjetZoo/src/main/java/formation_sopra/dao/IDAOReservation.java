package formation_sopra.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Reservation;
import formation_sopra.model.Visiteur;

public interface IDAOReservation extends JpaRepository<Reservation,Integer> {


    @Query("select r from Reservation r join r.spectacles s where s.id = :spectacle_id")
    public List<Reservation> findAllBySpectacleId(@Param("spectacle_id") Integer spectacle_id);

    public List<Reservation> findAllByVisiteur( Visiteur visiteur);

    @Query("SELECT r FROM Reservation r LEFT JOIN FETCH r.spectacles WHERE r.visiteur.id = :id")
    public List<Reservation> findAllWithSpectaclesByIdVisiteur(@Param("id") Integer id);

    @Query("Select r from Reservation r LEFT JOIN FETCH r.spectacles")
    public List<Reservation> findAllWithSpectacles();

    @Query("Select r from Reservation r left join fetch r.spectacles where r.id = :id")
    public Optional<Reservation> findByIdWithSpectacles(@Param("id") Integer id);
    
}
