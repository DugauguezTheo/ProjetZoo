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
    public String getAchatById(@PathVariable Integer id) {
        //return daoAchat.findById(id).orElse(null);
        return null;
    }

    @GetMapping("/visiteur")
    public List<Achat> getAllAchatsByIdVisiteur(Authentication auth) {
        return daoAchat.findAllByVisiteurId(Integer.parseInt(auth.getName()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/visiteur/{idVisiteur}")
    public List<Achat> getAllAchatsByIdVisiteur(@PathVariable Integer idVisiteur) {
        return daoAchat.findAllByVisiteurId(idVisiteur);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Achat> getAllAchats() {
        return daoAchat.findAll();
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Achat modifyAchat(@PathVariable Integer id,@ModelAttribute Achat achat){
        return this.daoAchat.save(achat);
    }

    @PostMapping
    public Achat createAchat(@RequestBody Achat achat) {
        this.daoAchat.save(achat);
        return achat;
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public void deleteAchatById(@PathVariable Integer id) {
        this.daoAchat.deleteById(id);
    }
    
}
