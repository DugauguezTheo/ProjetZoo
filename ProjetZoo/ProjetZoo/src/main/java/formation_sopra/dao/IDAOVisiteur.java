package formation_sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Achat;
import formation_sopra.model.Visiteur;

public interface IDAOVisiteur extends JpaRepository<Visiteur, Integer> {

    @Query("SELECT v from Visiteur v LEFT JOIN FETCH v.achats WHERE v.id = :id")
    public Visiteur findByIdWithAchats(@Param("id") Integer id);

    @Query("SELECT a from Achat a WHERE a.visiteur.id = :id ORDER BY a.dateAchat DESC, a.reference DESC LIMIT 1")
    public Achat findLastAchat(@Param("id") Integer id);
}
