package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Groupe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5850291596533942198L;
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

	public Groupe(String nomGroupe, int id, List<Utilisateur> listeUtilisateur) {
		this.nomGroupe = nomGroupe;
		this.idGroupe = id;
		this.listeUtilisateur = listeUtilisateur;
	}

	// -------------------------------------------------------------------------------
	@Override
	public String toString() {
		return nomGroupe;
	}

	// -------------------------------------------------------------------------------
	public void addMembers(List<Utilisateur> l) {
		for (Utilisateur u : l)
			addMember(u);
	}

	// -------------------------------------------------------------------------------
	public void initMembers(List<Utilisateur> l) {
		try {

			for (Utilisateur u : l)
				if (!listeUtilisateur.contains(u)) {
					this.listeUtilisateur.add(u);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMember(Utilisateur user) {
		try {
			if (!listeUtilisateur.contains(user)) {
				this.listeUtilisateur.add(user);
				db.addUserToGroup(user.getIdUser(), idGroupe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
