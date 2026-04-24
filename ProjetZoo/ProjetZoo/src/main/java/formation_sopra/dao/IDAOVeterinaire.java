package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Veterinaire;

public interface IDAOVeterinaire extends JpaRepository<Veterinaire, Integer> {

}
