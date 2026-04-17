package formation_sopra.controller;

import formation_sopra.controller.dto.response.ArticleResponse;
import formation_sopra.dao.IDAOArticle;
import formation_sopra.exception.ArticleNotFoundException;
import formation_sopra.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article") 
public class ArticleController {

    private static Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final IDAOArticle daoArticle;

    public ArticleController(IDAOArticle daoArticle) {
        this.daoArticle = daoArticle;
    }

    //@Autowired
    //IDAOArticle daoArticle;

    @GetMapping("/{id}")
    public ArticleResponse getArticleById(@PathVariable Integer id) {
        return this.daoArticle.findById(id)
                .map(ArticleResponse::convert)
                .orElseThrow(ArticleNotFoundException::new)
                ;
    }
    
    @GetMapping("/{libelle}")
    public List<ArticleResponse> getArticlesByLibelleContaining(@PathVariable String libelle) {
        return this.daoArticle.findByLibelleContaining(libelle)
        		.stream()
                .map(ArticleResponse::convert)
                .toList()
                ;
    }

    @GetMapping //la seule fonction que tout le monde devrait avoir acces
    public List<ArticleResponse> getAllArticles() {
        return this.daoArticle.findAll()
                .stream()
                .map(ArticleResponse::convert)
                .toList()
                ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Article modifyArticle(@PathVariable Integer id,@ModelAttribute Article article){
        this.daoArticle.save(article);
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        this.daoArticle.save(article);
        return article;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public boolean deleteArticleById(@PathVariable Integer id) {
        try {
            log.debug("Suppression de l'article {} ...", id);
            this.daoArticle.deleteById(id);
        }

        catch (Exception ex) {
            log.error("Une erreur est survenue pendant la suppression de l'article {} : {}", id, ex.getMessage());

            return false;
        }

        return true;
    }
}
