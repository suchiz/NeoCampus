package utilisateur;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DB;

public abstract class Utilisateur implements Serializable {

	private static final long serialVersionUID = -3928219126348549952L;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private int idUser = 0;
	private TypeUtilisateur type;
	private DB db = new DB();

	public Utilisateur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public Utilisateur(String nom, String prenom, String login, String mdp, int idUser, TypeUtilisateur type) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.idUser = idUser;
		this.type = type;

	}

	public Utilisateur(String nom, String prenom, String login, String mdp, TypeUtilisateur type) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.type = type;

	}

	@Override
	public String toString() {
		return nom + " " + prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void stockageUserBDD() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver O.K.");
			/* Connexion à la base de données */
			String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
			String username = "root";
			String mdp = "root";

			Connection connexion = null;
			try {
				connexion = DriverManager.getConnection(url, username, mdp);
				System.out.println("SQL : "
						+ "INSERT INTO Utilisateur (Identifiant,Mot_De_Passe,Nom_Utilisateur,Prenom_Utilisateur,Type_Utilisateur) VALUES ('"
						+ this.login + "','" + this.mdp + "','" + this.nom + "','" + this.prenom + "','" + this.type
						+ "');");
				/* Ici, nous placerons nos requêtes vers la BDD */
				Statement statement = connexion.createStatement();

				int statut = statement.executeUpdate(
						"INSERT INTO Utilisateur (Identifiant,Mot_De_Passe,Nom_Utilisateur,Prenom_Utilisateur,Type_Utilisateur) VALUES ('"
								+ this.login + "','" + this.mdp + "','" + this.nom + "','" + this.prenom + "','"
								+ this.type + "');");

				ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

				if (indicedanslabasededonnee.next()) {
					int res = indicedanslabasededonnee.getInt("ID");
					this.idUser = res;
					System.out.println(res);
				}
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------
	public void removeUserFromBDD() {
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE
			statement.executeUpdate(
					"DELETE ID_UTILISATEUR FROM APPARTENIR (ID_Utilisateur,ID_Groupe) WHERE (ID_Utilisateur ='"
							+ this.idUser + "';");

			// REMOVAL DE LA TABLE USER
			statement.executeUpdate(
					"DELETE ID_UTILISATEUR FROM UTILISATEUR (Identifiant,Mot_De_Passe,Nom_Utilisateur,Prenom_Utilisateur,Type_Utilisateur) WHERE (ID_Utilisateur ='"
							+ this.idUser + "';");

		} catch (SQLException e) {
			/* Gï¿½rer les ï¿½ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
					 */
				}
		}
	}

	// -------------------------------------------------------------------------------
	public Utilisateur login(String login, String motdepasse) {

		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		String logintomatch = null;
		String motdepasstomatch = null;
		Utilisateur u = null;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// RECUPERATION LOGIN VIA LA BDD
			ResultSet resultat1 = statement.executeQuery(
					"SELECT IDENTIFIANT,MOT_DE_PASSE,ID_UTILISATEUR FROM UTILISATEUR (Identifiant,Mot_De_Passe,Nom_Utilisateur,Prenom_Utilisateur,Type_Utilisateur) WHERE IDENTIFIANT='"
							+ login + "';");
			if (resultat1.next()) {
				logintomatch = resultat1.getString("IDENTIFIANT");
				motdepasstomatch = resultat1.getString("MOT_DE_PASSE");
				this.idUser = resultat1.getInt("ID_UTILISATEUR");
			}
			// COMPARAISON
			if (login.equals(logintomatch) && motdepasse.equals(motdepasstomatch) && ! motdepasse.equals(""))
				u = DB.UtilisateurFromID(this.idUser);

		} catch (SQLException e) {
			/* Gï¿½rer les ï¿½ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
					 */
				}
		}

		return u;
	}

}
