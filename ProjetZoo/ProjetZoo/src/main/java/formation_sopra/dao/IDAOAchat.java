package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Achat;

public interface IDAOAchat extends JpaRepository<Achat,Integer> {
}
