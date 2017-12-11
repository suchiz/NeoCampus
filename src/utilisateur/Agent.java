package utilisateur;

public class Agent extends Utilisateur{
	private Service service;

	public Agent(String nom, String prenom, Service service) {
		super(nom, prenom);
		this.service = service;
	}

	public Service getService() {
		return service;
	}
}
