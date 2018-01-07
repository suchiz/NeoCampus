package main;

import database.DB;
import ihm.FrameInterface;

public class MainProg {

	public static void main(String[] args) {
		
		DB database = new DB();
		database.creation_bd();
    	FrameInterface frame = new FrameInterface();
    	frame.setVisible(true);
    	frame.getMenuBarInterface().initFrameLogin();
	}

}
