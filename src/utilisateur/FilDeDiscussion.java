package utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilDeDiscussion {
	private String titre;
	private Utilisateur createur;
	private Groupe groupe;
	private int idFil;

	public int getIdFil() {
		return idFil;
	}

	public void setIdFil(int idFil) {
		this.idFil = idFil;
	}

	private List<Message> conversation = new ArrayList<>();

	// ---------------------------------------------------------------------------
	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
	}

	// ---------------------------------------------------------------------------
	public void addMessage(Message msg) {
		// TODO LA AUSSI IL FAUT APPELER LE SERVEUR POUR AJOUTER LE MESSAGE DANS LA BD
		conversation.add(msg);
	}

	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		return titre;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public List<Message> getConversation() {
		return conversation;
	}

	public String getTitre() {
		return titre;
	}

	public Utilisateur getCreateur() {
		return createur;
	}

	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}
}
