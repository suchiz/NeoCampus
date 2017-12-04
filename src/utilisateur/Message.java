package utilisateur;

import java.util.Date;

public class Message {
	private Date date;
	private String msg;
	
	public Message(Date date, String msg) {
		this.date = date;
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public String getMsg() {
		return msg;
	}	
	
	public void envoyer (){
	
	}
}
