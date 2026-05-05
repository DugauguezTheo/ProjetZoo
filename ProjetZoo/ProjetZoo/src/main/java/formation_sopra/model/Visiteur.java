package formation_sopra.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "visiteur")
public class Visiteur extends Compte {
	
	@Column(name="nom", length = 80, nullable = false)
	private String nom;
	
	@Column(name="prenom", length = 50, nullable = false)
	private String prenom;
	
	@Column(name="date_naissance", nullable = false)
	@DateTimeFormat(pattern = "dd--MM--yyyy")
	private LocalDate dateNaissance;
	
	@Column(name="points_fildelite")
	private Integer pointsFidelites;

	@OneToMany(mappedBy = "visiteur")
	private List<Achat> achats;
	
	@Embedded //pas utile je pense
	// @OneToOne(cascade = CascadeType.ALL) //permet d'enregistrer, modifier ou supprimer automatiquement selon la commande
	private Adresse adresse;
	
	public Visiteur(String nom, String prenom, LocalDate dateNaissance, Integer pointsFidelites, String numero,String voie,String ville,String cp) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.pointsFidelites = pointsFidelites;
		this.adresse=new Adresse(numero,voie,ville,cp);
	}
	
	public Visiteur() {}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Integer getPointsFidelites() {
		return pointsFidelites;
	}

	public void setPointsFidelites(Integer pointsFidelites) {
		this.pointsFidelites = pointsFidelites;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	@Override
	public String toString() {
		return "Visiteur [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", pointsFidelites="
				+ pointsFidelites + ", adresse=" + adresse + "]";
	}
	

}
