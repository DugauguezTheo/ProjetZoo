package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Compte;

public interface IDAOCompte extends JpaRepository<Compte, Integer> {

}
