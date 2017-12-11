package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
	private String nomGroupe;
	private List<Utilisateur> listUtilisateur = new ArrayList<>();
	
	public Groupe(String nomGroupe){
		this.nomGroupe = nomGroupe;
	}
	
	
	
	@Override
	public String toString() {
		return nomGroupe;
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
