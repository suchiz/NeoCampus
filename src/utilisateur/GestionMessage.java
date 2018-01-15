package utilisateur;

import java.util.ArrayList;
import java.util.List;

import classes.FilDeDiscussion;
import classes.Groupe;
import classes.Message;
import classes.TypeMessage;
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
		System.out.println(message.getType()+ " " + message.getIdMsg());
		switch (message.getType()) {
		case RECEIVED:
			gererReceived(message);
			break;
		case READ:
			gererRead(message);
			break;
		case READ_BY_ALL:
			gererReadForAll(message);
			break;
		case MESSAGE:
			gererMessage(message);
			break;

		default:
			break;
		}

	}

	private void gererReadForAll(Message message) {
		int idFilRef = message.getIdFil();
		int idMessageRef = message.getIdMsg();

		for (FilDeDiscussion fdd : frameInterface.getTousLesFils()) {
			if (idFilRef == fdd.getIdFil()) {
				for (Message m : fdd.getConversation()) {

					if (idMessageRef == m.getIdMsg()) {
						m.setType(TypeMessage.READ_BY_ALL);
						frameInterface.getPanelMsg().displayMessage(fdd);
						break;
					}
				}
				break;
			}
		}

	}

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

	private void gererMessage(Message message) {
		System.out.println(message.getMsg());
		PanelMessageDisplay panelMessageDisplay = frameInterface.getPanelMsg();
		PanelFilDeDiscussion panelFilDeDiscussion = frameInterface.getPanelFil();
		int idFilRef = message.getIdFil();
		for (FilDeDiscussion fdd : frameInterface.getTousLesFils()) {
			if (idFilRef == fdd.getIdFil()) {
				fdd.getConversation().add(message);
				panelMessageDisplay.displayMessage(fdd);
				fdd.increment();
				panelFilDeDiscussion.reloadTree();
				break;
			}
		}

	}

	public void liste(List<?> list) {
		if (!list.isEmpty()) {
			Object elem = list.get(0);
			if (elem instanceof Groupe) {
				List<Groupe> lg = (ArrayList<Groupe>) list;
				frameInterface.setTousLesGroupes(lg);

			} else if (elem instanceof FilDeDiscussion) {
				List<FilDeDiscussion> lfdd = (ArrayList<FilDeDiscussion>) list;
				frameInterface.initTousLesFils((ArrayList<FilDeDiscussion>) list);
			} else {
				List<Utilisateur> lu = (List<Utilisateur>) list;
				frameInterface.getPanelMsg().getFrameAfficherUtilisateur().setListUserRef(lu);
			}
		}
	}

	public void fildediscussion(FilDeDiscussion fdd) {
		frameInterface.getTousLesFils().add(fdd);
		frameInterface.getPanelFil().ajouterFilDeDisussion(fdd);
	}

}
