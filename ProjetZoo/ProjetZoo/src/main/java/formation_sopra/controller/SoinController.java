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

import formation_sopra.dao.IDAOSoin;
import formation_sopra.model.Soin;



@RestController
@RequestMapping("/api/soin")
@PreAuthorize("hasAnyRole('ADMIN','VETERINAIRE')")
public class SoinController {

    private final IDAOSoin daoSoin;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(SoinController.class);    

    public SoinController(formation_sopra.dao.IDAOSoin daoSoin) {
        this.daoSoin = daoSoin;
    }

    @GetMapping("/{id}")
    public Soin getSoinById(@PathVariable Integer id) {
        return this.daoSoin.findById(id)
                .orElseThrow(() -> new RuntimeException("Soin non trouvé"));
    }

    @GetMapping()
    public List<Soin> getAllSoins() {
        return this.daoSoin.findAll();
    }

    @PostMapping()
    public Soin createSoin(@RequestBody Soin soin) {
        Soin saved = this.daoSoin.save(soin);
        log.info("Soin créé : {}", saved.getId());
        return saved;
    }

    @PutMapping("/{id}")
    public Soin updateSoin(@PathVariable Integer id, @RequestBody Soin soin) {
        if (!this.daoSoin.existsById(id)) {
            throw new RuntimeException("Soin non trouvé");
        }
        soin.setId(id);
        Soin updated = this.daoSoin.save(soin);
        log.info("Soin mis à jour : {}", updated.getId());
        return updated;
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
