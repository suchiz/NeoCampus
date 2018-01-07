package utilisateur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Tube implements Runnable {
	private Socket socket;

	public Tube(Socket socket) throws IOException {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			listening();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private void listening() throws ClassNotFoundException, IOException {
		while (true) {
			receive();
		}
	}

	public void receive() throws ClassNotFoundException, IOException {
		ObjectInputStream inputFromServer = new ObjectInputStream(socket.getInputStream());
		Message message = (Message) inputFromServer.readObject();
		if (message != null)
			System.out.println(message.getMsg());
	}

	public void send(Message message) throws IOException {
		ObjectOutputStream outputToServer = new ObjectOutputStream(socket.getOutputStream());
		outputToServer.writeObject(message);
		outputToServer.flush();
		
	}
	
	public void disconnect() throws IOException{
		//socket.close();
	}
}
