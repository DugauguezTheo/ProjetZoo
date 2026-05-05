package formation_sopra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Soin;

public interface IDAOSoin extends JpaRepository<Soin, Integer> {

    @Query("SELECT s from Soin s WHERE s.animal.id = :id")
    public List<Soin> findAllByAnimalId(@Param("id") Integer id);
}
