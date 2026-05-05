package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateArticleRequest;
import formation_sopra.controller.dto.response.ArticleResponse;
import formation_sopra.controller.dto.response.ArticleWithVentesResponse;
import formation_sopra.dao.IDAOArticle;
import formation_sopra.exception.ArticleNotFoundException;
import formation_sopra.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/{id}")
    public ArticleResponse getArticleById(@PathVariable Integer id) {
        log.debug("Recherche de l'article n°{} ...", id);
        return this.daoArticle.findById(id)
                .map(ArticleResponse::convert)
                .orElseThrow(ArticleNotFoundException::new)
                ;
    }
    
    @GetMapping("/by-libelle/{libelle}")
    public List<ArticleResponse> getArticlesByLibelleContaining(@PathVariable String libelle) {
        log.debug("Recherche des articles dont le libelle contient {} ...", libelle);
        return this.daoArticle.findByLibelleContaining(libelle)
        		.stream()
                .map(ArticleResponse::convert)
                .toList()
                ;
    }

    @GetMapping("/{id}/ventes")
    public ArticleWithVentesResponse getArticleByIdWithVentes(@PathVariable Integer id) {
        log.debug("Recherche de l'article n°{} ...", id);
        return this.daoArticle.findByIdWithVentes(id)
                .map(ArticleWithVentesResponse::convert)
                .orElseThrow(ArticleNotFoundException::new)
                ;
    }
    
    @GetMapping("/by-libelle/{libelle}/ventes")
    public List<ArticleWithVentesResponse> getArticlesWithVentesByLibelleContaining(@PathVariable String libelle) {
        log.debug("Recherche des articles dont le libelle contient {} ...", libelle);
        return this.daoArticle.findByLibelleContaining(libelle)
        		.stream()
                .map(ArticleWithVentesResponse::convert)
                .toList()
                ;
    }

    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        log.debug("Recherche de tous les articles ...");
        return this.daoArticle.findAll()
                .stream()
                .map(ArticleResponse::convert)
                .toList()
                ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ArticleResponse modifyArticle(@PathVariable Integer id, @RequestBody CreateOrUpdateArticleRequest request){

        log.debug("Modification de l'article {} ({}) ...", id, request.getLibelle());
        Article article = new Article();

        article.setLibelle(request.getLibelle());
        article.setPrix(request.getPrix());
        article.setQuantiteStock(request.getQuantiteStock());

        article = this.daoArticle.save(article);

        return ArticleResponse.convert(article);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ArticleResponse createArticle(@RequestBody CreateOrUpdateArticleRequest request) {
        
        log.debug("Creation d'un article appelé {} ...", request.getLibelle());
        Article article = new Article();

        article.setLibelle(request.getLibelle());
        article.setPrix(request.getPrix());
        article.setQuantiteStock(request.getQuantiteStock());

        article = this.daoArticle.save(article);

        return ArticleResponse.convert(article);
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
