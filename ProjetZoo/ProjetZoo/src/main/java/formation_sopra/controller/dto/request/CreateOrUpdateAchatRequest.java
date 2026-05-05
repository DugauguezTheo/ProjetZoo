package formation_sopra.controller.dto.request;

import java.time.LocalDate;

public record CreateOrUpdateAchatRequest(Integer idArticle, int quantite, double prixReel, LocalDate dateAchat, Integer idVisiteur) {

}
