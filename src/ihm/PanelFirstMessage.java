package ihm;

import java.awt.Window;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import utilisateur.FilDeDiscussion;
import utilisateur.Message;

@SuppressWarnings("serial")
public class PanelFirstMessage extends JPanel{
	//Attributs
	private JTextField textFieldFirstMessage = new javax.swing.JTextField("Ecrivez votre message...");
	private JButton buttonEnvoyer = new javax.swing.JButton("Envoyer");
	private JButton buttonAnnuler = new javax.swing.JButton("Annuler");
	private FrameInterface frameInterface;
	private JTree arbreFilDeDiscussion;
	private PanelSelectionGroupes panelSelectionGroupes;
	private JTextField textFieldTitre;
	
	//Constructor
    public PanelFirstMessage(){
    	initcomponent();
    }
    
	public void initcomponent(){
		//Inits
		
		//Events
        buttonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnnulerActionPerformed(evt);
            }
        });
        buttonEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					buttonEnvoyerActionPerformed(evt);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frameInterface, "Unable to send a message");
				}
            }
        });
        textFieldFirstMessage.addMouseListener(new java.awt.event.MouseAdapter() {
        	@Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	textFieldFirstMessageMouseClicked(evt);
            }
        	@Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	textFieldFirstMessageMouseExited(evt);
            }
        });
        
        
		//Layout
		javax.swing.GroupLayout panelFirstMessageLayout = new javax.swing.GroupLayout(this);
        setLayout(panelFirstMessageLayout);
        panelFirstMessageLayout.setHorizontalGroup(
            panelFirstMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFirstMessageLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(buttonEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(buttonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addComponent(textFieldFirstMessage, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelFirstMessageLayout.setVerticalGroup(
            panelFirstMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFirstMessageLayout.createSequentialGroup()
                .addComponent(textFieldFirstMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelFirstMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEnvoyer)
                    .addComponent(buttonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
	}

	//Events
    private void buttonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	closeWindow(evt);
    }                                             

    private void buttonEnvoyerActionPerformed(java.awt.event.ActionEvent evt) throws IOException {     
    	if(checkFields()){
    		envoieMessage();
    		closeWindow(evt);
    	}
    }   
    
    private void textFieldFirstMessageMouseClicked(java.awt.event.MouseEvent evt) {                                              
        if (textFieldFirstMessage.getText().equals("Ecrivez votre message..."))
        	textFieldFirstMessage.selectAll();
   }                                             

   private void textFieldFirstMessageMouseExited(java.awt.event.MouseEvent evt) {                                             
   	  if (textFieldFirstMessage.getText().equals(""))
   		textFieldFirstMessage.setText("Ecrivez votre message...");
   }    
   
   //Others
   private void envoieMessage() throws IOException{
   	FilDeDiscussion nouveauFil = new FilDeDiscussion(textFieldTitre.getText(), panelSelectionGroupes.getGroupeSelected(), frameInterface.getUser());
   	Message temp = new Message(frameInterface.getUser(), textFieldFirstMessage.getText());
   	frameInterface.getTube().send(temp);
   	nouveauFil.getConversation().add(temp);
   	frameInterface.getPanelFil().ajouterFilDeDisussion(nouveauFil);
   }
   
   private boolean checkFields(){
	   if (panelSelectionGroupes.getGroupeSelected() == null || textFieldFirstMessage.getText().equals("")
			   || textFieldFirstMessage.getText().equals("Ecrivez votre message...") || textFieldTitre.getText().equals("")){
		   JOptionPane.showMessageDialog(frameInterface, "Remplissez tous les champs");
		   return false;
	   }
			   return true;
   }
   
   private void closeWindow(java.awt.event.ActionEvent evt){
	   	JComponent comp = (JComponent) evt.getSource();
   		Window win = SwingUtilities.getWindowAncestor(comp);
   		win.dispose();
   		frameInterface.getMenuBarInterface().setFrameFirstMessageOpened(false);
   }
   
   public void setArbreFilDeDiscussion (JTree jt){
	   arbreFilDeDiscussion = jt;
   }
   
   public void setParent(FrameInterface frameInterface){
	   this.frameInterface = frameInterface;
   }
   
   public void setTextFieldTitre(JTextField jtf){
	   textFieldTitre = jtf;
   }

   public void setPanelSelectionGroupes(PanelSelectionGroupes panelSelectionGroupes) {
	   this.panelSelectionGroupes = panelSelectionGroupes;
   }
   
   
	
}
