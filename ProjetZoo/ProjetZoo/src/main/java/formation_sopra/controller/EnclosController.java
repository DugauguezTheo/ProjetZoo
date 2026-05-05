package formation_sopra.controller;

import formation_sopra.controller.dto.response.EnclosResponse;
import formation_sopra.dao.IDAOEnclos;
import formation_sopra.exception.EnclosNotFoundException;
import formation_sopra.model.Enclos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enclos")
@PreAuthorize("hasRole('ADMIN')")
public class EnclosController {

    private static Logger log = LoggerFactory.getLogger(EnclosController.class);

    private final IDAOEnclos daoEnclos;

    public EnclosController(IDAOEnclos daoEnclos) {
        this.daoEnclos = daoEnclos;
    }

    @GetMapping("/{numero}") //fonction que tout le monde devrait avoir acces
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE') or hasPermission('Enclos', 'read')")
    public EnclosResponse getEnclosById(@PathVariable Integer numero) {

        log.debug("Enclos {} ...", numero);

        return this.daoEnclos.findById(numero)
                .map(EnclosResponse::convert)
                .orElseThrow(EnclosNotFoundException::new)
                ;
    }

    @GetMapping //fonction que tout le monde devrait avoir acces
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE') or hasPermission('Enclos', 'read')")
    public List<EnclosResponse> getAllEncloss() {

        log.debug("Liste des enclos ...");


        return this.daoEnclos.findAll()
                .stream()
                .map(EnclosResponse::convert)
                .toList()
                ;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public Enclos modifyEnclos(@PathVariable Integer numero,@RequestBody Enclos enclos){

        if (!daoEnclos.existsById(numero)) {
            throw new EnclosNotFoundException();
        }

        enclos.setNumero(numero);
        Enclos updated = daoEnclos.save(enclos);

        log.info("Enclos mis à jour : {}", updated.getNumero());
        return updated;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public Enclos createEnclos(@RequestBody Enclos enclos) {

        log.debug("Nouvel enclos ajouté !");

        this.daoEnclos.save(enclos);
        return enclos;
    }

    @DeleteMapping("/{id}/delete")
    public boolean deleteEnclosById(@PathVariable Integer id) {
        try {
            log.debug("Suppression de l'enclos- {} ...", id);
            this.daoEnclos.deleteById(id);
        }

        catch (Exception ex) {
            log.error("Une erreur est survenue pendant la suppression de l'enclos {} : {}", id, ex.getMessage());

            return false;
        }
        return true;
    }
}
