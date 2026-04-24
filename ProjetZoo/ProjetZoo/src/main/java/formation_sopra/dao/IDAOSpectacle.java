package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Spectacle;

public interface IDAOSpectacle extends JpaRepository<Spectacle,Integer> {
    
}
