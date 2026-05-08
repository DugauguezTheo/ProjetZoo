package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateSpectacleRequest;
import formation_sopra.controller.dto.response.EntityCreatedOrUpdatedResponse;
import formation_sopra.controller.dto.response.SpectacleResponse;
import formation_sopra.controller.dto.response.SpectacleWithReservationsResponse;
import formation_sopra.dao.IDAOEnclos;
import formation_sopra.dao.IDAOReservation;
import formation_sopra.dao.IDAOSpectacle;
import formation_sopra.model.Enclos;
import formation_sopra.model.Spectacle;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/spectacle")
public class SpectacleController {

    private static Logger log = LoggerFactory.getLogger(SpectacleController.class);

    private final IDAOSpectacle daoSpectacle;
    private final IDAOReservation daoReservation;
    private final IDAOEnclos daoEnclos;

    public SpectacleController(IDAOSpectacle daoSpectacle, IDAOReservation daoReservation, IDAOEnclos daoEnclos) {
        this.daoSpectacle = daoSpectacle;
        this.daoReservation = daoReservation;
        this.daoEnclos = daoEnclos;
    }



    @GetMapping("/{id}/with-reservations")
    @PreAuthorize("hasRole('ADMIN')")
    public SpectacleWithReservationsResponse findByIdWithReservations(@PathVariable Integer id) {
        
        log.debug("Spectacle {} avec les réservations ...", id);

        return daoSpectacle.findByIdWithReservations(id).map(SpectacleWithReservationsResponse::convert).orElse(null);    
    }

    @GetMapping("/{id}")
    public SpectacleResponse findById(@PathVariable Integer id) {
        
        log.debug("Spectacle {} ...", id);

        return daoSpectacle.findById(id).map(SpectacleResponse::convert).orElse(null);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<SpectacleResponse> findAll() {
        log.debug("Liste des spectacles ...");

        return this.daoSpectacle.findAllWithReservations().stream().map(SpectacleResponse::convert).toList();
    }

    @GetMapping("/between")
    public List<SpectacleResponse> findAllBetween(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        
        log.debug("Liste des spectacles entre le {} et le {}", dateDebut, dateFin);

        return daoSpectacle.findAllBetween(dateDebut, dateFin).stream().map(SpectacleResponse::convert).toList();
    }

    @GetMapping("/enclos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SpectacleWithReservationsResponse> findAllByEnclosId(@PathVariable Integer id) {
        log.debug("Liste des spectacles pour l'enclos {}...", id);
        Enclos e = this.daoEnclos.findById(id).orElseThrow(EntityNotFoundException::new);
        return this.daoSpectacle.findAllByEnclosWithReservations(e).stream().map(SpectacleWithReservationsResponse::convert).toList();
    }

    @GetMapping("/enclos/{id}/between")
    public List<SpectacleResponse> findAllByEnclosIdBetween(@PathVariable Integer id, @RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        log.debug("Liste des spectacles pour l'enclos {} entre le {} et le {}...", id, dateDebut, dateFin);
        return this.daoSpectacle.findAllByEnclosIdBetween(id, dateDebut, dateFin).stream().map(SpectacleResponse::convert).toList();
    }
    

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EntityCreatedOrUpdatedResponse update(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateSpectacleRequest request) {
        log.debug("Modification du spectacle {} ...", id);

        Spectacle spectacle = this.daoSpectacle.findById(id).orElseThrow(EntityNotFoundException::new);


        spectacle.setDateDebut(request.getDateDebut());
        spectacle.setHeureDebut(request.getHeureDebut());
        spectacle.setDuree(request.getDuree()); 
        spectacle.setEnclos(this.daoEnclos.findById(request.getEnclosId()).orElseThrow(EntityNotFoundException::new));
        spectacle.setReservations(this.daoReservation.findAllBySpectacleId(id));
        spectacle.setTitre(request.getTitre());

        this.daoSpectacle.save(spectacle);

        log.debug("Spectacle {} modifié !", id);

        return new EntityCreatedOrUpdatedResponse(spectacle.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedOrUpdatedResponse create(@Valid @RequestBody CreateOrUpdateSpectacleRequest request) {
        log.debug("Création d'un nouveau spectacle ...");

        Spectacle spectacle = new Spectacle();

        spectacle.setDateDebut(request.getDateDebut());
        spectacle.setHeureDebut(request.getHeureDebut());
        spectacle.setDuree(request.getDuree());
        spectacle.setTitre(request.getTitre());
        spectacle.setEnclos(this.daoEnclos.findById(request.getEnclosId()).orElseThrow(EntityNotFoundException::new));

        this.daoSpectacle.save(spectacle);

        log.debug("Spectacle créé !");

        return new EntityCreatedOrUpdatedResponse(spectacle.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Integer id) {
        log.debug("Suppression du spectacle {} ...", id);
        this.daoSpectacle.deleteById(id);
        log.debug("Spectacle {} supprimé !", id);
    }
}
