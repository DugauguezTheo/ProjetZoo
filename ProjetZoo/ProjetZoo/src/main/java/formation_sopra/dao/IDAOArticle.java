package formation_sopra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation_sopra.model.Article;

public interface IDAOArticle extends JpaRepository<Article,Integer> {
	
	public List<Article> findByLibelleContaining(String libelle);
}
