package classes;

import java.util.ArrayList;
import java.util.List;

public class FilDeDiscussion {
	private String titre;
	private Utilisateur createur;
	private Groupe groupe;
	private int idFil;
	private List<Message> conversation = new ArrayList<>();
	
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
	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur,List<Message> messages) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
		this.conversation=messages;
	}

	// ---------------------------------------------------------------------------
	public void addMessage(Message msg) {
		try {
			db.addMessageToFil(this.idFil, msg);
			conversation.add(msg);
		} catch (DataBaseException e) {
			System.out.println("PROBLEME BASE DE DONNEES");
		}
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