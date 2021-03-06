package classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Utilisateur implements Serializable {

	private static final long serialVersionUID = -3928219126348549952L;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private int idUser = 0;
	private TypeUtilisateur type;

	public Utilisateur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Utilisateur) {
			Utilisateur u = (Utilisateur) o;
			return u.getIdUser() == this.idUser;
		}
		return false;
	}

	public int HashCode() {
		int code = idUser * 17;

		return code;

	}

	public Utilisateur(String nom, String prenom, String login, String mdp, int idUser, TypeUtilisateur type) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.idUser = idUser;
		this.type = type;

	}

	public Utilisateur(String nom, String prenom, String login, String mdp, TypeUtilisateur type) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.type = type;

	}

	@Override
	public String toString() {
		return nom + " " + prenom;
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
