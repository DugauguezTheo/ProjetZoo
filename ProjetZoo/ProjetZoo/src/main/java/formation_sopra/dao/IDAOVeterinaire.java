package formation_sopra.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Soin;
import formation_sopra.model.Veterinaire;

public interface IDAOVeterinaire extends JpaRepository<Veterinaire, Integer> {

    @Query("SELECT v FROM Veterinaire v LEFT JOIN FETCH v.soins WHERE v.id = :id")
    Optional<Veterinaire> findByIdWithSoins(@Param("id") Integer id);

    @Query("SELECT s from Soin s WHERE s.veterinaire.id = :id ORDER BY s.dateSoin DESC, s.id DESC LIMIT 1")
    public Soin findLastSoin(@Param("id") Integer id);

}
