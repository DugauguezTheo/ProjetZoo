package formation_sopra.security.jwt;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import formation_sopra.model.Compte;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
    private static String JWT_KEY = "6E5A7234753778214125442A472D4B6150645367556B58703273357638792F42";

    private JwtUtils() { }

    public static String generate(Authentication auth, Compte compte) {
    Date now = new Date();
    SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

    String role = auth.getAuthorities()
        .stream()
        .findFirst()
        .map(a -> a.getAuthority())
        .orElse("ROLE_VISITEUR");

    return Jwts.builder()
        .subject(compte.getId().toString()) // ← garde l'id
        .claim("login", compte.getLogin())
        .claim("role", role)
        .issuedAt(now)
        .expiration(new Date(now.getTime() + 3_600_000))
        .signWith(secretKey)
        .compact();
}

    public static Optional<String> validate(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        try {
            return Optional.of(Jwts.parser()
                    .verifyWith(secretKey) // On donne la clé pour valider le jeton
                    .build()
                    .parseSignedClaims(token) // On donne le jeton à valider
                    .getPayload() // Le contenu du jeton
                    .getSubject() // Le nom d'utilisateur
            );
        }

        catch (Exception ex) {
            return Optional.empty();
        }
    }

    public static Claims getClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
