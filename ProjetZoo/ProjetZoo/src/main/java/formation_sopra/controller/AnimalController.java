package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateAnimalRequest;
import formation_sopra.controller.dto.response.AnimalResponse;
import formation_sopra.controller.dto.response.AnimalWithSoinsResponse;
import formation_sopra.dao.IDAOAnimal;
import formation_sopra.dao.IDAOEnclos;
import formation_sopra.dao.IDAOSoin;
import formation_sopra.exception.AnimalNotFoundException;
import formation_sopra.exception.ArticleNotFoundException;
import formation_sopra.model.Animal;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    private static Logger log = LoggerFactory.getLogger(AnimalController.class);

    private final IDAOAnimal daoAnimal;
    private final IDAOSoin daoSoin;
    private final IDAOEnclos daoEnclos;

    public AnimalController(IDAOAnimal daoAnimal, IDAOSoin daoSoin, IDAOEnclos daoEnclos) {
        this.daoAnimal = daoAnimal;
        this.daoSoin = daoSoin;
        this.daoEnclos = daoEnclos;
    }

    @GetMapping("/{id}")
    public AnimalResponse getAnimalById(@PathVariable Integer id) {

        log.debug("Animal {} ...", id);

        return this.daoAnimal.findById(id)
                .map(AnimalResponse::convert)
                .orElseThrow(AnimalNotFoundException::new)
                ;
    }

    @GetMapping("/{id}/soins")
    public AnimalWithSoinsResponse getAnimalByIdWithSoins(@PathVariable Integer id) {
        log.debug("Recherche de l'animal n°{} avec l'historique des soins", id);
        return this.daoAnimal.findByIdWithSoins(id)
                .map(AnimalWithSoinsResponse::convert)
                .orElseThrow(ArticleNotFoundException::new)
                ;
    }

    @GetMapping
    public List<AnimalResponse> getAllAnimals() {

        log.debug("Liste des animaux ...");

        return this.daoAnimal.findAll()
                .stream()
                .map(AnimalResponse::convert)
                .toList()
                ;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public AnimalResponse modifyAnimal(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateAnimalRequest request){

        if (!daoAnimal.existsById(id)) {
            throw new AnimalNotFoundException();
        }

        Animal animal = new Animal();
        animal.setId(id);
        animal.setDateNaissance(request.getDateNaissance());
        animal.setEnclos(this.daoEnclos.findById(request.getIdEnclos()).orElse(null));
        animal.setEspece(request.getEspece());
        animal.setPrenom(request.getPrenom());
        
        animal.setSoins(this.daoSoin.findAllByAnimalId(id));
        Animal updated = daoAnimal.save(animal);

        log.info("Animal mis à jour : {}", updated.getId());
        return AnimalResponse.convert(updated);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public AnimalResponse createAnimal(@Valid @RequestBody CreateOrUpdateAnimalRequest request) {

        log.debug("Nouvel animal ajouté !");

        Animal animal = new Animal();
        animal.setDateNaissance(request.getDateNaissance());
        animal.setEnclos(this.daoEnclos.findById(request.getIdEnclos()).orElse(null));
        animal.setEspece(request.getEspece());
        animal.setPrenom(request.getPrenom());
        animal = daoAnimal.save(animal);

        AnimalResponse resp = AnimalResponse.convert(animal);
        resp.setId(animal.getId());
        return resp;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
    public boolean deleteAnimalById(@PathVariable Integer id) {
        try {
            log.debug("Suppression de l'animal {} ...", id);
            this.daoAnimal.deleteById(id);
        }

        catch (Exception ex) {
            log.error("Une erreur est survenue pendant la suppression de l'animal {} : {}", id, ex.getMessage());

            return false;
        }

        return true;
    }
}
