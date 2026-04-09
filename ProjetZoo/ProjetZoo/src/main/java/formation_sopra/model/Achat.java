package formation_sopra.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table; 

@Entity
@Table(name="achat")
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reference;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;
    
    @Column(nullable = false)
    private int quantite;
    
    @Column(name = "prix_reel")
    private double prixReel;
    
    @Column(name = "date_achat", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAchat;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Visiteur visiteur; // Import de la classe Visiteur à faire quand elle sera créée
    
    public Achat() {
    }

    public Achat(Article article, int quantite, double prixReel, LocalDate dateAchat, Visiteur visiteur) {
        this.article = article;
        this.quantite = quantite;
        this.prixReel = prixReel;
        this.dateAchat = dateAchat;
        this.visiteur = visiteur;
    }

    public Achat(Integer reference, Article article, int quantite, double prixReel, LocalDate dateAchat, Visiteur visiteur) {
        this.reference = reference;
    	this.article = article;
        this.quantite = quantite;
        this.prixReel = prixReel;
        this.dateAchat = dateAchat;
        this.visiteur = visiteur;
    }

    
    public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixReel() {
		return prixReel;
	}

	public void setPrixReel(double prixReel) {
		this.prixReel = prixReel;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	public Visiteur getVisiteur() {
		return visiteur;
	}
	
	public void setVisiteur(Visiteur visiteur) {
		this.visiteur = visiteur;
	}

	@Override
	public String toString() {
		return "Achat [reference=" + reference + ", article=" + article + ", quantite=" + quantite + ", prixReel="
				+ prixReel + ", dateAchat=" + dateAchat + ", visiteur = " + visiteur + "]";
	}

}
