package utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	public void stockageBDD() {
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requêtes vers la BDD */
			Statement statement = connexion.createStatement();

			int statut = statement.executeUpdate(
					"INSERT INTO Fil_De_Discussion (TITRE_FIL_DE_DISCUSSION) VALUES ('" + this.titre + "');");

			ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

			while (indicedanslabasededonnee.next()) {
				int idFilDediscussionn = indicedanslabasededonnee.getInt("ID");
				this.idFil = idFilDediscussionn;
				System.out.println(idFilDediscussionn);
			}

			int a = statement.executeUpdate("INSERT INTO Creer (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR) VALUES ("
					+ this.idFil + "," + createur.getIdUser() + ");");

			int b = statement
					.executeUpdate("INSERT INTO destine (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE) VALUES ("
							+ this.idFil + "," + createur.getIdUser() + "," + this.groupe.getIdGroupe() + ");");

		} catch (SQLException e) {
			/* Gérer les éventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}

	}

	// ---------------------------------------------------------------------------
	public void addMessage(Message msg) {
		System.out.println("MESSAGE : +" + msg.getMsg());
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		int idmessage = 0;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requêtes vers la BDD */
			Statement statement = connexion.createStatement();

			int a = statement.executeUpdate("INSERT INTO MESSAGE (CONTENU_MESSAGE) VALUES ('" + msg.getMsg() + "');");

			ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

			if (indicedanslabasededonnee.next()) {
				idmessage = indicedanslabasededonnee.getInt("ID");
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date now = new Date();
			try {
				System.out.println(msg.getIdMsg());
				System.out.println(msg.getAuteur());
				System.out.println("VOILA :" + msg.getAuteur().getIdUser());
				System.out.println(idmessage);
				System.out.println(dateFormat.format(now));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int b = statement
					.executeUpdate("INSERT INTO envoyer_message (ID_UTILISATEUR,ID_MESSAGE,DATE_ENVOI_MESSAGE) VALUES ("
							+ msg.getAuteur().getIdUser() + "," + idmessage + ",'" + dateFormat.format(now) + "');");

			int statut = statement.executeUpdate("INSERT INTO CONTIENT (ID_FIL_DE_DISCUSSION,ID_MESSAGE) VALUES ("
					+ this.idFil + "," + idmessage + ");");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}
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
