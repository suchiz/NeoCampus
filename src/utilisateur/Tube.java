package utilisateur;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Tube implements Runnable {
	private Socket socket;
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;
	private Message message;

	public Tube(Socket socket) throws IOException {
		this.socket = socket;
		
		
	}


	@Override
	public void run() {
			
	}

	private void listening() throws ClassNotFoundException, IOException{
		while(true){
			receive();
		}
	}
	
	public void receive() throws ClassNotFoundException, IOException{
		inputFromServer = new ObjectInputStream(socket.getInputStream());
		if (inputFromServer.available() > 0){
		message = (Message) inputFromServer.readObject();
		System.out.println(message.getMsg());
		}
	}
	
	public void send(Message message) throws IOException{
		outputToServer = new ObjectOutputStream(socket.getOutputStream());
		outputToServer.writeObject(message);
		outputToServer.flush();
	}
}