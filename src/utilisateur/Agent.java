package utilisateur;

public class Agent extends Utilisateur{
	private static final long serialVersionUID = 6347866291761750368L;
	private Service service;

	public Agent(String nom, String prenom, Service service) {
		super(nom, prenom);
		this.service = service;
	}

	public Service getService() {
		return service;
	}
}
