package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import classes.FilDeDiscussion;
import classes.Message;
import classes.Utilisateur;

@SuppressWarnings("serial")
public class PanelMessageDisplay extends JScrollPane{
	//Attributs
	private JPanel mainPanel = new JPanel();
	private Color grey= new Color (242,242,242);
	private Color red = new Color (255,153,153);
	private Color orange = new Color (255,194,102);
	private Color green = new Color (152,230,152);
	private FrameAfficherUtilisateur frameAfficherUtilisateur = new FrameAfficherUtilisateur();
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
	public void displayMessage(final FilDeDiscussion fdd){
		mainPanel.removeAll();
		for (Message msg: fdd.getConversation()){
			JPanel temp = new JPanel();
			JTextArea tp2 = new JTextArea(msg.getMsg());
			temp.setLayout(new BorderLayout());
			temp.addMouseListener(new MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					frameAfficherUtilisateur.setVisible(true);
					frameAfficherUtilisateur.initModel(fdd.getGroupe());
					frameAfficherUtilisateur.addUserInModel(fdd.getGroupe(), fdd.getCreateur());
					frameAfficherUtilisateur.setVisible(true);
				}
			});
			switch (msg.getType()) {
			case RECEIVED:
				temp.setBackground(red);
				tp2.setBackground(red);
				break;
			case READ:
				temp.setBackground(orange);
				tp2.setBackground(orange);
				break;
			case READ_BY_ALL:
				temp.setBackground(green);
				tp2.setBackground(green);
				break;
			default:
				temp.setBackground(grey);
				tp2.setBackground(grey);
				break;
			}
			
			
			JLabel tp = new JLabel(msg.getAuteur().toString());
			tp.setHorizontalAlignment(SwingConstants.LEFT);
			tp.setVerticalAlignment(SwingConstants.TOP);
			temp.add(tp, BorderLayout.WEST);
			
			JLabel tp1 = new JLabel(msg.dateToString());
			tp1.setHorizontalAlignment(SwingConstants.RIGHT);
			tp1.setVerticalAlignment(SwingConstants.TOP);
			temp.add(tp1, BorderLayout.EAST);
			
			
			
			tp2.setLineWrap(true);
			tp2.setEditable(false);
			temp.add(tp2, BorderLayout.SOUTH);
			
			mainPanel.add(temp);
			mainPanel.add(Box.createRigidArea(new Dimension(0, 7)));
		}
		if (fdd.getConversation().size() < 6)
			for (int i = 0; i < 20 - fdd.getConversation().size(); i++)
				mainPanel.add(new JPanel());
		refresh();
	}
	
	public void clear() {
		mainPanel.removeAll();
		refresh();
	}
	
	public void refresh(){	
		invalidate();
		validate();
		repaint();
		getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
	}
}
