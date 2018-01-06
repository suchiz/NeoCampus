package utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class FilDeDiscussion {
	private String titre;
	private Utilisateur createur;
	private Groupe groupe;
	private int idFil;
	
	private List<Message> conversation = new ArrayList<>();
	
// ---------------------------------------------------------------------------	
	public FilDeDiscussion(String titre, Groupe groupe, Utilisateur createur) {
		this.titre = titre;
		this.groupe = groupe;
		this.setCreateur(createur);
	}
// ---------------------------------------------------------------------------	
	public void stockageBDD( FilDeDiscussion fil)
	{
		/* Connexion � la base de donn�es */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
		    connexion = DriverManager.getConnection(url,username,mdp);

		    /* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();
			
			statement.executeUpdate("INSERT INTO FilDeDiscussion (ID_FilDeDiscussion,Titre) VALUES ('"+this.idFil+"','"+this.titre+"';");

		} catch ( SQLException e ) {
		    /* G�rer les �ventuelles erreurs ici */
		} finally {
		    if ( connexion != null )
		        try {
		            /* Fermeture de la connexion */
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
	}
// ---------------------------------------------------------------------------	
	public void addMessage(Message msg){
		conversation.add(msg);
	}
// ---------------------------------------------------------------------------	
	@Override
	public String toString() {
		return titre;
	}

	public Groupe getGroupe() {
		return groupe;
	}
	
	public List<Message> getConversation() {
		return conversation;
	}
	
	public String getTitre() {
		return titre;
	}

	public Utilisateur getCreateur() {
		return createur;
	}

	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}	
}
