package utilisateur;

public class Etudiant extends Utilisateur {
	private static final long serialVersionUID = -3973319087851392544L;

	public Etudiant(String nom, String prenom) {
		super(nom, prenom);
	}

	public Etudiant(String nom, String prenom, String mdp, String login, int IdUser) {
		super(nom, prenom, mdp, login, IdUser, TypeUtilisateur.ETUDIANT);

	}

	public Etudiant(String nom, String prenom, String mdp, String login) {
		super(nom, prenom, mdp, login, TypeUtilisateur.ETUDIANT);

	}

	public int HashCode() {
		return super.HashCode();
	}

	public boolean equals(Object o) {
		return super.equals(o);
	}

}
