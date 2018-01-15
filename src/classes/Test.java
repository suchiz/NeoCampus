package classes;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		DB d = new DB();

		try {
			d.creation_bd();
			d.test();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Message> m = d.getAllMessages();

		for (Message message : m) {
			System.out.println(message);
		}

		List<FilDeDiscussion> fdd = d.getAllFilDeDiscussion();

		for (FilDeDiscussion filDeDiscussion : fdd) {
			System.out.println(filDeDiscussion);
		}
	}
}
