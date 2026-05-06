package formation_sopra.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation_sopra.controller.dto.request.CreateOrUpdateSoinRequest;
import formation_sopra.controller.dto.response.SoinResponse;
import formation_sopra.dao.IDAOAnimal;
import formation_sopra.dao.IDAOSoin;
import formation_sopra.dao.IDAOVeterinaire;
import formation_sopra.exception.AnimalNotFoundException;
import formation_sopra.exception.CompteNotFoundException;
import formation_sopra.model.Soin;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/soin")
@PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
public class SoinController {

    private final IDAOSoin daoSoin;
    private final IDAOAnimal daoAnimal;
    private final IDAOVeterinaire daoVeterinaire;

    private static final Logger log = LoggerFactory.getLogger(SoinController.class);    

    public SoinController(IDAOSoin daoSoin, IDAOAnimal daoAnimal, IDAOVeterinaire daoVeterinaire) {
        this.daoSoin = daoSoin;
        this.daoAnimal = daoAnimal;
        this.daoVeterinaire = daoVeterinaire;
    }

    @GetMapping("/{id}")
    public SoinResponse getSoinById(@PathVariable Integer id) {
        return this.daoSoin.findById(id)
                .map(SoinResponse::convert)
                .orElseThrow(() -> new RuntimeException("Soin non trouvé"));
    }

    @GetMapping()
    public List<SoinResponse> getAllSoins() {
        return this.daoSoin.findAll().stream()
            .map(SoinResponse::convert)
            .toList();
    }

    @PostMapping()
    public SoinResponse createSoin(@Valid @RequestBody CreateOrUpdateSoinRequest request) {

        Soin soin = new Soin();

        soin.setDescription(request.description());
        soin.setAnimal(this.daoAnimal.findById(request.animalId()).orElseThrow(AnimalNotFoundException::new));
        soin.setDateSoin(request.dateSoin());
        soin.setVeterinaire(this.daoVeterinaire.findById(request.veterinaireId()).orElseThrow(CompteNotFoundException::new));
        
        Soin saved = this.daoSoin.save(soin);
        
        log.info("Soin créé : {}", saved.getId());
        
        return SoinResponse.convert(saved);
    }

    @PutMapping("/{id}")
    public SoinResponse updateSoin(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateSoinRequest request) {
        if (!this.daoSoin.existsById(id)) {
            throw new RuntimeException("Soin non trouvé");
        }

        Soin soin = new Soin();

        soin.setId(id);
        soin.setDescription(request.description());
        soin.setAnimal(this.daoAnimal.findById(request.animalId()).orElseThrow(AnimalNotFoundException::new));
        soin.setDateSoin(request.dateSoin());
        soin.setVeterinaire(this.daoVeterinaire.findById(request.veterinaireId()).orElseThrow(CompteNotFoundException::new));
        
        Soin updated = this.daoSoin.save(soin);
        log.info("Soin mis à jour : {}", updated.getId());
        return SoinResponse.convert(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteSoin(@PathVariable Integer id) {
        if (!this.daoSoin.existsById(id)) {
            throw new RuntimeException("Soin non trouvé");
        }
        this.daoSoin.deleteById(id);
        log.info("Soin supprimé : {}", id);
    }

}
