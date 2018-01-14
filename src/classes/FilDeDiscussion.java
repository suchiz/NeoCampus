package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilDeDiscussion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -424381409067590736L;
	private String titre;
	private Utilisateur createur;
	private Groupe groupe;
	private int idFil;
	private List<Message> conversation = new ArrayList<>();
	private int messagesNonLus = 0;

	public int getMessagesNonLus() {
		return messagesNonLus;
	}

	public void setMessagesNonLus(int messagesNonLus) {
		this.messagesNonLus = messagesNonLus;
	}

	public void incrementMessageNonLu() {
		messagesNonLus++;
	}

	private DB db = new DB();

	public int getIdFil() {
		return idFil;
	}

	public void setIdFil(int idFil) {
		this.idFil = idFil;
	}

	// ---------------------------------------------------------------------------
	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
	}

	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur, List<Message> messages) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
		this.conversation = messages;
	}

	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur, List<Message> messages, int idFil) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
		this.conversation = messages;
		this.idFil = idFil;
	}

	// ---------------------------------------------------------------------------
	public void addMessage(Message msg) {

		try {
			System.out.println("Bonjouterztze");
			db.addMessageToFil(this.idFil, msg);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		conversation.add(msg);

	}

	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		if (messagesNonLus > 0)
			return titre + "(" + messagesNonLus + ")";
		else
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
