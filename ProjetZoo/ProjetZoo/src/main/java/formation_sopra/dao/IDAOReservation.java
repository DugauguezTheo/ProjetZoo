package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Reservation;

public interface IDAOReservation extends JpaRepository<Reservation,Integer> {
    
}
