package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateSpectacleRequest;
import formation_sopra.controller.dto.response.EntityCreatedOrUpdatedResponse;
import formation_sopra.controller.dto.response.SpectacleResponse;
import formation_sopra.dao.IDAOReservation;
import formation_sopra.dao.IDAOSpectacle;
import formation_sopra.model.Reservation;
import formation_sopra.model.Spectacle;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spectacle")
// @PreAuthorize("hasRole('ADMIN')") // Still need to decide who can access
public class SpectacleController {

    private static Logger log = LoggerFactory.getLogger(SpectacleController.class);

    private final IDAOSpectacle daoSpectacle;
    private final IDAOReservation daoReservation;

    public SpectacleController(IDAOSpectacle daoSpectacle, IDAOReservation daoReservation) {
        this.daoSpectacle = daoSpectacle;
        this.daoReservation = daoReservation;
    }



    @GetMapping("/{id}")
    public SpectacleResponse findById(@PathVariable Integer id) {
        
        log.debug("Spectacle {} ...", id);

        return daoSpectacle.findById(id).map(SpectacleResponse::convert).orElse(null);      
    }

    @GetMapping
    public List<SpectacleResponse> findAll() {
        log.debug("Liste des spectacles ...");

        return this.daoSpectacle.findAll().stream().map(SpectacleResponse::convert).toList();
    }

    @PutMapping("/{id}")
    public EntityCreatedOrUpdatedResponse update(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateSpectacleRequest request) {
        log.debug("Modification du spectacle {} ...", id);

        Spectacle spectacle = this.daoSpectacle.findById(id).orElseThrow(EntityNotFoundException::new);


        spectacle.setDateDebut(request.getDateDebut());
        spectacle.setHeureDebut(request.getHeureDebut());
        spectacle.setDuree(request.getDuree()); 
        spectacle.setEnclos(request.getEnclos())
        if (request.getReservationIds() != null) {
            List<Reservation> reservations = daoReservation.findAllById(request.getReservationIds());
            spectacle.setReservations(reservations);

            spectacle.setReservations(reservations);

            for (Reservation r : reservations) {
                r.getSpectacles().add(spectacle);
            }
    }

        this.daoSpectacle.save(spectacle);

        log.debug("Spectacle {} modifié !", id);

        return new EntityCreatedOrUpdatedResponse(spectacle.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedOrUpdatedResponse create(@RequestBody CreateOrUpdateSpectacleRequest request) {
        log.debug("Création d'un nouveau spectacle ...");

        Spectacle spectacle = new Spectacle();

        spectacle.setDateDebut(request.getDateDebut());
        spectacle.setHeureDebut(request.getHeureDebut());
        spectacle.setDuree(request.getDuree());
        spectacle.setEnclos(request.getEnclos())
        if (request.getReservationIds() != null) {
            List<Reservation> reservations = daoReservation.findAllById(request.getReservationIds());
            spectacle.setReservations(reservations); // replaces the list
        

            spectacle.setReservations(reservations);

            for (Reservation r : reservations) {
                r.getSpectacles().add(spectacle);
            }
        
        }

        this.daoSpectacle.save(spectacle);

        log.debug("Spectacle créé !");

        return new EntityCreatedOrUpdatedResponse(spectacle.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        log.debug("Suppression du spectacle {} ...", id);
        this.daoSpectacle.deleteById(id);
        log.debug("Spectacle {} supprimé !", id);
    }
}
