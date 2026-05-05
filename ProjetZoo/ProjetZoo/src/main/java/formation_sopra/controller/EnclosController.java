package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateEnclosRequest;
import formation_sopra.controller.dto.response.EnclosResponse;
import formation_sopra.controller.dto.response.EnclosWithAnimalsResponse;
import formation_sopra.controller.dto.response.EnclosWithSpectaclesResponse;
import formation_sopra.dao.IDAOAnimal;
import formation_sopra.dao.IDAOEnclos;
import formation_sopra.dao.IDAOSpectacle;
import formation_sopra.exception.EnclosNotFoundException;
import formation_sopra.model.Enclos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enclos")
public class EnclosController {

    private static Logger log = LoggerFactory.getLogger(EnclosController.class);

    private final IDAOEnclos daoEnclos;
    private final IDAOAnimal daoAnimal;
    private final IDAOSpectacle daoSpectacle;

    public EnclosController(IDAOEnclos daoEnclos, IDAOAnimal daoAnimal, IDAOSpectacle daoSpectacle) {
        this.daoEnclos = daoEnclos;
        this.daoAnimal = daoAnimal;
        this.daoSpectacle = daoSpectacle;
    }

    @GetMapping("/{numero}") 
    public EnclosWithAnimalsResponse getEnclosById(@PathVariable Integer numero) {

        log.debug("Enclos {} ...", numero);

        return this.daoEnclos.findByIdWithAnimals(numero)
                .map(EnclosWithAnimalsResponse::convert)
                .orElseThrow(EnclosNotFoundException::new)
                ;
    }

    @GetMapping("/{numero}/spectacles") 
    public EnclosWithSpectaclesResponse getEnclosByIdWithSpectacles(@PathVariable Integer numero) {

        log.debug("Enclos {} ...", numero);

        return this.daoEnclos.findByIdWithSpectacles(numero)
                .map(EnclosWithSpectaclesResponse::convert)
                .orElseThrow(EnclosNotFoundException::new)
                ;
    }

    @GetMapping
    public List<EnclosResponse> getAllEncloss() {

        log.debug("Liste des enclos ...");


        return this.daoEnclos.findAll()
                .stream()
                .map(EnclosResponse::convert)
                .toList()
                ;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public EnclosResponse modifyEnclos(@PathVariable Integer numero, @RequestBody CreateOrUpdateEnclosRequest request){

        if (!daoEnclos.existsById(numero)) {
            throw new EnclosNotFoundException();
        }

        Enclos enclos = new Enclos();
        enclos.setNumero(numero);
        enclos.setBiome(request.getBiome());
        enclos.setCapacite(request.getCapacite());
        enclos.setEspece(request.getEspece());
        enclos.setAnimals(this.daoAnimal.findAllByEnclosId(numero));
        enclos.setSpectacles(this.daoSpectacle.findAllByEnclosId(numero));

        Enclos updated = daoEnclos.save(enclos);

        log.info("Enclos mis à jour : {}", updated.getNumero());
        return EnclosResponse.convert(updated);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public EnclosResponse createEnclos(@RequestBody CreateOrUpdateEnclosRequest request) {

        Enclos enclos = new Enclos();
        enclos.setBiome(request.getBiome());
        enclos.setCapacite(request.getCapacite());
        enclos.setEspece(request.getEspece());
        
        log.debug("Nouvel enclos ajouté !");

        enclos = this.daoEnclos.save(enclos);
        return EnclosResponse.convert(enclos);
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
