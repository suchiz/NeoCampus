package utilisateur;

import java.io.Serializable;


public abstract class Utilisateur implements Serializable{

	private static final long serialVersionUID = -3928219126348549952L;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private int idUser;

	public Utilisateur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return nom + " "+ prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	
}
