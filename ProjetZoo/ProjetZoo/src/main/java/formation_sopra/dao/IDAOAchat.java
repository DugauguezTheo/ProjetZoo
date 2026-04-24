package formation_sopra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Achat;

public interface IDAOAchat extends JpaRepository<Achat,Integer> {
    // Afficher tous les achats d'un visiteur donné
    @Query("SELECT a from Achat a where a.visiteur.id=:id")
    public List<Achat> findAllByVisiteurId(@Param("id") Integer IdVisiteur);
}
