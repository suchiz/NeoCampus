package utilisateur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.Utilisateur;
import ihm.FrameInterface;
import ihm.PanelLogin;
import utilisateur.Tube;

public class Connexion implements Runnable {
	private Socket socket;
	private PanelLogin panelLogin;
	private FrameInterface frameInterface;
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;

	public Connexion(FrameInterface frameInterface, PanelLogin panelLogin, Socket socket) {
		this.frameInterface = frameInterface;
		this.socket = socket;
		this.panelLogin = panelLogin;
	}

	@Override
	public void run() {
		send(panelLogin.createRequestLogin());
		receive();
	}

	public void send(Object object) {
		try {
			outputToServer = new ObjectOutputStream(socket.getOutputStream());
			outputToServer.writeObject(object);
			outputToServer.flush();
		} catch (IOException e) {
			System.out.println("Error sending message from server to client");
		}
	}

	public void receive() {
		try {
			inputFromServer = new ObjectInputStream(socket.getInputStream());
			Object temp = null;

			temp = inputFromServer.readObject();

			if (temp != null) {
				if (temp instanceof Utilisateur) {
					Utilisateur u = (Utilisateur) temp;
					frameInterface.setUser(u);
					frameInterface.getMenuBarInterface().setConnected();
					Tube tube = new Tube(frameInterface, socket);
					frameInterface.setTube(tube);
					System.out.println("Connecte !");
					Thread t = new Thread(tube);
					frameInterface.setTitle(u.toString());
					panelLogin.closeWindow(panelLogin.getAe());
					t.start();

				}
			} else {

				panelLogin.errorMessage();
			}
		} catch (IOException e) {
			System.out.println("Erreur reception login");
		} catch (ClassNotFoundException e) {
			System.err.println("Erreur de classe dans authentification");
			e.printStackTrace();
		}
	}

}
