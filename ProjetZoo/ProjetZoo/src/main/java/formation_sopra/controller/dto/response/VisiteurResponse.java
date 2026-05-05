package formation_sopra.controller.dto.response;

import java.time.LocalDate;
import java.util.List;

import formation_sopra.model.Visiteur;

public record VisiteurResponse(
    Integer id,
    String login,
    String password,
    String nom,
    String prenom,
    LocalDate dateNaissance,
    Integer pointsFidelite,
    List<Integer> achatsIds, 
    String numeroVoie,
    String voie,
    String ville,
    String cp
) 
{

    public static VisiteurResponse convert(Visiteur visiteur) {
        VisiteurResponse resp = new VisiteurResponse(
            visiteur.getId(), 
            visiteur.getLogin(), 
            visiteur.getPassword(),
            visiteur.getNom(),
            visiteur.getPrenom(),
            visiteur.getDateNaissance(), 
            visiteur.getPointsFidelites(),
            visiteur.getAchats().stream()
                .map(a -> a.getReference())
                .toList(),
            visiteur.getAdresse().getNumero(),
            visiteur.getAdresse().getVoie(),
            visiteur.getAdresse().getVille(),
            visiteur.getAdresse().getCp()
        );

        return resp;
    }
}
