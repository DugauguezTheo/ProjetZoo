package formation_sopra.controller;

import formation_sopra.dao.IDAOEnclos;
import formation_sopra.model.Enclos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enclos")
@PreAuthorize("hasRole('ADMIN')")
public class EnclosController {

    private static Logger log = LoggerFactory.getLogger(EnclosController.class);

    private final IDAOEnclos daoEnclos;

    public EnclosController(IDAOEnclos daoEnclos) {
        this.daoEnclos = daoEnclos;
    }

    //@Autowired
    //IDAOEnclos daoEnclos;

    @GetMapping("/{id}") //fonction que tout le monde devrait avoir acces
    @PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE') or hasPermission('Enclos', 'read')")
    public String getEnclosById(@PathVariable Integer id) {
        //return daoEnclos.findById(id).orElse(null);
        return null;
    }

    @GetMapping //fonction que tout le monde devrait avoir acces
    @PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE') or hasPermission('Enclos', 'read')")
    public List<Enclos> getAllEncloss() {
        return daoEnclos.findAll();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE')")
    public Enclos modifyEnclos(@PathVariable Integer id,@ModelAttribute Enclos enclos){
        this.daoEnclos.save(enclos);
        return null;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('VETERINAIRE')")
    public Enclos createEnclos(@RequestBody Enclos enclos) {
        this.daoEnclos.save(enclos);
        return enclos;
    }

    @DeleteMapping("/{id}/delete")
    public Enclos deleteEnclosById(@PathVariable Integer id) {
        this.daoEnclos.deleteById(id);
        return null;
    }
}
