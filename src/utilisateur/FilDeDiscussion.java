package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class FilDeDiscussion {
	private Groupe groupe = new Groupe();
	private List<Message> convo = new ArrayList<>();
	private String titre;
	
	public FilDeDiscussion(String titre, Groupe groupe) {
		this.titre = titre;
		this.groupe = groupe;
	}
	
	@Override
	public String toString() {
		return  titre;
	}

	public Groupe getGroupe() {
		return groupe;
	}
	
	public List<Message> getConvo() {
		return convo;
	}
	
	public String getTitre() {
		return titre;
	}	
}
