package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Visiteur;

public interface IDAOVisiteur extends JpaRepository<Visiteur, Integer> {

}
