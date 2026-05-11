package formation_sopra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="compte")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(length = 50, nullable = false, unique = true)
	protected String login;

	@Column(length = 100, nullable = false)
	protected String password;

	public Compte() {}

	public Compte(Integer id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}

	// NOUVEAU : rôle déduit dynamiquement
	public String getRole() {
		if (this instanceof Admin) {
			return "ADMIN";
		}
		if (this instanceof Veterinaire) {
			return "VETERINAIRE";
		}
		if (this instanceof Visiteur) {
			return "VISITEUR";
		}
		return "UNKNOWN";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}