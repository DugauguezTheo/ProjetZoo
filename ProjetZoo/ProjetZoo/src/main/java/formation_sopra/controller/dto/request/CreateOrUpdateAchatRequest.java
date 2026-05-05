package formation_sopra.controller.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record CreateOrUpdateAchatRequest(

    @NotNull(message = "L'article acheté ne peut pas être Null")
    Integer idArticle, 

    @Min(1)
    @NotNull(message = "On ne peut pas acheter une quantité Null")
    int quantite, 

    @NotNull(message = "Le prix unitaire ne peut pas être Null")
    @Min(0)
    double prixUnitaireATM, 

    @PastOrPresent(message = "La date d'achat a lieu au plus tard aujourd'hui")
    @NotNull
    LocalDate dateAchat,
    
    @NotNull(message = "Le visiteur qui achète le produit n'est pas Null")
    Integer idVisiteur
) 
{

}
