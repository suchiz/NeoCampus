package interfaceUtilisateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import utilisateur.FilDeDiscussion;
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
		//setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		setViewportView(mainPanel);
		
		//Events
		
		//Layout

	}
	
	//Events
	
	//Others
	public void displayMessage(FilDeDiscussion fdd){
		mainPanel.removeAll();
		for (Message msg: fdd.getConversation()){
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.setBackground(Color.orange);
			JLabel tp = new JLabel(msg.getAuteur().toString());
			tp.setHorizontalAlignment(SwingConstants.LEFT);
			tp.setVerticalAlignment(SwingConstants.TOP);
			temp.add(tp, BorderLayout.WEST);
			JLabel tp1 = new JLabel(msg.dateToString());
			tp1.setHorizontalAlignment(SwingConstants.RIGHT);
			tp1.setVerticalAlignment(SwingConstants.TOP);
			temp.add(tp1, BorderLayout.EAST);
			JLabel tp2 = new JLabel(msg.getMsg());
			tp2.setHorizontalAlignment(SwingConstants.LEFT);
			tp2.setVerticalAlignment(SwingConstants.BOTTOM);
			temp.add(tp2, BorderLayout.SOUTH);
			mainPanel.add(temp);
			mainPanel.add(Box.createRigidArea(new Dimension(0, 7)));
		}
		invalidate();
		validate();
		repaint();
		getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
	}
}
