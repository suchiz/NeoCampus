
package classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6347298237211490750L;
	static String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
	static String username = "root";
	static String mdp = "";
	static Connection connexion = null;

	public void init() {
		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, mdp);
			System.out.println("Connexion effective !");

			Statement s = conn.createStatement();
			s.executeUpdate("CREATE DATABASE base_de_donnees_neocampus");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creation_bd() throws DataBaseException {
		// init();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver O.K.");

			try {
				connexion = DriverManager.getConnection(url, username, mdp);
			} catch (Exception e) {
				connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, mdp);
			}

			// System.out.println("Connexion effective !");

			Statement s = connexion.createStatement();

			String req = "DROP DATABASE IF EXISTS base_de_donnees_neocampus;CREATE DATABASE base_de_donnees_neocampus;USE base_de_donnees_neocampus;CREATE TABLE UTILISATEUR( ID_UTILISATEUR INT NOT NULL AUTO_INCREMENT, IDENTIFIANT VARCHAR(255), MOT_DE_PASSE VARCHAR(255), NOM_UTILISATEUR VARCHAR(255), PRENOM_UTILISATEUR VARCHAR(255), TYPE_UTILISATEUR ENUM('ENSEIGNANT','ETUDIANT','TECHNIQUE','ADMINISTRATIF'), PRIMARY KEY (ID_UTILISATEUR));CREATE TABLE MESSAGE( ID_MESSAGE INT NOT NULL AUTO_INCREMENT, CONTENU_MESSAGE VARCHAR(2048), PRIMARY KEY (ID_MESSAGE));CREATE TABLE FIL_DE_DISCUSSION( ID_FIL_DE_DISCUSSION INT NOT NULL AUTO_INCREMENT, TITRE_FIL_DE_DISCUSSION VARCHAR(1000), PRIMARY KEY (ID_FIL_DE_DISCUSSION));CREATE TABLE GROUPE( ID_GROUPE INT NOT NULL AUTO_INCREMENT, NOM_GROUPE VARCHAR(100), PRIMARY KEY (ID_GROUPE));CREATE TABLE APPARTENIR( ID_GROUPE INT NOT NULL, ID_UTILISATEUR INT NOT NULL, PRIMARY KEY (ID_GROUPE,ID_UTILISATEUR), FOREIGN KEY (ID_GROUPE) REFERENCES GROUPE(ID_GROUPE), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR));CREATE TABLE ENVOYER_MESSAGE( ID_UTILISATEUR INT NOT NULL, ID_MESSAGE INT NOT NULL, DATE_ENVOI_MESSAGE DATETIME, PRIMARY KEY (ID_UTILISATEUR,ID_MESSAGE), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR), FOREIGN KEY (ID_MESSAGE) REFERENCES MESSAGE(ID_MESSAGE));CREATE TABLE CONTIENT( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_MESSAGE INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_MESSAGE), FOREIGN KEY (ID_MESSAGE) REFERENCES MESSAGE(ID_MESSAGE), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION));CREATE TABLE CREER( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_UTILISATEUR INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR));CREATE TABLE DESTINE( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_UTILISATEUR INT NOT NULL, ID_GROUPE INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR), FOREIGN KEY (ID_GROUPE) REFERENCES GROUPE(ID_GROUPE));";

			String[] reqs = req.split(";");

			for (String r : reqs) {
				int res = s.executeUpdate(r + ";");
				//System.out.println(r + ": " + res);
			}

			addGroupBD(new Groupe("Tous les utilisateurs"));

			connexion.close();

		} catch (Exception e) {
			e.printStackTrace();

			throw new DataBaseException();
		}
	}

	public Utilisateur UtilisateurFromID(int idUtilisateur) {

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

		Groupe g = null;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			Statement statement = connexion.createStatement();
			ResultSet groupeBD = statement.executeQuery("SELECT * FROM GROUPE WHERE ID_GROUPE = " + idGroup + ")");

			String nomGroupe = groupeBD.getString("NOM_GROUPE");

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
			e.printStackTrace();

		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
				} catch (Exception e) {
					e.printStackTrace();
				}

			return g;
		}
	}

	public Utilisateur login(String login, String string) {
		String logintomatch = null;
		String motdepasstomatch = null;
		Utilisateur u = null;
		int idUser = 0;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			Statement statement = connexion.createStatement();

			String req = "SELECT IDENTIFIANT,MOT_DE_PASSE,ID_UTILISATEUR FROM UTILISATEUR WHERE IDENTIFIANT='" + login
					+ "';";
			// System.out.println(req);
			ResultSet resultat1 = statement.executeQuery(req);
			if (resultat1.next()) {
				logintomatch = resultat1.getString("IDENTIFIANT");
				motdepasstomatch = resultat1.getString("MOT_DE_PASSE");
				idUser = resultat1.getInt("ID_UTILISATEUR");

				// System.out.println("IDENTIFIANT:" + logintomatch + ";MOT_DE_PASSE:" +
				// motdepasstomatch + ";ID_UTILISATEUR:" + idUser);
			}
			if (login.equals(logintomatch) && string.equals(motdepasstomatch) && !string.equals("")) {
				u = UtilisateurFromID(idUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
				}
		}

		return u;
	}

	public void removeUserBD(Utilisateur u) throws DataBaseException {
		int idUser = u.getIdUser();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			connexion.setAutoCommit(false);
			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE
			String req = "DELETE FROM APPARTENIR WHERE ID_UTILISATEUR = " + idUser;
			statement.executeUpdate(req);

			req = "DELETE FROM CREER WHERE ID_UTILISATEUR = " + idUser + ";";
			statement.executeUpdate(req);

			req = "DELETE FROM DESTINE WHERE ID_UTILISATEUR = " + idUser + ";";
			statement.executeUpdate(req);

			req = "DELETE FROM ENVOYER_MESSAGE WHERE ID_UTILISATEUR = " + idUser + ";";
			statement.executeUpdate(req);

			req = "DELETE FROM UTILISATEUR WHERE ID_UTILISATEUR = " + idUser + ";";
			statement.executeUpdate(req);

			connexion.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (connexion != null) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
					 */
				}
		}
	}

	public List<Message> getAllMessages() {
		List<Message> messages = new ArrayList<Message>();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			ResultSet messagesQuery = statement.executeQuery(
					"SELECT M.ID_MESSAGE,M.CONTENU_MESSAGE,U.ID_UTILISATEUR,U.NOM_UTILISATEUR,U.PRENOM_UTILISATEUR,U.TYPE_UTILISATEUR,E.DATE_ENVOI_MESSAGE,F.ID_FIL_DE_DISCUSSION FROM CONTIENT AS C, FIL_DE_DISCUSSION AS F,MESSAGE AS M,UTILISATEUR AS U,ENVOYER_MESSAGE AS E WHERE E.ID_MESSAGE=M.ID_MESSAGE AND E.ID_UTILISATEUR=U.ID_UTILISATEUR AND F.ID_FIL_DE_DISCUSSION=C.ID_FIL_DE_DISCUSSION AND C.ID_MESSAGE=M.ID_MESSAGE ");

			while (messagesQuery.next()) {
				// System.out.println("alo");
				try {
					int idMessage = messagesQuery.getInt("ID_MESSAGE");
					int idFil = messagesQuery.getInt("ID_FIL_DE_DISCUSSION");
					String contenu = messagesQuery.getString("CONTENU_MESSAGE");
					String nomU = messagesQuery.getString("NOM_UTILISATEUR");
					String prenomU = messagesQuery.getString("PRENOM_UTILISATEUR");
					String type = messagesQuery.getString("TYPE_UTILISATEUR");
					Date date = messagesQuery.getTimestamp("DATE_ENVOI_MESSAGE");
					// System.out.println("DATE : " + date);
					Utilisateur u = null;
					switch (type) {
					case "ETUDIANT":
						u = new Etudiant(nomU, prenomU);
						break;
					case "ENSEIGNANT":
						u = new Enseignant(nomU, prenomU);
						break;
					case "ADMINISTRATIF":
						u = new Agent(nomU, prenomU, TypeUtilisateur.ADMINISTRATIF);
						break;
					case "TECHNIQUE":
						u = new Agent(nomU, prenomU, TypeUtilisateur.TECHNIQUE);
						break;

					default:
						break;
					}
					Message m = new Message(u, contenu, idMessage, idFil, date, TypeMessage.READ_BY_ALL);
					// System.out.println("UTILISATEUR" + u);
					messages.add(m);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
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

		return messages;

	}

	public List<FilDeDiscussion> getAllFilDeDiscussion() {
		List<FilDeDiscussion> fils = new ArrayList<FilDeDiscussion>();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			ResultSet messagesQuery = statement.executeQuery("SELECT ID_FIL_DE_DISCUSSION FROM FIL_DE_DISCUSSION");
			while (messagesQuery.next()) {
				// System.out.println("alo");
				try {
					try {
						int idFil = messagesQuery.getInt("ID_FIL_DE_DISCUSSION");

						FilDeDiscussion fdd = loadFil(idFil);
						System.out.println(fdd);

						fils.add(fdd);
					} catch (Exception e) {
						e.printStackTrace();
					}

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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fils;
	}

	public void updateUser(Utilisateur u) throws DataBaseException {
		int idUser = u.getIdUser();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			connexion.setAutoCommit(false);
			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE
			String req = "UPDATE UTILISATEUR SET IDENTIFIANT='" + u.getLogin() + "', MOT_DE_PASSE='" + u.getMdp()
					+ "', NOM_UTILISATEUR = '" + u.getNom() + "', PRENOM_UTILISATEUR='" + u.getPrenom()
					+ "' WHERE ID_UTILISATEUR = " + idUser + ";";
			statement.executeUpdate(req);

			connexion.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (connexion != null) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
					 */
				}
		}
	}

	public void updateGroupe(Groupe g) {
		int idGroupe = g.getIdGroupe();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			connexion.setAutoCommit(false);
			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE
			String req = "UPDATE GROUPE SET NOM_GROUPE='" + g.getNomGroupe() + "'ID_GROUPE = " + idGroupe + ";";
			statement.executeUpdate(req);

			connexion.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (connexion != null) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
					 */
				}
		}

	}

	public void addUserBD(Utilisateur u) throws DataBaseException {
		// System.out.println("AD USER TOUT COURT");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver O.K.");

			String login = u.getLogin();
			String mdpU = u.getMdp();
			String nomU = u.getNom();
			String prenomU = u.getPrenom();
			String typeU = null;

			if (u instanceof Etudiant) {
				typeU = "ETUDIANT";
			} else if (u instanceof Enseignant) {
				typeU = "ENSEIGNANT";
			} else if (u instanceof Agent) {
				Agent a = (Agent) u;
				if (a.getService().toString() == TypeUtilisateur.ADMINISTRATIF.toString()) {
					typeU = "ADMINISTRATIF";
				} else {
					typeU = "TECHNIQUE";
				}
			}

			Connection connexion = null;
			try {
				connexion = DriverManager.getConnection(url, username, mdp);
				connexion.setAutoCommit(false);
				String req = "INSERT INTO Utilisateur (Identifiant,Mot_De_Passe,Nom_Utilisateur,Prenom_Utilisateur,Type_Utilisateur) VALUES ('"
						+ login + "','" + mdpU + "','" + nomU + "','" + prenomU + "','" + typeU + "');";
				// System.out.println(req);
				/* Ici, nous placerons nos requï¿½tes vers la BDD */
				Statement statement = connexion.createStatement();

				int statut = statement.executeUpdate(req);
				if (statut == 0) {
					throw new DataBaseException("Error insert utilisateur");
				}

				ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

				if (indicedanslabasededonnee.next()) {
					int res = indicedanslabasededonnee.getInt("ID");
					u.setIdUser(res);
					// System.out.println(res);
					// addUserToGroup(u.getIdUser(), 1);
				}

				connexion.commit();

			} catch (SQLException e) {
				if (connexion != null) {
					connexion.rollback();
				}
				e.printStackTrace();
				throw new DataBaseException();
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

	public void removeGroupBD(int idGroup) throws DataBaseException {
		try {
			connexion = DriverManager.getConnection(url, username, mdp);
			connexion.setAutoCommit(false);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();
			Statement statement2 = connexion.createStatement();
			Statement statement3 = connexion.createStatement();

			int i;

			// RECUPERATION DES FILS DE DISCUSSIONS D'UN GROUPE AFIN DE LES SUPPRIMER
			String req = "SELECT ID_FIL_DE_DISCUSSION FROM DESTINE WHERE ID_GROUPE =" + idGroup + ";";
			ResultSet resultat = statement.executeQuery(req);

			while (resultat.next()) {
				int idFil = resultat.getInt("ID_FIL_DE_DISCUSSION");
				// DELETE LES FDD DU GROUPE CIBLE

				req = "SELECT ID_MESSAGE FROM CONTIENT WHERE ID_FIL_DE_DISCUSSION=" + idFil;

				ResultSet idsMessage = statement2.executeQuery(req);

				while (idsMessage.next()) {
					int idMessage = idsMessage.getInt("ID_MESSAGE");
					req = "DELETE FROM ENVOYER_MESSAGE WHERE ID_MESSAGE=" + idMessage;
					i = statement3.executeUpdate(req);
					//System.out.println(req + ":" + i);

					req = "DELETE FROM CONTIENT WHERE ID_MESSAGE=" + idMessage;
					i = statement3.executeUpdate(req);
					//System.out.println(req + ":" + i);

					req = "DELETE FROM MESSAGE WHERE ID_MESSAGE=" + idMessage;
					i = statement3.executeUpdate(req);
					//System.out.println(req + ":" + i);
				}

				req = "DELETE FROM CREER WHERE ID_FIL_DE_DISCUSSION=" + idFil;
				i = statement2.executeUpdate(req);
				//System.out.println(req + ":" + i);

				req = "DELETE FROM FIL_DE_DISCUSSION WHERE ID_FIL_DE_DISCUSSION=" + idFil + ";";
				i = statement2.executeUpdate(req);
				//System.out.println(req + ":" + i);
			}

			// DELETE LE GROUPE DE LA TABLE DESTINE
			req = "DELETE FROM DESTINE WHERE ID_GROUPE=" + idGroup + ";";
			i = statement.executeUpdate(req);

			//System.out.println(req + ":" + i);

			// DELETE LE GROUPE DE LA TABLE GROUPE
			req = "DELETE FROM GROUPE WHERE ID_Groupe =" + idGroup + ";";
			i = statement.executeUpdate(req);

			//System.out.println(req + ":" + i);
			connexion.commit();
		} catch (SQLException e) {
			if (connexion != null) {
				try {
					e.printStackTrace();
					connexion.rollback();
					throw new DataBaseException();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (connexion != null)
				try {
					connexion.close();

				} catch (SQLException ignore) {

				}
		}
	}

	public void addGroupBD(Groupe g) throws DataBaseException {
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			statement.executeUpdate("INSERT INTO Groupe (NOM_GROUPE) VALUES ('" + g.getNomGroupe() + "');");
			ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

			if (indicedanslabasededonnee.next()) {
				g.setIdGroupe(indicedanslabasededonnee.getInt("ID"));
			}

		} catch (SQLException e) {
			if (connexion != null) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new DataBaseException();
			}
			e.printStackTrace();
		} finally {
			if (connexion != null)
				try {
					connexion.close();

				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
					 */
				}
		}

	}

	public void addUserToGroup(int idUser, int idGroup) throws DataBaseException {
		// System.out.println("ADD USER TO GROUP\n");
		try {
			connexion = DriverManager.getConnection(url, username, mdp);
			Statement statement = connexion.createStatement();
			String req = "INSERT INTO APPARTENIR (ID_Utilisateur,ID_Groupe) VALUES (" + idUser + "," + idGroup + ");";
			// System.out.println(req);
			statement.executeUpdate(req);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
				}
		}
	}

	public void removeUserInGroup(int idUser, int idGroup) throws DataBaseException {
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			statement.executeUpdate(
					"DELETE FROM APPARTENIR WHERE (ID_Utilisateur =" + idUser + " and  ID_GROUPE =" + idGroup + ");");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
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

	public List<Groupe> getAllGroupsBD() throws DataBaseException {
		List<Groupe> listeGroupe = new ArrayList<Groupe>();
		String nomgroupe;
		int idgroupe;

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
			e.printStackTrace();
			throw new DataBaseException();
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

	public List<Utilisateur> getAllUsers() throws DataBaseException {

		// System.out.println("get all users");
		List<Utilisateur> listeUser = new ArrayList<Utilisateur>();
		String nom;
		String prenom;
		int IdUser;
		String login;
		String mdpU;
		String typeutilisateur;
		Service service;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			ResultSet resultat = statement.executeQuery("SELECT * FROM UTILISATEUR");

			Utilisateur u = null;

			while (resultat.next()) {
				// System.out.println("alo");
				try {
					nom = resultat.getString("Nom_Utilisateur");
					prenom = resultat.getString("Prenom_Utilisateur");
					IdUser = resultat.getInt("ID_Utilisateur");
					login = resultat.getString("Identifiant");
					mdpU = resultat.getString("Mot_De_Passe");
					typeutilisateur = resultat.getString("Type_Utilisateur");

					if (typeutilisateur == TypeUtilisateur.ETUDIANT.toString()) {
						u = new Etudiant(nom, prenom, mdpU, login, IdUser);
					} else if (typeutilisateur == TypeUtilisateur.ENSEIGNANT.toString()) {
						u = new Enseignant(nom, prenom, mdpU, login, IdUser);
					} else {
						u = new Agent(nom, prenom, mdpU, login, IdUser, TypeUtilisateur.ADMINISTRATIF);
					}
					listeUser.add(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}

		for (Utilisateur utilisateur : listeUser) {
			// System.out.println(utilisateur);
		}

		return listeUser;
	}

	public List<Utilisateur> getUsersFromGroup(int idGroup) throws DataBaseException {
		// System.out.println("GET USERS FROM GROUP");
		List<Utilisateur> listeUser = new ArrayList<Utilisateur>();
		String nom;
		String prenom;
		int IdUser;
		String login;
		String mdpU;
		String typeutilisateur;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			ResultSet resultat = statement.executeQuery(
					"SELECT * FROM utilisateur as U WHERE U.ID_UTILISATEUR IN (SELECT ID_UTILISATEUR FROM appartenir WHERE ID_GROUPE ="
							+ idGroup + ");");

			while (resultat.next()) {
				try {
					nom = resultat.getString("Nom_Utilisateur");
					prenom = resultat.getString("Prenom_Utilisateur");
					IdUser = resultat.getInt("ID_Utilisateur");
					login = resultat.getString("Identifiant");
					mdpU = resultat.getString("Mot_De_Passe");
					typeutilisateur = resultat.getString("Type_Utilisateur");

					Utilisateur u = null;
					if (typeutilisateur.equals(TypeUtilisateur.ETUDIANT.toString())) {
						u = new Etudiant(nom, prenom, mdpU, login, IdUser);
					} else if (typeutilisateur.equals(TypeUtilisateur.ENSEIGNANT.toString())) {
						u = new Enseignant(nom, prenom, mdpU, login, IdUser);
					} else if (typeutilisateur.equals(TypeUtilisateur.ADMINISTRATIF.toString())) {
						u = new Agent(nom, prenom, mdpU, login, IdUser, TypeUtilisateur.ADMINISTRATIF);
					} else if (typeutilisateur.equals(TypeUtilisateur.TECHNIQUE.toString())) {
						u = new Agent(nom, prenom, mdpU, login, IdUser, TypeUtilisateur.TECHNIQUE);
					}

					listeUser.add(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new DataBaseException();

		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}

		for (Utilisateur utilisateur : listeUser) {
			// System.out.println(utilisateur);
		}

		return listeUser;
	}

	public void addFilDeDiscussion(FilDeDiscussion f) throws DataBaseException {
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requêtes vers la BDD */
			Statement statement = connexion.createStatement();

			int statut = statement.executeUpdate(
					"INSERT INTO Fil_De_Discussion (TITRE_FIL_DE_DISCUSSION) VALUES ('" + f.getTitre() + "');");

			ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

			while (indicedanslabasededonnee.next()) {
				int idFilDediscussion = indicedanslabasededonnee.getInt("ID");
				f.setIdFil(idFilDediscussion);
				// System.out.println(idFilDediscussion);
			}

			int a = statement.executeUpdate("INSERT INTO Creer (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR) VALUES ("
					+ f.getIdFil() + "," + f.getCreateur().getIdUser() + ");");
			if (a == 0) {
				throw new DataBaseException("Error insert CREER");
			} else {
				int b = statement.executeUpdate(
						"INSERT INTO destine (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE) VALUES (" + f.getIdFil()
								+ "," + f.getCreateur().getIdUser() + "," + f.getGroupe().getIdGroupe() + ");");
				if (b == 0) {
					throw new DataBaseException("Error insert CREER");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
		} finally {
			if (connexion != null)
				try {
					connexion.close();
				} catch (SQLException ignore) {
				}
		}
	}

	public void addMessageToFil(int idFil, Message msg) throws DataBaseException {
		// System.out.println("MESSAGE : +" + msg.getMsg());
		int idmessage = 0;

		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requêtes vers la BDD */
			Statement statement = connexion.createStatement();

			statement.executeUpdate("INSERT INTO MESSAGE (CONTENU_MESSAGE) VALUES ('" + msg.getMsg() + "');");

			ResultSet indicedanslabasededonnee = statement.executeQuery("SELECT LAST_INSERT_ID() AS ID;");

			if (indicedanslabasededonnee.next()) {
				idmessage = indicedanslabasededonnee.getInt("ID");
				msg.setIdMsg(idmessage);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date now = new Date();
			try {
				// System.out.println("Wait " + msg.getIdMsg());
				// System.out.println(msg.getAuteur());
				// System.out.println("VOILA :" + msg.getAuteur().getIdUser());
				// System.out.println(msg.getIdMsg());
				// System.out.println(dateFormat.format(now));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String req = "INSERT INTO envoyer_message (ID_UTILISATEUR,ID_MESSAGE,DATE_ENVOI_MESSAGE) VALUES ("
					+ msg.getAuteur().getIdUser() + "," + idmessage + ",'" + dateFormat.format(now) + "');";
			int b = statement.executeUpdate(req);

			if (b == 0) {
				throw new DataBaseException("Error while adding message.");
			} else {

				req = "INSERT INTO CONTIENT (ID_FIL_DE_DISCUSSION,ID_MESSAGE) VALUES (" + idFil + "," + idmessage
						+ ");";

				int statut = statement.executeUpdate(req);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Unknow Error DATABASE");
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

	public void removeFilBD(int idFil) throws DataBaseException {
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE
			statement.executeUpdate(
					"DELETE ID_FIL_DE_DISCUSSION FROM DESTINE (ID_FIL_DE_DISCUSSION,ID_Utilisateur,ID_Groupe) WHERE (ID_FIL_DE_DISCUSSION ='"
							+ idFil + "';");

			// REMOVAL DE LA TABLE FIL DE DISCUSSION
			statement.executeUpdate(
					"DELETE ID_FIL_DE_DISCUSSION FROM FILDEDISCUSSION (ID_FIL_DE_DISCUSSION,TITRE) WHERE (ID_FIL_DE_DISCUSSION ='"
							+ idFil + "';");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
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

	public List<Message> messagesFromFil(int idFil) throws DataBaseException {
		// System.out.println("MESSAGES FROM FIL");
		List<Message> messages = new ArrayList<>();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE

			String req = "SELECT E.DATE_ENVOI_MESSAGE,M.ID_MESSAGE,M.CONTENU_MESSAGE,U.NOM_UTILISATEUR,U.PRENOM_UTILISATEUR,U.TYPE_UTILISATEUR FROM MESSAGE AS M, utilisateur AS U,envoyer_message AS E WHERE E.ID_MESSAGE=M.ID_MESSAGE AND E.ID_UTILISATEUR=U.ID_UTILISATEUR AND M.ID_MESSAGE IN (SELECT C.ID_MESSAGE FROM CONTIENT AS C WHERE C.ID_FIL_DE_DISCUSSION ="
					+ idFil + ")";

			// System.out.println(req);
			// System.out.println("AVANT");
			ResultSet messagesQuery = statement.executeQuery(req);
			// System.out.println("APRTES");

			while (messagesQuery.next()) {
				// System.out.println("NEXT MESSAGE");
				try {
					int idMessage = messagesQuery.getInt("ID_MESSAGE");
					String contenu = messagesQuery.getString("CONTENU_MESSAGE");
					String nomU = messagesQuery.getString("NOM_UTILISATEUR");
					String prenomU = messagesQuery.getString("PRENOM_UTILISATEUR");
					String type = messagesQuery.getString("TYPE_UTILISATEUR");
					Date date = messagesQuery.getTimestamp("DATE_ENVOI_MESSAGE");
					// System.out.println("DATE : " + date);
					Utilisateur u = null;
					switch (type) {
					case "ETUDIANT":
						u = new Etudiant(nomU, prenomU);
						break;
					case "ENSEIGNANT":
						u = new Enseignant(nomU, prenomU);
						break;
					case "ADMINISTRATIF":
						u = new Agent(nomU, prenomU, TypeUtilisateur.ADMINISTRATIF);
						break;
					case "TECHNIQUE":
						u = new Agent(nomU, prenomU, TypeUtilisateur.TECHNIQUE);
						break;

					default:
						break;
					}
					Message m = new Message(u, contenu, idMessage, date, TypeMessage.READ_BY_ALL);
					// System.out.println("UTILISATEUR" + u);
					messages.add(m);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
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

		return messages;
	}

	public FilDeDiscussion loadFil(int idFil) throws DataBaseException {
		FilDeDiscussion f = null;
		List<Message> messages = null;
		// System.out.println("LOAD FIL DEBUT");
		try {
			messages = messagesFromFil(idFil);
		} catch (Exception e) {
			// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
		// System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		try {
			connexion = DriverManager.getConnection(url, username, mdp);
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE
			ResultSet fil = statement.executeQuery(
					"SELECT F.TITRE_FIL_DE_DISCUSSION,G.ID_GROUPE,G.NOM_GROUPE,C.ID_UTILISATEUR FROM fil_de_discussion AS F,destine AS D,groupe AS G,creer AS C WHERE F.ID_FIL_DE_DISCUSSION = C.ID_FIL_DE_DISCUSSION AND F.ID_FIL_DE_DISCUSSION = D.ID_FIL_DE_DISCUSSION AND G.ID_GROUPE = D.ID_GROUPE AND F.ID_FIL_DE_DISCUSSION = "
							+ idFil);
			if (fil.next()) {
				String titreFil = fil.getString("TITRE_FIL_DE_DISCUSSION");
				String nomGroupe = fil.getString("NOM_GROUPE");
				int idGroupe = fil.getInt("ID_GROUPE");
				int idUser = fil.getInt("ID_UTILISATEUR");

				Groupe g = new Groupe(nomGroupe, idGroupe, getUsersFromGroup(idGroupe));

				f = new FilDeDiscussion(titreFil, g, UtilisateurFromID(idUser), messages, idFil);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
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
		// System.out.println("LOAD FIL FINNNNNNNNNNNNNNNN");
		return f;
	}

	public List<FilDeDiscussion> filsFromIdUser(int idUser) throws DataBaseException {
		List<FilDeDiscussion> fils = new ArrayList<>();
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requï¿½tes vers la BDD */
			Statement statement = connexion.createStatement();

			// DISPARITION DE L'UTILISATEUR DE TOUT LES GROUPES DONT IL ETAIT MEMBRE

			String req = "SELECT * FROM fil_de_discussion AS F WHERE F.ID_FIL_DE_DISCUSSION IN ( SELECT D.ID_FIL_DE_DISCUSSION FROM destine AS D WHERE D.ID_GROUPE IN (SELECT A.ID_GROUPE FROM appartenir AS A WHERE A.ID_UTILISATEUR ="
					+ idUser
					+ ")) UNION SELECT * FROM fil_de_discussion AS F2 WHERE F2.ID_FIL_DE_DISCUSSION IN ( SELECT D2.ID_Fil_DE_DISCUSSION FROM destine AS D2 WHERE D2.ID_UTILISATEUR = "
					+ idUser + ")";

			// System.out.println(req);
			ResultSet filsGroupes = statement.executeQuery(req);

			while (filsGroupes.next()) {
				int idFil = filsGroupes.getInt("ID_FIL_DE_DISCUSSION");
				FilDeDiscussion f = (loadFil(idFil));
				// System.out.println(f.getTitre());

				fils.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException();
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

		return fils;
	}

	public void changeUser(Utilisateur u) throws DataBaseException {
		try {
			String req = "UPDATE UTILISATEUR WHERE ID_UTILISATEUR =" + u.getIdUser() + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test() {

		/*
		 * try { creation_bd(); } catch (DataBaseException e1) { e1.printStackTrace(); }
		 */

		try {
			Etudiant e = new Etudiant("Fablyat", "Mofolyat", "a", "a");
			addUserBD(e);
			addUserToGroup(e.getIdUser(), 1);

			Etudiant e2 = new Etudiant("Qiu", "Jr", "b", "b");
			addUserBD(e2);
			addUserToGroup(e2.getIdUser(), 1);

			Etudiant e3 = new Etudiant("Suchiz", "Kyuu", "C", "C");
			addUserBD(e3);
			addUserToGroup(e3.getIdUser(), 1);

			Etudiant e4 = new Etudiant("Ruben", "Le connard", "d", "d");
			addUserBD(e4);
			addUserToGroup(e4.getIdUser(), 1);

			Groupe g = new Groupe("TDA1");
			addGroupBD(g);

			Groupe g2 = new Groupe("TDA2");
			addGroupBD(g2);

			g.addMember(e);
			g.addMember(e2);
			g.addMember(e3);
			g.addMember(e4);

			g2.addMember(e);
			g2.addMember(e4);
			g2.addMember(e3);

			FilDeDiscussion f = new FilDeDiscussion("BONJOUR YA UN PB ICI", g, e);
			addFilDeDiscussion(f);

			for (int i = 0; i < 5; i++) {
				// System.out.println("i : " + i);
				Message m = new Message(e, "VOILA LE MESSAGE nï¿½ " + i);
				f.addMessage(m);
			}

			FilDeDiscussion f2 = new FilDeDiscussion("LA AUSSI YA UN PB", g2, e2);
			addFilDeDiscussion(f2);

			for (int i = 0; i < 5; i++) {
				// System.out.println("i : " + i);
				Message m = new Message(e2, "VOILA LE MESSAGE nï¿½ " + i + " MAIS C''EST PAS LE MEME FIL DAKOR");
				f2.addMessage(m);
			}

			// System.out.println("oui");

			List<FilDeDiscussion> ff = filsFromIdUser(e.getIdUser());

			for (FilDeDiscussion filDeDiscussion : ff) {
				// System.out.println( "FIL DE DISCUSSION : " + filDeDiscussion.getTitre() + ";"
				// + filDeDiscussion.getIdFil());

				for (Message message : filDeDiscussion.getConversation()) {
					// System.out.println(message.getMsg() + ";" + message.getAuteur() + ";" +
					// message.getDate());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
