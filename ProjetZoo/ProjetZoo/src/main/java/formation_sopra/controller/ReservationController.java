package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateReservationRequest;
import formation_sopra.controller.dto.response.EntityCreatedOrUpdatedResponse;
import formation_sopra.controller.dto.response.ReservationResponse;
import formation_sopra.dao.IDAOReservation;
import formation_sopra.dao.IDAOSpectacle;
import formation_sopra.dao.IDAOVisiteur;
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
@RequestMapping("/api/reservation")
// @PreAuthorize("hasRole('ADMIN')") // Still need to decide who can access
public class ReservationController {

    private static Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final IDAOReservation daoReservation;
    private final IDAOSpectacle daoSpectacle;
    private final IDAOVisiteur daoVisiteur;

    public ReservationController(IDAOReservation daoReservation, IDAOSpectacle daoSpectacle, IDAOVisiteur daoVisiteur) {
        this.daoReservation = daoReservation;
        this.daoSpectacle = daoSpectacle;
        this.daoVisiteur = daoVisiteur;
    }



    @GetMapping("/{id}")
    public ReservationResponse findById(@PathVariable Integer id) {
        
        log.debug("Reservation {} ...", id);

        return daoReservation.findById(id).map(ReservationResponse::convert).orElse(null);      
    }

    @GetMapping
    public List<ReservationResponse> findAll() {
        log.debug("Liste des reservations ...");

        return this.daoReservation.findAll().stream().map(ReservationResponse::convert).toList();
    }

    @PutMapping("/{id}")
    public EntityCreatedOrUpdatedResponse update(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateReservationRequest request) {
        log.debug("Modification de la reservation {} ...", id);

        Reservation reservation = this.daoReservation.findById(id).orElseThrow(EntityNotFoundException::new);

        reservation.setDateReservation(request.getDateReservation());
        reservation.setDateVisite(request.getDateVisite());
        reservation.setPrix(request.getPrix());
        reservation.setNbPersonne(request.getNbPersonne());
        reservation.setVisiteur(this.daoVisiteur.findById(request.getVisiteurId()).orElseThrow(EntityNotFoundException::new));



        if (request.getSpectacleIds() != null) {
            List<Spectacle> spectacles = daoSpectacle.findAllById(request.getSpectacleIds());

            reservation.setSpectacles(spectacles);
            //sync spectacle side
            for (Spectacle s : spectacles) {
                s.getReservations().add(reservation);
            }
        }


        this.daoReservation.save(reservation);

        log.debug("Reservation {} modifié !", id);

        return new EntityCreatedOrUpdatedResponse(reservation.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedOrUpdatedResponse create(@RequestBody CreateOrUpdateReservationRequest request) {
        log.debug("Création d'un nouveau reservation ...");

        Reservation reservation = new Reservation();

        reservation.setDateReservation(request.getDateReservation());
        reservation.setDateVisite(request.getDateVisite());
        reservation.setPrix(request.getPrix());
        reservation.setNbPersonne(request.getNbPersonne());
        reservation.setVisiteur(this.daoVisiteur.findById(request.getVisiteurId()).orElseThrow(EntityNotFoundException::new));

        if (request.getSpectacleIds() != null) {
            List<Spectacle> spectacles = daoSpectacle.findAllById(request.getSpectacleIds());

            reservation.setSpectacles(spectacles);

            for (Spectacle s : spectacles) {
                s.getReservations().add(reservation);
            }
        }


        this.daoReservation.save(reservation);

        log.debug("Reservation créé !");

        return new EntityCreatedOrUpdatedResponse(reservation.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        log.debug("Suppression du reservation {} ...", id);
        this.daoReservation.deleteById(id);
        log.debug("Reservation {} supprimé !", id);
    }
}
