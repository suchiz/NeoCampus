package utilisateur;

import java.util.ArrayList;
import java.util.List;

import classes.FilDeDiscussion;
import classes.Groupe;
import classes.Message;
import classes.Utilisateur;
import ihm.FrameInterface;
import ihm.PanelFilDeDiscussion;
import ihm.PanelMessageDisplay;

public class GestionMessage {
	private Tube tube;
	FrameInterface frameInterface;
	
	
	public GestionMessage(FrameInterface frameInterface, Tube tube) {
		this.tube = tube;
		this.frameInterface = frameInterface;
	}

	public void message(Message message) {
		switch (message.getType()) {
		case ACK_MESSAGE:

			break;
<<<<<<< HEAD
<<<<<<< HEAD
		case READ:
			gererRead(message);
=======
		case READ_BY_ALL:

>>>>>>> parent of 30f3f30... 4 yeux
=======
		case READ_BY_ALL:

>>>>>>> parent of 30f3f30... 4 yeux
			break;
		case MESSAGE:
			gererMessage(message);
			break;

		default:
			break;
		}

	}

<<<<<<< HEAD
<<<<<<< HEAD
	private void gererRead(Message message) {
	
		int idFilRef = message.getIdFil();
		int idMessageRef = message.getIdMsg();
		
		for (FilDeDiscussion fdd : frameInterface.getTousLesFils()) {
			if (idFilRef == fdd.getIdFil()) {
				for (Message m : fdd.getConversation()) {
					
					if (idMessageRef == m.getIdMsg()) {
						m.setType(TypeMessage.READ);
						frameInterface.getPanelMsg().displayMessage(fdd);
						break;
					}
				}
				break;
			}
		}

	}

	private void gererReceived(Message message) {
		int idFilRef = message.getIdFil();
		int idMessageRef = message.getIdMsg();
		if (message.getAuteur().getIdUser() == frameInterface.getUser().getIdUser()) {
			for (FilDeDiscussion fdd : frameInterface.getTousLesFils()) {
				if (idFilRef == fdd.getIdFil()) {
					for (Message m : fdd.getConversation()) {
						if (idMessageRef == m.getIdMsg()) {
							m.setType(TypeMessage.RECEIVED);
							frameInterface.getPanelMsg().displayMessage(fdd);
							break;
						}
					}
					break;
				}
			}
		}
	}

=======
>>>>>>> parent of 30f3f30... 4 yeux
=======
>>>>>>> parent of 30f3f30... 4 yeux
	private void gererMessage(Message message) {
		System.out.println(message.getMsg());
		PanelMessageDisplay panelMessageDisplay = frameInterface.getPanelMsg();
		int idFilRef = message.getIdFil();
		for (FilDeDiscussion fdd : frameInterface.getTousLesFils()) {
			if (idFilRef == fdd.getIdFil()) {
				fdd.getConversation().add(message);
				panelMessageDisplay.displayMessage(fdd);
				break;
			}
		}
		
		
	}

	public void utilisateur(Utilisateur u) {
		// TODO Auto-generated method stub

	}

	public void liste(List<?> list) {
		Object elem = list.get(0);
		if (elem instanceof Groupe) {
			List<Groupe> lg = (ArrayList<Groupe>) list;
			frameInterface.setTousLesGroupes(lg);

		} else {
			List<FilDeDiscussion> lfdd = (ArrayList<FilDeDiscussion>) list;
			frameInterface.initTousLesFils((ArrayList<FilDeDiscussion>) list);
		}

	}

}
