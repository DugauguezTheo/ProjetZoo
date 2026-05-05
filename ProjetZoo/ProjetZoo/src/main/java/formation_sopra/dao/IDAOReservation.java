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




    
}
