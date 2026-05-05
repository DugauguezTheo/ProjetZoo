package formation_sopra.controller;

import java.util.ArrayList;
import java.util.List;

import formation_sopra.controller.dto.request.CreateOrUpdateVisiteurRequest;
import formation_sopra.controller.dto.response.VisiteurResponse;
import formation_sopra.dao.IDAOAchat;
import formation_sopra.dao.IDAOVisiteur;
import formation_sopra.exception.CompteNotFoundException;
import formation_sopra.model.Achat;
import formation_sopra.model.Adresse;
import formation_sopra.model.Visiteur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/visiteur") 
public class VisiteurController {

	    private static Logger log = LoggerFactory.getLogger(VisiteurController.class);

	    private final IDAOVisiteur daoVisiteur;
		private final IDAOAchat daoAchat;

	    public VisiteurController(IDAOVisiteur daoVisiteur, IDAOAchat daoAchat) {
	        this.daoVisiteur = daoVisiteur;
			this.daoAchat = daoAchat;
	    }

		@PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/{id}")
	    public VisiteurResponse getVisiteurById(@PathVariable Integer id) {
			log.debug("Recherche du visiteur {}", id);
	        return this.daoVisiteur.findById(id)
	                .map(VisiteurResponse::convert)
	                .orElseThrow(CompteNotFoundException::new)
	                ;
	    }

		@PreAuthorize("hasRole('ADMIN')")
	    @GetMapping 
	    public List<VisiteurResponse> getAllVisiteurs() {
			log.debug("Recherche de tous les visiteurs enregistrés");
	        return this.daoVisiteur.findAll()
	                .stream()
	                .map(VisiteurResponse::convert)
	                .toList()
	                ;
	    }

		@GetMapping("/me")
		public VisiteurResponse getMySelfAsVisitor(Authentication auth) {
			Integer id = Integer.parseInt(auth.getName());
			log.debug("Recherche du compte n°{} (compte visiteur) ...", id);

			return daoVisiteur.findById(id)
				.map(VisiteurResponse::convert)
				.orElseThrow(CompteNotFoundException::new)
				;
		}

	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping("/{id}")
	    public VisiteurResponse modifyVisiteur(@PathVariable Integer id, @RequestBody CreateOrUpdateVisiteurRequest request){
	        log.debug("Modification du visiteur {}", id);

			Visiteur visiteur = new Visiteur();
			List<Achat> achats = request.achatsIds().stream()
				.map(idAchat -> this.daoAchat.findById(idAchat).orElse(null))
				.toList();
			Adresse adresse = new Adresse(request.numeroVoie(), request.voie(), request.ville(), request.cp());

			visiteur.setLogin(request.login());
			visiteur.setPassword(request.password());
			visiteur.setNom(request.nom());
			visiteur.setPrenom(request.prenom());
			visiteur.setDateNaissance(request.dateNaissance());
			visiteur.setPointsFidelites(request.pointsFidelite());
			visiteur.setAchats(achats);
			visiteur.setAdresse(adresse);

			visiteur = this.daoVisiteur.save(visiteur);

			return VisiteurResponse.convert(visiteur);
	        
	    }

	  
	    @PostMapping
	    public VisiteurResponse createVisiteur(@RequestBody CreateOrUpdateVisiteurRequest request) {
	        log.debug("Création d'un visiteur...");

			Visiteur visiteur = new Visiteur();

			Adresse adresse = new Adresse(request.numeroVoie(), request.voie(), request.ville(), request.cp());

			visiteur.setLogin(request.login());
			visiteur.setPassword(request.password());
			visiteur.setNom(request.nom());
			visiteur.setPrenom(request.prenom());
			visiteur.setDateNaissance(request.dateNaissance());
			visiteur.setPointsFidelites(0);
			visiteur.setAchats(new ArrayList<>());
			visiteur.setAdresse(adresse);

			visiteur = this.daoVisiteur.save(visiteur);

			return VisiteurResponse.convert(visiteur);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/{id}/delete")
	    public boolean deleteVisiteurById(@PathVariable Integer id) {
	        try {
	            log.debug("Suppression du visiteur {} ", id);
	            this.daoVisiteur.deleteById(id);
	        }

	        catch (Exception ex) {
	            log.error("Une erreur est survenue pendant la suppression du visiteur {} : {}", id, ex.getMessage());

	            return false;
	        }

	        return true;
	    }
	}