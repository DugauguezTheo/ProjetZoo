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

import formation_sopra.dao.IDAOAdmin;
import formation_sopra.model.Admin;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    public final IDAOAdmin daoAdmin;

    public static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(AdminController.class);

    public AdminController(IDAOAdmin daoAdmin) {
        this.daoAdmin = daoAdmin;
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return this.daoAdmin.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin non trouvé"));
    }

    @GetMapping()
    public List<Admin> getAllAdmins() {
        return this.daoAdmin.findAll();
    }

    @PostMapping()
    public Admin createAdmin(@RequestBody Admin admin) {
        return this.daoAdmin.save(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        if (!daoAdmin.existsById(id)) {
            throw new RuntimeException("Admin non trouvé");
        }

        admin.setId(id);
        Admin updated = daoAdmin.save(admin);

        log.info("Admin mis à jour : {}", updated.getId());
        return updated;
    }

    @DeleteMapping ("/{id}")
    public void deleteAdmin(@PathVariable Integer id) {
        if (!this.daoAdmin.existsById(id)) {
            throw new RuntimeException("Admin non trouvé");
        }
        this.daoAdmin.deleteById(id);
        log.info("Admin supprimé : {}", id);
    }
}
