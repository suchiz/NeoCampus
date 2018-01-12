package classes;

import java.io.Serializable;

public enum TypeMessage implements Serializable{
	REQUETE_INIT_GROUP,
	REQUETE_INIT_FDD,
	REQUETE_LOGIN,
	MESSAGE,
	ACK_MESSAGE,
	READ,
	READ_BY_ALL,
	PENDING,
	RECIEVED;	
}
