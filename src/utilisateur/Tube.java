package utilisateur;

import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ihm.FrameInterface;

public class Tube implements Runnable {
	private Socket socket;
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;
	private FrameInterface frameInterface;
	private GererMessage gestionMessage = new GererMessage(this);

	public Tube(FrameInterface frameInterface, Socket socket) throws IOException {
		this.frameInterface = frameInterface;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			listening();
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Deconnecte !");
			frameInterface.getMenuBarInterface().setDisconnected();
		}
	}

	private void listening() throws ClassNotFoundException, IOException {
		while (true) {
			receive();
		}
	}

	public void receive() throws ClassNotFoundException, IOException {
		inputFromServer = new ObjectInputStream(socket.getInputStream());
		Object temp = inputFromServer.readObject();
		if (temp != null) {
			if (temp instanceof Message) {
				Message message = (Message) temp;
				gestionMessage.message(message);
			} else if (temp instanceof Utilisateur) {
				Utilisateur u = (Utilisateur) temp;
				gestionMessage.utilisateur(u);
			}else if (temp instanceof List<?>) {
				System.out.println();temp.getClass();
				//gestionMessage.utilisateur(fdd);
			}
		}
	}

	public void send(Object object) throws IOException {
		outputToServer = new ObjectOutputStream(socket.getOutputStream());
		outputToServer.writeObject(object);
		outputToServer.flush();
		
	}
	
	public void disconnect() throws IOException{
		if (outputToServer != null)
			outputToServer.flush();
		socket.close();
	}
}
