package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
	private String nomGroupe;
	private List<Utilisateur> listeUtilisateur = new ArrayList<>();
	private int idGroupe;
	private DB db = new DB();

	// -------------------------------------------------------------------------------
	public Groupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public Groupe(String nomGroupe, int id) {
		this.nomGroupe = nomGroupe;
		this.idGroupe = id;
	}

	// -------------------------------------------------------------------------------
	@Override
	public String toString() {
		return nomGroupe;
	}

	// -------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------
	public void addMember(Utilisateur user) {
		this.listeUtilisateur.add(user);
		db.addUserToGroup(user.getIdUser(), idGroupe);

	}

	// -------------------------------------------------------------------------------
	public void deleteMember(Utilisateur user) {
		this.listeUtilisateur.remove(user);

		// TODO FAUT COMMUNIQUER AVEC LE SERVEUR
		// deleteMemberBDD(user);
	}

	// -------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------
	public String getNomGroupe() {
		return nomGroupe;
	}

	public List<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur;
	}

	public int getIdGroupe() {
		return idGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

}
