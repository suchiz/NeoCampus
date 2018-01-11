package utilisateur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ihm.FrameInterface;
import ihm.PanelLogin;

public class Authentification implements Runnable {
	private Socket socket;
	private PanelLogin panelLogin;
	private FrameInterface frameInterface;
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;

	public Authentification(FrameInterface frameInterface, PanelLogin panelLogin, Socket socket) {
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

	@SuppressWarnings("unused")
	public void receive() {
		try {
			inputFromServer = new ObjectInputStream(socket.getInputStream());
			Object temp = null;

			temp = inputFromServer.readObject();

			if (temp != null) {
				if (temp instanceof Utilisateur) {
					Utilisateur u = (Utilisateur) temp;
					System.out.println("JE SUIS LA :" + temp);
					if (u != null) {
						frameInterface.setUser(u);
						frameInterface.initTousLesFils(u.getIdUser());
						frameInterface.getMenuBarInterface().setConnected();
						Tube tube = new Tube(frameInterface, socket);
						frameInterface.setTube(tube);
						Thread t = new Thread(tube);
						t.start();
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Donnees invalides");
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur reception login");
		} catch (ClassNotFoundException e) {
			System.err.println("Erreur de classe dans authentification");
			e.printStackTrace();
		}
	}

}
