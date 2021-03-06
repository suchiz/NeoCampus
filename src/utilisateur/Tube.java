package utilisateur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.FilDeDiscussion;
import classes.Message;
import classes.TypeMessage;
import ihm.FrameInterface;

public class Tube implements Runnable {
	private Socket socket;
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;
	private FrameInterface frameInterface;
	private GestionMessage gestionMessage;

	public Tube(FrameInterface frameInterface, Socket socket) throws IOException {
		this.frameInterface = frameInterface;
		this.socket = socket;
		gestionMessage = new GestionMessage(frameInterface, this);
	}

	@Override
	public void run() {
		try {

			send(new Message("", TypeMessage.REQUETE_INIT_GROUP));
			Integer temp = frameInterface.getUser().getIdUser();
			send(new Message(temp.toString(), TypeMessage.REQUETE_INIT_FDD));
			listening();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Deconnecte !");
			frameInterface.getMenuBarInterface().setDisconnected();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
	}

	private void listening() throws ClassNotFoundException, IOException {
		while (true) {
			receive();
		}
	}

	public void receive() throws ClassNotFoundException, IOException {

		inputFromServer = new ObjectInputStream(socket.getInputStream());
		System.out.println("Receive in client");
		Object temp = inputFromServer.readObject();
		System.out.println(temp.getClass());
		if (temp != null) {
			if (temp instanceof Message) {
				Message message = (Message) temp;
				gestionMessage.message(message);

			} else if (temp instanceof FilDeDiscussion) {
				FilDeDiscussion fdd = (FilDeDiscussion) temp;
				gestionMessage.fildediscussion(fdd);
			} else if (temp instanceof ArrayList<?>) {
				List<?> list = (ArrayList<?>) temp;
				gestionMessage.liste(list);
			} 
		}
	}

	public void send(Object object) {
		System.out.println("sending");
		try {
			outputToServer = new ObjectOutputStream(socket.getOutputStream());
			outputToServer.writeObject(object);
			outputToServer.flush();
		} catch (IOException e) {
			System.out.println("Erreur sending message in client");
		}
		

	}

	public void disconnect() throws IOException {
		if (outputToServer != null)
			outputToServer.flush();
		socket.close();
	}
}
