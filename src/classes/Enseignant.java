package classes;

public class Enseignant extends Utilisateur{
	private static final long serialVersionUID = 198167135746422428L;

	public Enseignant(String nom, String prenom) {
		super(nom, prenom);

	}
	
	public Enseignant(String nom, String prenom, String login , String mdp , int IdUser)
	{
		super(nom, prenom, login, mdp, IdUser, TypeUtilisateur.ENSEIGNANT);
	}
	public Enseignant(String nom, String prenom, String login , String mdp)
	{
		super(nom, prenom, login, mdp, TypeUtilisateur.ENSEIGNANT);
	}

}
