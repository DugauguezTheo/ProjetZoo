package formation_sopra.controller;

import formation_sopra.dao.IDAOAchat;
import formation_sopra.model.Achat;
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

    public AchatController(IDAOAchat daoAchat) {
        this.daoAchat = daoAchat;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Achat getAchatById(@PathVariable Integer id) {
        log.debug("Recherche de l'achat {}", id);
        return daoAchat.findById(id).orElse(null);
    }

    /*
     Un visiteur connecté peut, grâce à son authentification,
     lister tous les achats qu'il a enregistrés (l'authentification contient son id)
    */
    @GetMapping("/visiteur")
    public List<Achat> getAllAchatsByIdVisiteur(Authentication auth) {
        Integer id = Integer.parseInt(auth.getName());
        log.debug("Recherche des achats du visiteur avec l'id {}", id);
        return daoAchat.findAllByVisiteurId(id);
    }

    
    // L'admin peut lister tous les achats enregistrés par le visiteur de son choix
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/visiteur/{idVisiteur}")
    public List<Achat> getAllAchatsByIdVisiteur(@PathVariable Integer idVisiteur) {
        log.debug("Recherche en tant qu'admin des achats du visiteur avec l'id {}", idVisiteur);
        return daoAchat.findAllByVisiteurId(idVisiteur);
    }

    // L'admin peut lister tous les achats enregistrés, par tous les visiteurs
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Achat> getAllAchats() {
        log.debug("Recherche de TOUS les achats enregistrés");
        return daoAchat.findAll();
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Achat modifyAchat(@PathVariable Integer id, @ModelAttribute Achat achat){
        log.debug("Modification de l'achat n°{}", id);
        return this.daoAchat.save(achat);
    }

    @PostMapping
    public Achat createAchat(@RequestBody Achat achat) {
        log.debug("Achat du produit {} ({}) par le visiteur {}", achat.getArticle().getId(), achat.getArticle().getLibelle(), achat.getVisiteur().getId());
        this.daoAchat.save(achat);
        return achat;
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public void deleteAchatById(@PathVariable Integer id) {
        log.debug("Suppression de l'article {}", id);
        this.daoAchat.deleteById(id);
    }
    
}
