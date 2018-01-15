package classes;

import java.io.Serializable;

public enum TypeMessage implements Serializable{
	REQUETE_STATUS_MESSAGE,
	REQUETE_INIT_GROUP,
	REQUETE_INIT_FDD,
	REQUETE_LOGIN,
	REQUETE_STATUS_MESSAGE,
	MESSAGE,
	READ,
	READ_BY_ALL,
	PENDING,
	RECEIVED;	
}
