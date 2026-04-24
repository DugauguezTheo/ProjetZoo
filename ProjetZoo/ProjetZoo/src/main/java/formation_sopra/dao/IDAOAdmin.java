package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Admin;

public interface IDAOAdmin extends JpaRepository<Admin, Integer> {

}
