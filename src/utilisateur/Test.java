package utilisateur;


public class Test {

	public static void main(String[] args) {
		DB d = new DB();

		d.creation_bd();

		try {
			Etudiant e = new Etudiant("Fablyat", "Mofolyat", "a", "a");
			e.stockageUserBDD();

			Etudiant e2 = new Etudiant("Qiu", "Jr", "b", "b");
			e2.stockageUserBDD();

			Etudiant e3 = new Etudiant("Suchiz", "Kyuu", "C", "C");
			e3.stockageUserBDD();

			Etudiant e4 = new Etudiant("Ruben", "Le connard", "d", "d");
			e4.stockageUserBDD();

			Groupe g = new Groupe("TDA1");
			g.stockageGrpBDD();

			Groupe g2 = new Groupe("TDA2");
			g2.stockageGrpBDD();

			g.addMember(e);
			g.addMember(e2);
			g.addMember(e3);
			g.addMember(e4);

			g2.addMember(e);
			g2.addMember(e4);
			g2.addMember(e3);

			FilDeDiscussion f = new FilDeDiscussion("BONJOUR YA UN PB ICI", g, e);
			f.stockageBDD();

			for (int i = 0; i < 5; i++) {
				System.out.println("i : " + i);
				Message m = new Message(e,"VOILA LE MESSAGE n� " + i);
				f.addMessage(m);
			}

			FilDeDiscussion f2 = new FilDeDiscussion("LA AUSSI YA UN PB", g, e2);
			f2.stockageBDD();

			for (int i = 0; i < 5; i++) {
				System.out.println("i : " + i);
				Message m = new Message(e2,"VOILA LE MESSAGE n� " + i + " MAIS C''EST PAS LE MEME FIL DAKOR");
				f2.addMessage(m);
			}

			System.out.println("oui");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
