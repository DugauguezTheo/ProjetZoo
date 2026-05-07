package formation_sopra.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import formation_sopra.dao.IDAOAchat;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {


    @Autowired IDAOAchat daoAchat;

    private final Logger logger;

    public JwtHeaderFilter(){
        this.logger = LoggerFactory.getLogger(JwtHeaderFilter.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        logger.debug("Contenu Authorization : {}", authHeader);

        String uri = request.getRequestURI();


        // On laisse passer la route d'auth sans vérifier le token
        if (uri.equals("/api/auth")) {
            logger.debug(">>> /api/auth détecté, on ne vérifie pas le JWT");
            filterChain.doFilter(request, response);
            return;
        }

        // On laisse passer la route d'inscription sans vérifier le token
        if (uri.equals("/api/visiteur/inscription")) {
            logger.debug(">>> /api/visiteur/inscription détecté, on ne vérifie pas le JWT");
            filterChain.doFilter(request, response);
            return;
        }


        if (authHeader != null) {
            String token = authHeader.substring(7);

            System.out.println("Token = " + token);

            Optional<String> optUsername = JwtUtils.validate(token);

            if (optUsername.isPresent()) {
                String username = optUsername.get();
                System.out.println("jeton valide, user = " + username);

                Claims claims = JwtUtils.getClaims(token);

                String role = claims.get("role", String.class);
                Integer id = Integer.parseInt(claims.getSubject());

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));

                // Recréer une Authentication Spring Security
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(id, null, authorities);

                // Affecter l'authentication dans le contexte de Spring Security -> lui dire OK, l'utilisateur est authentifié !
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Pour passer à la suite
        filterChain.doFilter(request, response);
    }
}
