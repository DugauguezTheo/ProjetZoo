package formation_sopra.controller;

import formation_sopra.dao.IDAOAchat;
import formation_sopra.model.Achat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 	- un visiteur peut ajouter un achat, si c'est pour lui : côté front, passer l'idVisiteur en hidden
 * 	
 * L'aspect de la page change surement en fonction des autorisations ?
 */
@RestController
@RequestMapping("/api/achat")
@PreAuthorize("hasRole('ADMIN')")
public class AchatController {

    private static Logger log = LoggerFactory.getLogger(AchatController.class);

    private final IDAOAchat daoAchat;

    public AchatController(IDAOAchat daoAchat) {
        this.daoAchat = daoAchat;
    }
    
    @GetMapping("/{id}")
    public String getAchatById(@PathVariable Integer id) {
        //return daoAchat.findById(id).orElse(null);
        return null;
    }

    @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'Achat', 'read')")
    @GetMapping("/visiteur/{id}")
    public List<Achat> getAllAchatsByIdVisiteur(@PathVariable Integer id) {
        return daoAchat.findAllByVisiteurId(id);
    }


    @GetMapping
    public List<Achat> getAllAchats() {
        return daoAchat.findAll();
    }

    @PutMapping("/{id}")
    public Achat modifyAchat(@PathVariable Integer id,@ModelAttribute Achat achat){
        return this.daoAchat.save(achat);
    }

    @PostMapping
    public Achat createAchat(@RequestBody Achat achat) {
        this.daoAchat.save(achat);
        return achat;
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAchatById(@PathVariable Integer id) {
        this.daoAchat.deleteById(id);
    }
    
}
