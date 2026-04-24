package formation_sopra.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation_sopra.controller.dto.response.VeterinaireResponse;
import formation_sopra.dao.IDAOVeterinaire;
import formation_sopra.model.Veterinaire;

@RestController
@RequestMapping("/api/veterinaire")
public class VeterinaireController {

    private final IDAOVeterinaire daoVeterinaire;
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(VeterinaireController.class);

    public VeterinaireController(IDAOVeterinaire daoVeterinaire) {
        this.daoVeterinaire = daoVeterinaire;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public VeterinaireResponse getVeterinaireById(@PathVariable Integer id) {
        return daoVeterinaire.findById(id)
                .map(VeterinaireResponse::convert)
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
    public VeterinaireResponse createVeterinaire(@RequestBody Veterinaire veterinaire) {
        Veterinaire saved = daoVeterinaire.save(veterinaire);
        log.info("Vétérinaire créé : {}", saved.getLogin());
        return VeterinaireResponse.convert(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public VeterinaireResponse updateVeterinaire(@PathVariable Integer id,
                                                 @RequestBody Veterinaire veterinaire) {

        if (!daoVeterinaire.existsById(id)) {
            throw new RuntimeException("Vétérinaire non trouvé");
        }

        veterinaire.setId(id);
        Veterinaire updated = daoVeterinaire.save(veterinaire);

        log.info("Vétérinaire mis à jour : {}", updated.getLogin());
        return VeterinaireResponse.convert(updated);
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