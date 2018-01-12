package utilisateur;

import java.util.ArrayList;
import java.util.List;

import classes.FilDeDiscussion;
import classes.Groupe;
import classes.Message;
import classes.Utilisateur;
import ihm.FrameInterface;

public class GestionMessage {
	private Tube tube;
	FrameInterface frameInterface;
	
	public GestionMessage(FrameInterface frameInterface, Tube tube) {
		this.tube = tube;
		this.frameInterface = frameInterface;
	}
	
	public void message(Message message) {
		// TODO Auto-generated method stub
		
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
