package formation_sopra.controller;

import java.util.List;
import formation_sopra.controller.dto.response.CompteResponse;
import formation_sopra.exception.CompteNotFoundException;

import formation_sopra.dao.IDAOCompte;
import formation_sopra.model.Compte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/compte") 
public class CompteController {

	    private static Logger log = LoggerFactory.getLogger(CompteController.class);

	    private final IDAOCompte daoCompte;

	    public CompteController(IDAOCompte daoCompte) {
	        this.daoCompte = daoCompte;
	    }

		@PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/{id}")
	    public CompteResponse getCompteById(@PathVariable Integer id) {
			log.debug("Recherche du compte {}", id);
	        return this.daoCompte.findById(id)
	                .map(CompteResponse::convert)
	                .orElseThrow(CompteNotFoundException::new)
	                ;
	    }

		@PreAuthorize("hasRole('ADMIN')")
	    @GetMapping 
	    public List<CompteResponse> getAllComptes() {
			log.debug("Recherche de tous les comptes enregistrés");
	        return this.daoCompte.findAll()
	                .stream()
	                .map(CompteResponse::convert)
	                .toList()
	                ;
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping("/{id}")
	    public Compte modifyCompte(@PathVariable Integer id, @ModelAttribute Compte compte){
	        log.debug("Modification du compte {}", id);
			return this.daoCompte.save(compte);
	        
	    }

	  
	    @PostMapping
	    public Compte createCompte(@RequestBody Compte compte) {
	        log.debug("Création du compte {}", compte.getId());
			this.daoCompte.save(compte);
	        return compte;
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/{id}/delete")
	    public boolean deleteCompteById(@PathVariable Integer id) {
	        try {
	            log.debug("Suppression du compte {} ", id);
	            this.daoCompte.deleteById(id);
	        }

	        catch (Exception ex) {
	            log.error("Une erreur est survenue pendant la suppression du compte {} : {}", id, ex.getMessage());

	            return false;
	        }

	        return true;
	    }
	}