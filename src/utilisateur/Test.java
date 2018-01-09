package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		DB d = new DB();

		d.creation_bd();

		try {
			Etudiant e = new Etudiant("Fablyat", "Mofolyat", "a", "a");
			d.addUserBD(e);

			Etudiant e2 = new Etudiant("Qiu", "Jr", "b", "b");
			d.addUserBD(e2);

			Etudiant e3 = new Etudiant("Suchiz", "Kyuu", "C", "C");
			d.addUserBD(e3);

			Etudiant e4 = new Etudiant("Ruben", "Le connard", "d", "d");
			d.addUserBD(e4);

			Groupe g = new Groupe("TDA1");
			d.addGroupBD(g);

			Groupe g2 = new Groupe("TDA2");
			d.addGroupBD(g2);

			g.addMember(e);
			g.addMember(e2);
			g.addMember(e3);
			g.addMember(e4);

			g2.addMember(e);
			g2.addMember(e4);
			g2.addMember(e3);

			FilDeDiscussion f = new FilDeDiscussion("BONJOUR YA UN PB ICI", g, e);
			d.addFilDeDiscussion(f);

			for (int i = 0; i < 5; i++) {
				System.out.println("i : " + i);
				Message m = new Message(e,"VOILA LE MESSAGE n� " + i);
				f.addMessage(m);
			}
			

			FilDeDiscussion f2 = new FilDeDiscussion("LA AUSSI YA UN PB", g2, e2);
			d.addFilDeDiscussion(f2);

			for (int i = 0; i < 5; i++) {
				System.out.println("i : " + i);
				Message m = new Message(e2,"VOILA LE MESSAGE n� " + i + " MAIS C''EST PAS LE MEME FIL DAKOR");
				f2.addMessage(m);
			}

			System.out.println("oui");
			
			List<FilDeDiscussion> ff = d.filsFromIdUser(e.getIdUser());
			
			
			for (FilDeDiscussion filDeDiscussion : ff) {
				System.out.println(filDeDiscussion.getTitre()+";");
				
				for (Message message : filDeDiscussion.getConversation()) {
					System.out.println(message.getMsg()+";"+message.getAuteur());
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
