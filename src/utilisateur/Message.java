package utilisateur;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	private Utilisateur auteur;
	private Date date;
	private String msg;
	
	public Message(Utilisateur auteur, String msg) {
		this.date = new Date();
		this.msg = msg;
		this.auteur = auteur;
	}

	public Date getDate() {
		return date;
	}

	public String getMsg() {
		return msg;
	}	
	
	public Utilisateur getAuteur(){
		return auteur;
	}
	
	public String dateToString(){
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		return sdf.format(date);
	}
	
	public void envoyer (){
	
	}
}
