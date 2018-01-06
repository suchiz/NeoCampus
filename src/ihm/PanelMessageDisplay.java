package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
			temp.setBackground(Color.orange);
			temp.setLayout(new BorderLayout());
			JLabel tp = new JLabel(msg.getAuteur().toString());
			tp.setHorizontalAlignment(SwingConstants.LEFT);
			tp.setVerticalAlignment(SwingConstants.TOP);
			temp.add(tp, BorderLayout.WEST);
			JLabel tp1 = new JLabel(msg.dateToString());
			tp1.setHorizontalAlignment(SwingConstants.RIGHT);
			tp1.setVerticalAlignment(SwingConstants.TOP);
			temp.add(tp1, BorderLayout.EAST);
			JTextArea tp2 = new JTextArea(msg.getMsg());
			tp2.setBackground(Color.orange);
			tp2.setLineWrap(true);
			temp.add(tp2, BorderLayout.SOUTH);
			
			mainPanel.add(temp);
			mainPanel.add(Box.createRigidArea(new Dimension(0, 7)));
		}
		if (fdd.getConversation().size() < 6)
			for (int i = 0; i < 20 - fdd.getConversation().size(); i++)
				mainPanel.add(new JPanel());
		refresh();
	}
	
	public void refresh(){	
		invalidate();
		validate();
		repaint();
		getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
	}
}
