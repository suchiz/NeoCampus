package utilisateur;

public class Agent extends Utilisateur{
	private Service service;

	public Agent(String nom, String prenom, int id, Service service) {
		super(nom, prenom, id);
		this.service = service;
	}

	public Service getService() {
		return service;
	}
}
