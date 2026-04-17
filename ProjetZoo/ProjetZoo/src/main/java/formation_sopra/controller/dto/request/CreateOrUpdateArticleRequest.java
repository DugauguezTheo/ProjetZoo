package formation_sopra.controller.dto.request;

import formation_sopra.model.Enclos;
import formation_sopra.model.Espece;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class CreateOrUpdateArticleRequest {

    private String libelle;
    private double prix;
    private int quantiteStock;
    
}
