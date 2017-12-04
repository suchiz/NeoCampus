package interfaceUtilisateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utilisateur.Etudiant;
import utilisateur.FilDeDiscussion;
import utilisateur.Groupe;
import utilisateur.Message;

@SuppressWarnings("serial")
public class PanelMessageDisplay extends JScrollPane{
	//Attributs
	private JPanel mainPanel = new JPanel();
	
	//Constructor
	public PanelMessageDisplay(){
		initcomponent();
	}

	public void initcomponent(){
		//Inits
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		setViewportView(mainPanel);
		testAffichage();
		
		//Events
		
		//Layout

	}
	
	//Events
	
	//Others
	public void testAffichage(){
		FilDeDiscussion test = new FilDeDiscussion("test", new Groupe());
		for (int i = 0; i < 100; i++){
			test.getConvo().add(new Message(new Etudiant("Jean","Eude", 2), "Numero" + i));
		}
		displayMessage(test);
	}
	
	public void displayMessage(FilDeDiscussion fdd){
		for (Message msg: fdd.getConvo()){
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.setBackground(Color.orange);
			temp.add(new JLabel(msg.getAuteur().toString()), BorderLayout.WEST);
			temp.add(new JLabel(msg.dateToString()), BorderLayout.EAST);
			temp.add(new JLabel(msg.getMsg()), BorderLayout.SOUTH);
			mainPanel.add(temp);
			mainPanel.add(Box.createRigidArea(new Dimension(0, 7)));
		}
	
	}
}
