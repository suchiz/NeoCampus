package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class FilDeDiscussion {
	private List<Groupe> groupe = new ArrayList<>();
	private List<Message> convo = new ArrayList<>();
	private String titre;
	
	public FilDeDiscussion(String titre) {
		this.titre = titre;
	}
	
	public String afficherMessages(){
		String chaine = "";
		
		for (Message temp: this.convo){
			chaine += temp.getMsg() + ",";
		}
		
		return chaine;
	}
	
	@Override
	public String toString() {
		return  titre;
	}

	public List<Groupe> getGroupe() {
		return groupe;
	}
	
	public List<Message> getConvo() {
		return convo;
	}
	
	public String getTitre() {
		return titre;
	}	
}
