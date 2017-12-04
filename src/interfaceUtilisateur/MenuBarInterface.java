package interfaceUtilisateur;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import loginScreen.FrameLogin;
import firstMessage.FrameFirstMessage;

@SuppressWarnings("serial")
public class MenuBarInterface extends JMenuBar{
	//Attributs
    private JMenu fichierMenu = new JMenu("Fichier");
    private JMenuItem connexionItem = new JMenuItem("Connexion");
    private JMenuItem deconnexionItem = new JMenuItem("Déconnexion");
    private JMenuItem quitterItem = new JMenuItem("Quitter");
    private JMenuItem refreshItem = new JMenuItem("Rafraichir");
    private JMenuItem nouveauItem = new JMenuItem("Nouveau fichier");
    private JPopupMenu.Separator separator = new JPopupMenu.Separator();
    private FrameLogin frameLogin;
    private boolean frameLoginOpened = false;
    FrameFirstMessage frameFirstMessage;
    private boolean frameFirstMessageOpened = false;
    
    //Constructor
	public MenuBarInterface (){
		initcomponent();
	}
	
	public void initcomponent(){
		//Inits
        connexionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        fichierMenu.add(connexionItem);
        deconnexionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        fichierMenu.add(deconnexionItem);
        fichierMenu.add(separator);
        nouveauItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        fichierMenu.add(nouveauItem);
        refreshItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        fichierMenu.add(refreshItem);
        quitterItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        fichierMenu.add(quitterItem);
        add(fichierMenu);
        
        //Events
        nouveauItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nouveauItemActionPerformed(evt);
            }
        });
        refreshItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshItemActionPerformed(evt);
            }
        });
        quitterItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitterItemActionPerformed(evt);
            }
        });
        deconnexionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionItemActionPerformed(evt);
            }
        });
        connexionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionItemActionPerformed(evt);
            }
        });
        
        //Layout
	}
	
	//Events
    private void quitterItemActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	System.exit(0);
    }                                           

    private void connexionItemActionPerformed(java.awt.event.ActionEvent evt) {
    		if (frameLoginOpened){
    			frameLogin.toFront();
    			frameLogin.repaint();
    		} else {
    			frameLogin = new FrameLogin(this);
    			frameLogin.setVisible(true);
    			frameLoginOpened = true;
    		}
    }                                             

    private void deconnexionItemActionPerformed(java.awt.event.ActionEvent evt) {                                                

    }                                               

    private void nouveauItemActionPerformed(java.awt.event.ActionEvent evt) {   
		if (frameFirstMessageOpened){
			frameFirstMessage.toFront();
			frameFirstMessage.repaint();
		} else {
	    	frameFirstMessage = new FrameFirstMessage(this);
	    	frameFirstMessage.setVisible(true);
	    	frameFirstMessageOpened = true;
		}
    }                                           

    private void refreshItemActionPerformed(java.awt.event.ActionEvent evt) {                                            

    }  
    
    //Others
    public void setFrameLoginOpened(boolean b){
    	frameLoginOpened = b;
    }
    
    public void setFrameFirstMessageOpened(boolean b){
    	frameFirstMessageOpened = b;
    }
    
    public void initFrameLogin() {
			frameLogin = new FrameLogin(this);
			frameLogin.setVisible(true);
			frameLoginOpened = true;
    }                                             


}
