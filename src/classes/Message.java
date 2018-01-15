package classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable, Comparable<Message> {
	private static final long serialVersionUID = 2743293072480480987L;
	private Utilisateur auteur;
	private Date date;
	private String msg;
	private int idMsg;
	private int idFil;
	private TypeMessage type;

	public Message(Utilisateur auteur, String msg) {
		this.date = new Date();
		this.msg = msg;
		this.auteur = auteur;
	}

	public Message(String msg, TypeMessage type) {
		this.msg = msg;
		this.type = type;
	}

	public Message(Utilisateur auteur, String msg, int idMsg) {
		this.date = new Date();
		this.msg = msg;
		this.auteur = auteur;
		this.idMsg = idMsg;
	}

	public Message(Utilisateur auteur, String msg, int idMsg, Date date) {
		this.date = new Date();
		this.msg = msg;
		this.auteur = auteur;
		this.idMsg = idMsg;
		this.date = date;
	}

	public Message(Utilisateur auteur, String msg, int idMsg, Date date, TypeMessage type) {
		this.date = new Date();
		this.msg = msg;
		this.auteur = auteur;
		this.idMsg = idMsg;
		this.date = date;
		this.setType(type);
	}

	public Message(Utilisateur auteur, String msg, int idMsg, int idFil, Date date, TypeMessage type) {
		this.date = new Date();
		this.msg = msg;
		this.auteur = auteur;
		this.idMsg = idMsg;
		this.date = date;
		this.idFil = idFil;
		this.setType(type);
	}

	public Message(String msg) {
		this.date = new Date();
		this.msg = msg;
	}

	public Message(Utilisateur user, String text, TypeMessage message) {
		this.date = new Date();
		this.msg = text;
		this.type = message;
		this.setAuteur(user);
	}

	public Message(Utilisateur user, String text, int idFil2, TypeMessage message) {
		this.date = new Date();
		this.msg = text;
		this.type = message;
		this.setAuteur(user);
		this.idFil = idFil2;
	}

	public String dateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h:mm a");
		return sdf.format(date);
	}

	@Override
	public String toString() {
		return msg;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public Date getDate() {
		return date;
	}

	public String getMsg() {
		return msg;
	}

	public int getIdMsg() {
		return idMsg;
	}

	public int getIdFil() {
		return idFil;
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setIdMsg(int idMsg) {
		this.idMsg = idMsg;
	}

	public void setIdFil(int idFil) {
		this.idFil = idFil;
	}

	@Override
	public int compareTo(Message messagetocompare) {
		return this.date.compareTo(messagetocompare.getDate());
	}

}
