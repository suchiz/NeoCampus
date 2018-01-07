package utilisateur;

public class Etudiant extends Utilisateur{
	private static final long serialVersionUID = -3973319087851392544L;

	public Etudiant(String nom, String prenom) {
		super(nom, prenom);
	}
	
	public Etudiant(String nom , String prenom,String mdp, String login, int IdUser,TypeUtilisateur type)
	{
		super(nom, prenom, mdp, login, IdUser,TypeUtilisateur.ETUDIANT);
		
	}

}
