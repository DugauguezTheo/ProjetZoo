package formation_sopra.controller;

import formation_sopra.controller.dto.request.CreateOrUpdateReservationAsAdminRequest;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/reservation")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ReservationResponse findById(@PathVariable Integer id) {
        
        log.debug("Reservation {} ...", id);

        return daoReservation.findByIdWithSpectacles(id).map(ReservationResponse::convert).orElseThrow(EntityNotFoundException::new);      
    }

    @PreAuthorize("hasRole('VISITEUR')")
    @GetMapping("/mes-reservations")
    public List<ReservationResponse> findByIdAuthenticated(Authentication auth) {
        Integer id = Integer.parseInt(auth.getName());

        log.debug("Liste des reservations du visiteur {}...", id);

        return this.daoReservation.findAllWithSpectaclesByIdVisiteur(id).stream().map(ReservationResponse::convert).toList();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReservationResponse> findAll() {
        log.debug("Liste des reservations ...");

        return this.daoReservation.findAllWithSpectacles().stream().map(ReservationResponse::convert).toList();
    }

    @GetMapping("/spectacle/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReservationResponse> findAllBySpectacleId(@PathVariable Integer spectacleId) {
        log.debug("Liste des reservations pour le spectacle {}...", spectacleId);
        return this.daoReservation.findAllBySpectacleId(spectacleId).stream().map(ReservationResponse::convert).toList();
    }

    @GetMapping("/visiteur/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReservationResponse> findAllByVisiteurId(@PathVariable Integer visiteurId) {
        log.debug("Liste des reservations du visiteur {}...", visiteurId);
        return this.daoReservation.findAllWithSpectaclesByIdVisiteur(visiteurId).stream().map(ReservationResponse::convert).toList();
    }
    

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VISITEUR')")
    public EntityCreatedOrUpdatedResponse update(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateReservationRequest request) {
        log.debug("Modification de la reservation {} ...", id);

        Reservation reservation = this.daoReservation.findById(id).orElseThrow(EntityNotFoundException::new);

        reservation.setDateReservation(request.getDateReservation());
        reservation.setDateVisite(request.getDateVisite());
        reservation.setPrix(request.getPrix());
        reservation.setNbPersonne(request.getNbPersonne());
        
        reservation.setVisiteur(this.daoVisiteur.findById(request.getVisiteurId()).orElseThrow(EntityNotFoundException::new));

        reservation.setSpectacles(this.daoSpectacle.findAllById(request.getSpectaclesIds()));


        this.daoReservation.save(reservation);

        log.debug("Reservation {} modifiée !", id);

        return new EntityCreatedOrUpdatedResponse(reservation.getId());
    }

    @PutMapping("/as-admin/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VISITEUR')")
    public EntityCreatedOrUpdatedResponse updateAsAdmin(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateReservationAsAdminRequest request) {
        log.debug("Modification de la reservation {} ...", id);

        Reservation reservation = this.daoReservation.findById(id).orElseThrow(EntityNotFoundException::new);

        reservation.setDateReservation(request.getDateReservation());
        reservation.setDateVisite(request.getDateVisite());
        reservation.setPrix(request.getPrix());
        reservation.setNbPersonne(request.getNbPersonne());
        
        reservation.setVisiteur(this.daoVisiteur.findById(request.getVisiteurId()).orElseThrow(EntityNotFoundException::new));

        reservation.setSpectacles(this.daoSpectacle.findAllById(request.getSpectaclesIds()));


        this.daoReservation.save(reservation);

        log.debug("Reservation {} modifiée !", id);

        return new EntityCreatedOrUpdatedResponse(reservation.getId());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','VISITEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedOrUpdatedResponse create(@Valid @RequestBody CreateOrUpdateReservationRequest request) {
        log.debug("Création d'une nouvelle reservation ...");

        Reservation reservation = new Reservation();

        reservation.setDateReservation(request.getDateReservation());
        reservation.setDateVisite(request.getDateVisite());
        reservation.setPrix(request.getPrix());
        reservation.setNbPersonne(request.getNbPersonne());

        reservation.setVisiteur(this.daoVisiteur.findById(request.getVisiteurId()).orElseThrow(EntityNotFoundException::new));

        List<Spectacle> spectacles = new ArrayList<>();
        if (request.getSpectaclesIds() != null && !request.getSpectaclesIds().isEmpty()){
            spectacles = this.daoSpectacle.findAllById(request.getSpectaclesIds());
        }
        reservation.setSpectacles(spectacles);


        this.daoReservation.save(reservation);

        log.debug("Reservation créée !");

        return new EntityCreatedOrUpdatedResponse(reservation.getId());
    }

    @PostMapping("as-admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedOrUpdatedResponse createAsAdmin(@Valid @RequestBody CreateOrUpdateReservationAsAdminRequest request) {
        log.debug("Création d'une nouvelle reservation ...");

        Reservation reservation = new Reservation();

        reservation.setDateReservation(request.getDateReservation());
        reservation.setDateVisite(request.getDateVisite());
        reservation.setPrix(request.getPrix());
        reservation.setNbPersonne(request.getNbPersonne());

        reservation.setVisiteur(this.daoVisiteur.findById(request.getVisiteurId()).orElseThrow(EntityNotFoundException::new));

        List<Spectacle> spectacles = new ArrayList<>();
        if (request.getSpectaclesIds() != null && !request.getSpectaclesIds().isEmpty()){
            spectacles = this.daoSpectacle.findAllById(request.getSpectaclesIds());
        }
        reservation.setSpectacles(spectacles);


        this.daoReservation.save(reservation);

        log.debug("Reservation créée !");

        return new EntityCreatedOrUpdatedResponse(reservation.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Integer id) {
        log.debug("Suppression du reservation {} ...", id);
        this.daoReservation.deleteById(id);
        log.debug("Reservation {} supprimé !", id);
    }
}
