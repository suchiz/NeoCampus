package classes;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		DB d = new DB();

		/*
		 * try { d.creation_bd(); d.test(); } catch (DataBaseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		List<Message> m = d.getAllMessages();

		for (Message message : m) {
			System.out.println(message + ";" + message.getIdMsg() + ";" + message.getIdFil());
			System.out.println(message.getAuteur() + ";" + message.getAuteur().getIdUser());

		}

		List<FilDeDiscussion> fdd = d.getAllFilDeDiscussion();

		for (FilDeDiscussion filDeDiscussion : fdd) {
			System.out.println(filDeDiscussion + ";" + filDeDiscussion.getIdFil()+";"+filDeDiscussion.getGroupe()+";"+filDeDiscussion.getGroupe().getIdGroupe());
		}
		
		Utilisateur u = d.UtilisateurFromID(1);
		System.out.println(u);
		
		u.setNom("OLALA UN NOUVEAU NOM");
		
		try {
			d.updateUser(u);
			System.out.println(u);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
	}
}
