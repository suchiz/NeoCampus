package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class FilDeDiscussion {
	private Utilisateur createur;
	private Groupe groupe;
	private List<Message> conversation = new ArrayList<>();
	private String titre;
	
	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
	}
	
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
