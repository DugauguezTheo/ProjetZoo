package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Compte;

public interface IDAOCompte extends JpaRepository<Compte, Integer> {

    public Compte findByLogin(String login);

}
