package formation_sopra.controller;

import formation_sopra.dao.IDAOAchat;
import formation_sopra.model.Achat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Les autorisations doivent être revues :
 * 		- un visiteur doit pouvoir voir ses achats, mais pas ceux des autres
 * 		- un visiteur peut ajouter un achat, si c'est pour lui ? Ou on gere ça cote front ?
 * 		- l'admin doit pouvoir afficher tous les achats, mais c'est le seul
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
    

    /*
    @GetMapping("/{id}")
    public String getAchatById(@PathVariable Integer id) {
        //return daoAchat.findById(id).orElse(null);
        return null;
    }

    @GetMapping
    public List<Achat> getAllAchats() {
        return daoAchat.findAll();
    }

    @PostMapping("/{id}")
    public Achat modifyAchat(@PathVariable Integer id,@ModelAttribute Achat achat){
        this.daoAchat.save(achat);
        return null;
    }

    @PostMapping
    public Achat createAchat(@RequestBody Achat achat) {
        this.daoAchat.save(achat);
        return achat;
    }

    @DeleteMapping("/{id}/delete")
    public Achat deleteAchatById(@PathVariable Integer id) {
        this.daoAchat.deleteById(id);
        return null;
    }
    */
}
