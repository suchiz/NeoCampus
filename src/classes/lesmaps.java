package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class lesmaps {

	public static void main(String[] args) {
		Map<Integer, List<Message>> a = new HashMap<Integer, List<Message>>();
		Utilisateur u = new Etudiant("Fablyat", "Mofolyat", "a", "a", 1);
		Utilisateur u2 = new Etudiant("Fablyat", "Mofolyat", "a", "a", 1);
		Message m = new Message(u, "Bonjour", 1, new Date());
		Message m2 = new Message(u, "Bonjour54", 2, new Date());
		a.put(u.getIdUser(), new ArrayList<Message>());
		a.get(u.getIdUser()).add(m);
		a.get(u.getIdUser()).add(m2);

		System.out.println(a.get(u2.getIdUser()));

		Set<Entry<Integer, List<Message>>> s = a.entrySet();

		Iterator<Entry<Integer, List<Message>>> i = s.iterator();

		while (i.hasNext()) {
			Entry<Integer, List<Message>> e = i.next();
			System.out.println(e.getKey() + ";" + e.getValue());
		}

	}

}
