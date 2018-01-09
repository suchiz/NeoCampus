
package utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {

	public void creation_bd() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
			String user = "root";
			String pwd = "root";

			Connection conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connexion effective !");

			Statement s = conn.createStatement();

			String req = "DROP DATABASE base_de_donnees_neocampus;CREATE DATABASE base_de_donnees_neocampus;USE base_de_donnees_neocampus;CREATE TABLE UTILISATEUR( ID_UTILISATEUR INT NOT NULL AUTO_INCREMENT, IDENTIFIANT VARCHAR(255), MOT_DE_PASSE VARCHAR(255), NOM_UTILISATEUR VARCHAR(255), PRENOM_UTILISATEUR VARCHAR(255), TYPE_UTILISATEUR ENUM('ENSEIGNANT','ETUDIANT','TECH','ADMN'), PRIMARY KEY (ID_UTILISATEUR));CREATE TABLE MESSAGE( ID_MESSAGE INT NOT NULL AUTO_INCREMENT, CONTENU_MESSAGE VARCHAR(2048), PRIMARY KEY (ID_MESSAGE));CREATE TABLE FIL_DE_DISCUSSION( ID_FIL_DE_DISCUSSION INT NOT NULL AUTO_INCREMENT, TITRE_FIL_DE_DISCUSSION VARCHAR(1000), PRIMARY KEY (ID_FIL_DE_DISCUSSION));CREATE TABLE GROUPE( ID_GROUPE INT NOT NULL AUTO_INCREMENT, NOM_GROUPE VARCHAR(100), PRIMARY KEY (ID_GROUPE));CREATE TABLE APPARTENIR( ID_GROUPE INT NOT NULL, ID_UTILISATEUR INT NOT NULL, PRIMARY KEY (ID_GROUPE,ID_UTILISATEUR), FOREIGN KEY (ID_GROUPE) REFERENCES GROUPE(ID_GROUPE), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR));CREATE TABLE ENVOYER_MESSAGE( ID_UTILISATEUR INT NOT NULL, ID_MESSAGE INT NOT NULL, DATE_ENVOI_MESSAGE DATETIME, PRIMARY KEY (ID_UTILISATEUR,ID_MESSAGE), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR), FOREIGN KEY (ID_MESSAGE) REFERENCES MESSAGE(ID_MESSAGE));CREATE TABLE CONTIENT( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_MESSAGE INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_MESSAGE), FOREIGN KEY (ID_MESSAGE) REFERENCES MESSAGE(ID_MESSAGE), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION));CREATE TABLE CREER( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_UTILISATEUR INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR));CREATE TABLE DESTINE( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_UTILISATEUR INT NOT NULL, ID_GROUPE INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR), FOREIGN KEY (ID_GROUPE) REFERENCES GROUPE(ID_GROUPE));";

			String[] reqs = req.split(";");

			for (String r : reqs) {
				System.out.println("Requete : " + r);
				System.out.println("=) " + s.executeUpdate(r + ";"));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Utilisateur UtilisateurFromID(int idUtilisateur) {
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;

		Utilisateur u = null;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			Statement statement = connexion.createStatement();
			ResultSet utilisateurBD = statement
					.executeQuery("SELECT * FROM UTILISATEUR WHERE ID_UTILISATEUR = " + idUtilisateur);

			int id_utilisateur = 0;
			String identifiant = null;

			String mdp2 = null;

			String nom = null;
			String prenom = null;
			String type = null;
			if (utilisateurBD.next()) {
				id_utilisateur = utilisateurBD.getInt("ID_UTILISATEUR");
				identifiant = utilisateurBD.getString("IDENTIFIANT");

				mdp2 = utilisateurBD.getString("MOT_DE_PASSE");

				nom = utilisateurBD.getString("NOM_UTILISATEUR");
				prenom = utilisateurBD.getString("PRENOM_UTILISATEUR");
				type = utilisateurBD.getString("TYPE_UTILISATEUR");
			}

			switch (type) {
			case "ETUDIANT":
				u = new Etudiant(nom, prenom, mdp2, identifiant, id_utilisateur);
				break;
			case "ENSEIGNANT":
				u = new Enseignant(nom, prenom, identifiant, mdp2, id_utilisateur);
				break;
			case "ADMINISTRATIF":
				u = new Agent(nom, prenom, identifiant, mdp2, id_utilisateur, TypeUtilisateur.ADMINISTRATIF);
				break;
			case "TECHNIQUE":
				u = new Agent(nom, prenom, identifiant, mdp2, id_utilisateur, TypeUtilisateur.TECHNIQUE);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}

	public Groupe groupeFromId(int idGroup) {
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;

		Groupe g = null;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			Statement statement = connexion.createStatement();
			ResultSet gorupeBD = statement.executeQuery("SELECT * FROM GROUPE WHERE ID_GROUPE = " + idGroup + ")");

			String nomGroupe = gorupeBD.getString("NOM_GROUPE");

			List<Utilisateur> listeUser = new ArrayList();
			String nom;
			String prenom;
			int IdUser;
			String login;
			String mdp2;
			TypeUtilisateur typeutilisateur;
			Service service;

			ResultSet resultat = statement.executeQuery(
					"SELECT * FROM utilisateur as U WHERE U.ID_UTILISATEUR IN (SELECT ID_UTILISATEUR FROM appartenir WHERE ID_GROUPE ='"
							+ idGroup + "';");

			while (resultat.next()) {
				nom = resultat.getString("Nom_Utilisateur");
				prenom = resultat.getString("Prenom_Utilisateur");
				IdUser = resultat.getInt("ID_Utilisateur");
				login = resultat.getString("Identifiant");
				mdp = resultat.getString("Mot_De_Passe");
				typeutilisateur = (TypeUtilisateur) resultat.getObject("TypeUtilisateur");

				if (typeutilisateur == TypeUtilisateur.ETUDIANT) {
					Etudiant etudiant = new Etudiant(nom, prenom, mdp, login, IdUser);
					listeUser.add(etudiant);
				} else if (typeutilisateur == TypeUtilisateur.ENSEIGNANT) {
					Enseignant enseignant = new Enseignant(nom, prenom, mdp, login, IdUser);
					listeUser.add(enseignant);
				} else {
					Agent agent = new Agent(nom, prenom, mdp, login, IdUser, typeutilisateur);
					listeUser.add(agent);
				}

			}
			g.setListeUtilisateur(listeUser);

		} catch (SQLException e) {
			/* G�rer les �ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */

				} catch (Exception e) {
					e.printStackTrace();
				}

			return g;
		}
	}

	public Utilisateur login(String login, String string) {

		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		String logintomatch = null;
		String motdepasstomatch = null;
		Utilisateur u = null;
		int idUser = 0;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			// RECUPERATION LOGIN VIA LA BDD
			String req = "SELECT IDENTIFIANT,MOT_DE_PASSE,ID_UTILISATEUR FROM UTILISATEUR WHERE IDENTIFIANT='" + login
					+ "';";
			System.out.println(req);
			ResultSet resultat1 = statement.executeQuery(req);
			if (resultat1.next()) {
				logintomatch = resultat1.getString("IDENTIFIANT");
				motdepasstomatch = resultat1.getString("MOT_DE_PASSE");
				idUser = resultat1.getInt("ID_UTILISATEUR");

				System.out.println("IDENTIFIANT:" + logintomatch + ";MOT_DE_PASSE:" + motdepasstomatch
						+ ";ID_UTILISATEUR:" + idUser);
			}
			// COMPARAISON
			if (login.equals(logintomatch) && string.equals(motdepasstomatch) && !string.equals(""))
				u = DB.UtilisateurFromID(idUser);

		} catch (SQLException e) {
			/* G�rer les �ventuelles erreurs ici */
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
