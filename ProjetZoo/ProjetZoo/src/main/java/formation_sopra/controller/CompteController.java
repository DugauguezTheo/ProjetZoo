package formation_sopra.controller;

import java.util.List;

import formation_sopra.controller.dto.response.CompteResponse;
import formation_sopra.exception.CompteNotFoundException;

import formation_sopra.dao.IDAOCompte;
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
	  
}