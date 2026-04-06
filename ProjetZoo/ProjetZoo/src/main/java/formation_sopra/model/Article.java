package formation_sopra.model;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table; 

@Entity
@Table(name="article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String libelle;
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    @Range(min = 0, max = 1000)
    private double prix;
    
    @Column(name="quantite_en_stock", nullable = false)
    private int quantiteStock;

    @OneToMany(mappedBy = "article")
    private List<Achat> ventes;

    public Article() {
    }

	public Article(Integer id, String libelle, double prix, int quantiteStock, List<Achat> ventes) {
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.quantiteStock = quantiteStock;
		this.ventes = ventes;
	}
	
	public Article(String libelle, double prix, int quantiteStock, List<Achat> ventes) {
		this.libelle = libelle;
		this.prix = prix;
		this.quantiteStock = quantiteStock;
		this.ventes = ventes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public List<Achat> getVentes() {
		return ventes;
	}

	public void setVentes(List<Achat> ventes) {
		this.ventes = ventes;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", quantiteStock=" + quantiteStock
				+ ", ventes=" + ventes + "]";
	}
	
	
    
}
