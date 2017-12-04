package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
	private String nomGroupe;
	private List<Utilisateur> listUtilisateur = new ArrayList<>();
	
	public Groupe(){
		
	}
	
	public Groupe(Utilisateur... grpUtil){
		for (Utilisateur temp: grpUtil)
			listUtilisateur.add(temp);
	}
	
	public Groupe(List<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}

	public List<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}
	
	public void addGroup(Utilisateur user){
		this.listUtilisateur.add(user);
	}

	public String getNomGroupe() {
		return nomGroupe;
	}
	
}
