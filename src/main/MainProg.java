package main;

import interfaceUtilisateur.FrameInterface;

public class MainProg {

	public static void main(String[] args) {
    	FrameInterface frame = new FrameInterface();
    	frame.setVisible(true);
    	frame.getMenuFrame().initFrameLogin();
	}

}