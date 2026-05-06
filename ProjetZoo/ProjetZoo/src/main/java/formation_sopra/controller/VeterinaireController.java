package formation_sopra.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation_sopra.controller.dto.request.CreateOrUpdateVeterinaireRequest;
import formation_sopra.controller.dto.response.VetWithSoinsResponse;
import formation_sopra.controller.dto.response.VeterinaireResponse;
import formation_sopra.dao.IDAOSoin;
import formation_sopra.dao.IDAOVeterinaire;
import formation_sopra.model.Veterinaire;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/veterinaire")
public class VeterinaireController {

    private final IDAOVeterinaire daoVeterinaire;
    private static final Logger log =
            LoggerFactory.getLogger(VeterinaireController.class);
    private final IDAOSoin daoSoin;

    private PasswordEncoder passwordEncoder;

    public VeterinaireController(IDAOVeterinaire daoVeterinaire, IDAOSoin daoSoin, PasswordEncoder passwordEncoder) {
        this.daoVeterinaire = daoVeterinaire;
        this.daoSoin = daoSoin;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public VeterinaireResponse getVeterinaireById(@PathVariable Integer id) {
        return daoVeterinaire.findById(id)
                .map(VeterinaireResponse::convert)
                .orElseThrow(() -> new RuntimeException("Vétérinaire non trouvé"));
    }

    @GetMapping("/{id}/soins")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public VetWithSoinsResponse getVeterinaireWithSoins(@PathVariable Integer id) {
        return daoVeterinaire.findById(id)
                .map(VetWithSoinsResponse::convert)
                .orElseThrow(() -> new RuntimeException("Vétérinaire non trouvé"));
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<VeterinaireResponse> getAllVeterinaires() {
        return daoVeterinaire.findAll()
                .stream()
                .map(VeterinaireResponse::convert)
                .toList();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public VeterinaireResponse createVeterinaire(@Valid @RequestBody CreateOrUpdateVeterinaireRequest request) {
        Veterinaire veterinaire = new Veterinaire();
        // Map the request to the entity (assuming you have a convert method or similar)
        veterinaire.setLogin(request.getLogin());
        veterinaire.setPassword(this.passwordEncoder.encode(request.getPassword()));
        Veterinaire saved = daoVeterinaire.save(veterinaire);
        log.info("Vétérinaire créé : {}", saved.getLogin());
        return VeterinaireResponse.convert(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public VetWithSoinsResponse updateVeterinaire(@PathVariable Integer id,
                                                 @Valid @RequestBody CreateOrUpdateVeterinaireRequest request) {

        if (!daoVeterinaire.existsById(id)) {
            throw new RuntimeException("Vétérinaire non trouvé");
        }

        Veterinaire veterinaire = daoVeterinaire.findById(id).orElseThrow(() -> new RuntimeException("Vétérinaire non trouvé"));
        // Map the request to the entity (assuming you have a convert method or similar)
        veterinaire.setLogin(request.getLogin());
        veterinaire.setPassword(this.passwordEncoder.encode(request.getPassword()));
        veterinaire.setId(id);
        veterinaire.setSoins(this.daoSoin.findAllByVeterinaireId(id));
        Veterinaire updated = daoVeterinaire.save(veterinaire);

        log.info("Vétérinaire mis à jour : {}", updated.getLogin());
        return VetWithSoinsResponse.convert(updated);
    }

    @PreAuthorize("hasRole('VETERINAIRE')")
		@PutMapping("/modifier-mes-infos")
		public VeterinaireResponse modifyMonCompte(Authentication auth, @Valid @RequestBody CreateOrUpdateVeterinaireRequest request) {
			Integer id = Integer.parseInt(auth.getName());

            if (!daoVeterinaire.existsById(id)) {
                throw new RuntimeException("Vétérinaire non trouvé");
            }

			Veterinaire veterinaire = new Veterinaire();
		
            veterinaire.setId(id);
			veterinaire.setLogin(this.passwordEncoder.encode(request.getLogin()));
			veterinaire.setPassword(request.getPassword());

			veterinaire = this.daoVeterinaire.save(veterinaire);

			return VeterinaireResponse.convert(veterinaire);
		}

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVeterinaire(@PathVariable Integer id) {

        if (!daoVeterinaire.existsById(id)) {
            throw new RuntimeException("Vétérinaire non trouvé");
        }

        daoVeterinaire.deleteById(id);
        log.info("Vétérinaire supprimé : {}", id);
    }
}