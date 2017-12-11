package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ouilabd {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:mysql://localhost:3306/base_de_donnees_neocampus?autoReconnect=true&useSSL=false";
			String user = "root";
			String pwd = "root";

			Connection conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connexion effective !");

			Statement s = conn.createStatement();

			String req = "DROP DATABASE base_de_donnees_neocampus;CREATE DATABASE base_de_donnees_neocampus;USE base_de_donnees_neocampus;CREATE TABLE UTILISATEUR( ID_UTILISATEUR INT NOT NULL AUTO_INCREMENT, IDENTIFIANT VARCHAR(255), MOT_DE_PASSE VARCHAR(255), NOM_UTILISATEUR VARCHAR(255), PRENOM_UTILISATEUR VARCHAR(255), TYPE_UTILISATEUR ENUM('ENSEIGNANT','ETUDIANT','TECH','ADMN'), PRIMARY KEY (ID_UTILISATEUR));CREATE TABLE MESSAGE( ID_MESSAGE INT NOT NULL AUTO_INCREMENT, CONTENU_MESSAGE VARCHAR(2048), PRIMARY KEY (ID_MESSAGE));CREATE TABLE FIL_DE_DISCUSSION( ID_FIL_DE_DISCUSSION INT NOT NULL AUTO_INCREMENT, TITRE_FIL_DE_DISCUSSION VARCHAR(1000), PRIMARY KEY (ID_FIL_DE_DISCUSSION));CREATE TABLE CROUPE( ID_GROUPE INT NOT NULL AUTO_INCREMENT, NOM_GROUPE VARCHAR(100), PRIMARY KEY (ID_GROUPE));CREATE TABLE APPARTENIR( ID_GROUPE INT NOT NULL, ID_UTILISATEUR INT NOT NULL, PRIMARY KEY (ID_GROUPE,ID_UTILISATEUR), FOREIGN KEY (ID_GROUPE) REFERENCES GROUPE(ID_GROUPE), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR));CREATE TABLE ENVOYER_MESSAGE( ID_UTILISATEUR INT NOT NULL, ID_MESSAGE INT NOT NULL, DATE_ENVOI_MESSAGE DATE, PRIMARY KEY (ID_UTILISATEUR,ID_MESSAGE), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR), FOREIGN KEY (ID_MESSAGE) REFERENCES MESSAGE(ID_MESSAGE));CREATE TABLE CONTIENT( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_MESSAGE INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_MESSAGE), FOREIGN KEY (ID_MESSAGE) REFERENCES MESSAGE(ID_MESSAGE), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION));CREATE TABLE CREER( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_UTILISATEUR INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR));CREATE TABLE DESTINE( ID_FIL_DE_DISCUSSION INT NOT NULL, ID_UTILISATEUR INT NOT NULL, ID_GROUPE INT NOT NULL, PRIMARY KEY (ID_FIL_DE_DISCUSSION,ID_UTILISATEUR,ID_GROUPE), FOREIGN KEY (ID_FIL_DE_DISCUSSION) REFERENCES ID_FIL_DE_DISCUSSION(ID_FIL_DE_DISCUSSION), FOREIGN KEY (ID_UTILISATEUR) REFERENCES UTILISATEUR(ID_UTILISATEUR), FOREIGN KEY (ID_GROUPE) REFERENCES GROUPE(ID_GROUPE));";

			String[] reqs = req.split(";");

			for (String r : reqs) {
				System.out.println("Requete : " + r);
				System.out.println("VOILA : " + s.executeUpdate(r + ";"));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}