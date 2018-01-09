package utilisateur;

public class Agent extends Utilisateur{
	private static final long serialVersionUID = 6347866291761750368L;
	private TypeUtilisateur type;

	public Agent(String nom, String prenom, TypeUtilisateur type) {
		super(nom, prenom);
		this.type = type;
	}
	
	public Agent(String nom, String prenom, String login , String mdp , int IdUser, TypeUtilisateur type)
	{
		super(nom, prenom, login, mdp, IdUser,type);		
	}

	public Agent(String nom, String prenom, String login , String mdp ,  TypeUtilisateur type)
	{
		super(nom, prenom, login, mdp,type);		
	}

	public TypeUtilisateur getService() {
		return type;
	}
}
