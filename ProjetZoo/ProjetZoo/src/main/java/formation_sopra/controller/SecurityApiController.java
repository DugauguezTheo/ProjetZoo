package formation_sopra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import formation_sopra.controller.dto.request.AuthRequest;
import formation_sopra.controller.dto.response.AuthResponse;
import formation_sopra.dao.IDAOCompte;
import formation_sopra.model.Compte;
import formation_sopra.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;

@RestController
public class SecurityApiController {
    private final static Logger log = LoggerFactory.getLogger(SecurityApiController.class);
    private final AuthenticationManager authenticationManager;
    private final IDAOCompte daoCompte;

    public SecurityApiController(AuthenticationManager authenticationManager, IDAOCompte daoCompte, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.daoCompte = daoCompte;
    }

    @PostMapping("/api/auth")
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        try {
            log.debug("Tentative d'authentification ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("Authentification validée !");

            Compte compte = daoCompte.findByLogin(request.getLogin());

            String token = JwtUtils.generate(authentication, compte);

            Claims claims = JwtUtils.getClaims(token);

            return new AuthResponse(true, token, claims.get("role", String.class), claims.get("login", String.class));
        }

        catch (BadCredentialsException ex) {
            log.error("Authentification impossible : mauvais identifiants.");
        }

        catch (Exception ex) {
            log.error("Authentification impossible : erreur ({}).", ex.getClass().getSimpleName());
        }

        return new AuthResponse(false, "", "", "");
    }


    // @PostMapping("/api/inscription")
    // public EntityCreatedOrUpdatedResponse subscribe(@Valid @RequestBody SubscriptionRequest request) {
    //     Personne personne = new Personne();

    //     personne.setLogin(request.getUsername());
    //     personne.setPassword(this.passwordEncoder.encode(request.getPassword()));

    //     this.daoCompte.save(personne);

    //     return new EntityCreatedOrUpdatedResponse(personne.getId());
    // }
}
