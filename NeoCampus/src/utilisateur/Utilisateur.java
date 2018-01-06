package utilisateur;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class Utilisateur {
	private String nom;
	private String prenom;

	public Utilisateur(String nom, String prenom) {
		super();
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
}
