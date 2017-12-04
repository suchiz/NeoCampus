package utilisateur;

public abstract class Utilisateur {
	private String nom;
	private String prenom;
	private int id;
	
	public boolean equals (Object obj){
		if (obj != null && obj instanceof Utilisateur){
			Utilisateur ut = (Utilisateur) obj;
			return this.id == ut.id;
		}
		return false;
	}

	public Utilisateur(String nom, String prenom, int id) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getId() {
		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
