package utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
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

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			statement
					.executeUpdate("DELETE FROM APPARTENIR (ID_Utilisateur,ID_Groupe) WHERE (ID_Utilisateur ='"
							+ user.getIdUser() + "'and'" + this.idGroupe + "';");

		} catch (SQLException e) {
			/* G�rer les �ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de
					 * l'ignorer.
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
			statement
					.executeUpdate("INSERT INTO APPARTENIR (ID_Utilisateur,ID_Groupe) VALUES ('"
							+ user.getIdUser() + "','" + this.idGroupe + "');");

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
	public void stockageGrpBDD(Groupe groupe) {
		/* Connexion � la base de donn�es */
		String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
		String username = "root";
		String mdp = "root";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, username, mdp);

			/* Ici, nous placerons nos requ�tes vers la BDD */
			Statement statement = connexion.createStatement();

			statement
					.executeUpdate("INSERT INTO Groupe (ID_Groupe,NomGroupe) VALUES ('"
							+ this.idGroupe + "'," + this.idGroupe + "';");

		} catch (SQLException e) {
			/* G�rer les �ventuelles erreurs ici */
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de
					 * l'ignorer.
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
