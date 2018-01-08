package utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Groupe {
	private String nomGroupe;
	private List<Utilisateur> listeUtilisateur = new ArrayList<>();
	private int idGroupe;

	// -------------------------------------------------------------------------------
	public Groupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public Groupe(String nomGroupe, int id) {
		this.nomGroupe = nomGroupe;
		this.idGroupe = id;
	}

	// -------------------------------------------------------------------------------
	@Override
	public String toString() {
		return nomGroupe;
	}

	// -------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------
	public void addMember(Utilisateur user) {
		this.listeUtilisateur.add(user);
		addMemberBDD(user);
	}

	// -------------------------------------------------------------------------------
	public void deleteMember(Utilisateur user) {
		this.listeUtilisateur.remove(user);
		deleteMemberBDD(user);
	}

	// -------------------------------------------------------------------------------
	public void deleteMemberBDD(Utilisateur user) {
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			statement.executeUpdate("DELETE FROM APPARTENIR (ID_Utilisateur,ID_Groupe) WHERE (ID_Utilisateur ='"
					+ user.getIdUser() + "'and'" + this.idGroupe + "';");

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
	public void addMemberBDD(Utilisateur user) {
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);
			Statement statement = connexion.createStatement();
			statement.executeUpdate("INSERT INTO APPARTENIR (ID_Utilisateur,ID_Groupe) VALUES ('" + user.getIdUser()
					+ "','" + this.idGroupe + "');");

		} catch (SQLException e) {
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
				}
		}
	}

	// -------------------------------------------------------------------------------
	public void stockageGrpBDD() {
		/* Connexion ï¿½ la base de donnï¿½es */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();
			
			System.out.println();

			statement.executeUpdate("INSERT INTO Groupe (NOM_GROUPE) VALUES ('" + this.nomGroupe + "');");
			ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

			if (indicedanslabasededonnee.next()) {
				this.idGroupe = indicedanslabasededonnee.getInt("ID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
	public List<Groupe> retrieveAllGroups() {

		List<Groupe> listeGroupe = new ArrayList<Groupe>();
		String nomgroupe;
		int idgroupe;

		/* Connexion � la base de donn�es */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			ResultSet resultat = statement.executeQuery("SELECT (ID_GROUPE) , (Nom_Groupe) FROM Groupe ;");
			while (resultat.next()) {
				idgroupe = resultat.getInt("ID_GROUPE");
				nomgroupe = resultat.getString("Nom_Groupe");
				Groupe groupetest = new Groupe(nomgroupe, idgroupe);
				listeGroupe.add(groupetest);
			}

		} catch (SQLException e) {
			/* G�rer les �ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}
		return listeGroupe;
	}

	// -------------------------------------------------------------------------------
	// RENVOIE LA LISTE DES PERSONNES FESANT PARTIE DUN GROUPE
	public List<Utilisateur> retriveUsersFromGroupe() {

		List<Utilisateur> listeUser = new ArrayList();
		String nom;
		String prenom;
		int IdUser;
		String login;
		String mdp;
		TypeUtilisateur typeutilisateur;
		Service service;

		/* Connexion � la base de donn�es */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp1 = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp1);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			ResultSet resultat = statement.executeQuery(
					"SELECT * FROM utilisateur as U WHERE U.ID_UTILISATEUR IN (SELECT ID_UTILISATEUR FROM appartenir WHERE ID_GROUPE ='"
							+ this.idGroupe + "';");

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
					Enseignant enseignant = new Enseignant(nom, prenom, mdp, login, IdUser, TypeUtilisateur.ENSEIGNANT);
					listeUser.add(enseignant);
				} else {
					Agent agent = new Agent(nom, prenom, mdp, login, IdUser, typeutilisateur);
					listeUser.add(agent);
				}

			}

		} catch (SQLException e) {
			/* G�rer les �ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}

		return listeUser;
	}

	// -------------------------------------------------------------------------------
	// REMOVE UN GROUPE DE LA BDD
	public void removeGroupFromBDD() {
		/* Connexion ï¿½ la base de donnï¿½es */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// RECUPERATION DES FILS DE DISCUSSIONS D'UN GROUPE AFIN DE LES SUPPRIMER
			ResultSet resultat = statement.executeQuery(
					"SELECT ID_FIL_DE_DISCUSSION FROM DESTINE (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE) WHERE ID_GROUPE ='"
							+ this.idGroupe + "';");

			while (resultat.next()) {
				// DELETE LES FDD DU GROUPE CIBLE
				statement.executeUpdate(
						"DELETE ID_FIL_DE_DISCUSSION FROM FilDeDiscussion(ID_Fil_DE_DISCUSSION,Titre) WHERE ID_FIL_DE_DISCUSSION='"
								+ resultat.getInt("ID_FIL_DE_DISCUSSION") + "';");
			}

			// DELETE LE GROUPE DE LA TABLE DESTINE
			statement.executeUpdate(
					"DELETE ID_GROUPE  FROM DESTINE (ID_Fil_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE)WHERE ID_GROUPE='"
							+ this.idGroupe + "';");

			// DELETE LE GROUPE DE LA TABLE GROUPE
			statement.executeUpdate(
					"DELETE FROM GROUPE (ID_Utilisateur,ID_Groupe) WHERE (ID_Groupe ='" + this.idGroupe + "';");

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
	public String getNomGroupe() {
		return nomGroupe;
	}

	public List<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur;
	}

	public int getIdGroupe() {
		return idGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

}
