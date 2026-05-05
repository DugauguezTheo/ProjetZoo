package formation_sopra.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.model.Article;

public interface IDAOArticle extends JpaRepository<Article,Integer> {
	
	public List<Article> findByLibelleContaining(String libelle);

	@Query("SELECT a from Article a LEFT JOIN FETCH a.ventes WHERE a.id = :id")
	Optional<Article> findByIdWithVentes(@Param("id") Integer id);

}
