package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Soin;

public interface IDAOSoin extends JpaRepository<Soin, Integer> {

}
