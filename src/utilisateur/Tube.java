package utilisateur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tube implements Runnable {
	private Socket socket;
	ObjectInputStream inputFromServer;
	ObjectOutputStream outputToServer;

	public Tube(Socket socket) throws IOException {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			listening();
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Déconnecté !");
		}
	}

	private void listening() throws ClassNotFoundException, IOException {
		while (true) {
			receive();
		}
	}

	public void receive() throws ClassNotFoundException, IOException {
		inputFromServer = new ObjectInputStream(socket.getInputStream());
		Message message = (Message) inputFromServer.readObject();
		if (message != null)
			System.out.println("De serveur: " + message.getMsg());
	}

	public void send(Message message) throws IOException {
		outputToServer = new ObjectOutputStream(socket.getOutputStream());
		outputToServer.writeObject(message);
		outputToServer.flush();
		
	}
	
	public void disconnect() throws IOException{
		if (outputToServer != null)
			outputToServer.flush();
		socket.close();
	}
}
