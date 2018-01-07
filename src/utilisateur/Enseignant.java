package utilisateur;

public class Enseignant extends Utilisateur{
	private static final long serialVersionUID = 198167135746422428L;

	public Enseignant(String nom, String prenom) {
		super(nom, prenom);

	}
	
	public Enseignant(String nom, String prenom, String login , String mdp , int IdUser,TypeUtilisateur type)
	{
		super(nom, prenom, login, mdp, IdUser, TypeUtilisateur.ENSEIGNANT);
	}

}
