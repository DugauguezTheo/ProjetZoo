package formation_sopra.controller;

import formation_sopra.controller.dto.response.AnimalResponse;
import formation_sopra.dao.IDAOAnimal;
import formation_sopra.exception.AnimalNotFoundException;
import formation_sopra.model.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
@PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE')") //semble pertinent d'ajouter aussi vétos
public class AnimalController {

    private static Logger log = LoggerFactory.getLogger(AnimalController.class);

    private final IDAOAnimal daoAnimal;

    public AnimalController(IDAOAnimal daoAnimal) {
        this.daoAnimal = daoAnimal;
    }

    //@Autowired
    //IDAOAnimal daoAnimal;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE') or hasPermission('Animal', 'read')")
    public AnimalResponse getAnimalById(@PathVariable Integer id) {
        return this.daoAnimal.findById(id)
                .map(AnimalResponse::convert)
                .orElseThrow(AnimalNotFoundException::new)
                ;
    }

    @GetMapping //la seule fonction que tout le monde devrait avoir acces
    @PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE') or hasPermission('Animal', 'read')")
    public List<AnimalResponse> getAllAnimals() {
        return this.daoAnimal.findAll()
                .stream()
                .map(AnimalResponse::convert)
                .toList()
                ;
    }

    @PutMapping("/{id}")
    public Animal modifyAnimal(@PathVariable Integer id,@ModelAttribute Animal animal){
        this.daoAnimal.save(animal);
        return null;
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
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
