package formation_sopra.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import formation_sopra.dao.IDAOCompte;
import formation_sopra.model.Admin;
import formation_sopra.model.Compte;
import formation_sopra.model.Veterinaire;
import formation_sopra.model.Visiteur;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    
    @Autowired
    private IDAOCompte daoCompte;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Compte u = daoCompte.findByLogin(username);
        
        if (u == null) {
            throw new UsernameNotFoundException("L'utilisateur n'existe pas!");
        }

        String role = switch (u) {
            case Admin a -> "ADMIN";
            case Visiteur v -> "VISITEUR";
            case Veterinaire vet -> "VETERINAIRE";
            default -> throw new RuntimeException("Type de compte inconnu");
        };

        return User.builder()
            .username(u.getLogin())
            .password(u.getPassword())
            .roles(role)
            .build();
    }
}