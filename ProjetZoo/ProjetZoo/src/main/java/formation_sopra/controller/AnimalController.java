package formation_sopra.controller;

import formation_sopra.controller.dto.response.AnimalResponse;
import formation_sopra.dao.IDAOAnimal;
import formation_sopra.exception.AnimalNotFoundException;
import formation_sopra.model.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
@PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')") //semble pertinent d'ajouter aussi vétos
public class AnimalController {

    private static Logger log = LoggerFactory.getLogger(AnimalController.class);

    private final IDAOAnimal daoAnimal;

    public AnimalController(IDAOAnimal daoAnimal) {
        this.daoAnimal = daoAnimal;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE') or hasPermission('Animal', 'read')")
    public AnimalResponse getAnimalById(@PathVariable Integer id) {

        log.debug("Animal {} ...", id);

        return this.daoAnimal.findById(id)
                .map(AnimalResponse::convert)
                .orElseThrow(AnimalNotFoundException::new)
                ;
    }

    @GetMapping //la seule fonction que tout le monde devrait avoir acces
    @PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE') or hasPermission('Animal', 'read')")
    public List<AnimalResponse> getAllAnimals() {

        log.debug("Liste des animaux ...");

        return this.daoAnimal.findAll()
                .stream()
                .map(AnimalResponse::convert)
                .toList()
                ;
    }

    @PutMapping("/{id}")
    public Animal modifyAnimal(@PathVariable Integer id,@RequestBody Animal animal){

        if (!daoAnimal.existsById(id)) {
            throw new AnimalNotFoundException();
        }

        animal.setId(id);
        Animal updated = daoAnimal.save(animal);

        log.info("Animal mis à jour : {}", updated.getId());
        return updated;
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {

        log.debug("Nouvel animal ajouté !");

        this.daoAnimal.save(animal);
        return animal;
    }

    @DeleteMapping("/{id}/delete")
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
