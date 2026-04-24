package formation_sopra.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import model.Compte;
import view.Views;

@Entity
@Table (name = "visiteur")
public class Visiteur extends Compte {
	
	@Column(name="Nom")
	@JsonView(Views.Common.class)
	private String nom;
	
	@Column(name="Prénom")
	@JsonView(Views.Common.class)
	private String prenom;
	
	@Column(name="Date de naissance")
	@JsonFormat(pattern = "dd--MM--yyyy")
	private LocalDate dateNaissance;
	
	@Column(name="Points de fidélités")
	@JsonView(Views.Common.class)
	private Integer pointsFidelites;
	
	//@Embedded pas utile je pense
	@OneToOne(cascade = CascadeType.ALL) //permet d'enregistrer, modifier ou supprimer automatiquement selon la commande
	@JsonView(Views.Common.class)
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

	@Override
	public String toString() {
		return "Visiteur [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", pointsFidelites="
				+ pointsFidelites + ", adresse=" + adresse + "]";
	}
	

}
