package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateAchatRequest;
import formation_sopra.controller.dto.response.AchatResponse;
import formation_sopra.dao.IDAOAchat;
import formation_sopra.dao.IDAOArticle;
import formation_sopra.dao.IDAOVisiteur;
import formation_sopra.model.Achat;
import formation_sopra.model.Article;
import formation_sopra.model.Visiteur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 	- un visiteur peut ajouter un achat, si c'est pour lui : côté front, passer l'idVisiteur en hidden
 * 	
 * L'aspect de la page change surement en fonction des autorisations ?
 */
@RestController
@RequestMapping("/api/achat")
public class AchatController {

    private static Logger log = LoggerFactory.getLogger(AchatController.class);

    private final IDAOAchat daoAchat;
    private final IDAOArticle daoArticle;
    private final IDAOVisiteur daoVisiteur;

    public AchatController(IDAOAchat daoAchat, IDAOArticle daoArticle, IDAOVisiteur daoVisiteur) {
        this.daoAchat = daoAchat;
        this.daoArticle = daoArticle;
        this.daoVisiteur = daoVisiteur;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public AchatResponse getAchatById(@PathVariable Integer id) {
        log.debug("Recherche de l'achat {}", id);
        return AchatResponse.convert(daoAchat.findById(id).orElse(null));
    }

    /*
     Un visiteur connecté peut, grâce à son authentification,
     lister tous les achats qu'il a enregistrés (l'authentification contient son id)
    */
    @GetMapping("/visiteur")
    public List<AchatResponse> getAllAchatsByIdVisiteur(Authentication auth) {
        Integer id = Integer.parseInt(auth.getName());
        log.debug("Recherche des achats du visiteur avec l'id {}", id);
        return daoAchat.findAllByVisiteurId(id).stream()
            .map(a -> AchatResponse.convert(a))
            .toList();
    }

    
    // L'admin peut lister tous les achats enregistrés par le visiteur de son choix
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/visiteur/{idVisiteur}")
    public List<AchatResponse> getAllAchatsByIdVisiteur(@PathVariable Integer idVisiteur) {
        log.debug("Recherche en tant qu'admin des achats du visiteur avec l'id {}", idVisiteur);
        return daoAchat.findAllByVisiteurId(idVisiteur).stream()
            .map(a -> AchatResponse.convert(a))
            .toList();
    }

    // L'admin peut lister tous les achats enregistrés, par tous les visiteurs
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<AchatResponse> getAllAchats() {
        log.debug("Recherche de TOUS les achats enregistrés");
        return daoAchat.findAll().stream()
            .map(a -> AchatResponse.convert(a))
            .toList();
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public AchatResponse modifyAchat(@PathVariable Integer id, @RequestBody CreateOrUpdateAchatRequest request){
        log.debug("Modification de l'achat n°{}", id);

        Achat achat = new Achat();

        achat.setArticle(this.daoArticle.findById(request.idArticle()).orElse(null));
        achat.setQuantite(request.quantite());
        achat.setPrixReel(request.prixReel());
        achat.setDateAchat(request.dateAchat());
        achat.setVisiteur(this.daoVisiteur.findById(request.idVisiteur()).orElse(null));

        return AchatResponse.convert(this.daoAchat.save(achat));
    }

    @PostMapping
    public AchatResponse createAchat(@RequestBody CreateOrUpdateAchatRequest request) {
        
        Article article = this.daoArticle.findById(request.idArticle()).orElse(null);
        Visiteur visiteur =this.daoVisiteur.findById(request.idVisiteur()).orElse(null);

        log.debug("Achat du produit {} ({}) par le visiteur {}", request.idArticle(), article.getLibelle(), request.idVisiteur());
        
        Achat achat = new Achat();
        
        achat.setArticle(article);
        achat.setQuantite(request.quantite());
        achat.setPrixReel(request.prixReel());
        achat.setDateAchat(request.dateAchat());
        achat.setVisiteur(visiteur);

        achat = this.daoAchat.save(achat);
        return AchatResponse.convert(achat);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public void deleteAchatById(@PathVariable Integer id) {
        log.debug("Suppression de l'article {}", id);
        this.daoAchat.deleteById(id);
    }
    
}
