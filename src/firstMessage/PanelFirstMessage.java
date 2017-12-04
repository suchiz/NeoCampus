package firstMessage;

import interfaceUtilisateur.MenuBarInterface;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import utilisateur.FilDeDiscussion;
import utilisateur.Groupe;

@SuppressWarnings("serial")
public class PanelFirstMessage extends JPanel{
	//Attributs
	private JTextField textFieldFirstMessage = new javax.swing.JTextField("Ecrivez votre message...");
	private JButton buttonEnvoyer = new javax.swing.JButton("Envoyer");
	private JButton buttonAnnuler = new javax.swing.JButton("Annuler");
	private MenuBarInterface parent;
	private JTree panelFilDeDiscussion;
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
                buttonEnvoyerActionPerformed(evt);
            }
        });
        textFieldFirstMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	textFieldFirstMessageMouseClicked(evt);
            }
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

    private void buttonEnvoyerActionPerformed(java.awt.event.ActionEvent evt) {     
    	FilDeDiscussion nouveauFil = new FilDeDiscussion(textFieldTitre.getText(), new Groupe());
    	DefaultTreeModel model = (DefaultTreeModel) panelFilDeDiscussion.getModel();
    	DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
    	DefaultMutableTreeNode newGroupe = new DefaultMutableTreeNode(nouveauFil.getGroupe().getNomGroupe());
    	newGroupe.add(new DefaultMutableTreeNode(nouveauFil.getTitre()));
    	root.add(newGroupe);
    	model.reload(root);
    	closeWindow(evt);
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
   private void closeWindow(java.awt.event.ActionEvent evt){
	   	JComponent comp = (JComponent) evt.getSource();
   		Window win = SwingUtilities.getWindowAncestor(comp);
   		win.dispose();
   		parent.setFrameFirstMessageOpened(false);
   }
   
   public void setPanelFilDeDiscussion (JTree jt){
	   panelFilDeDiscussion = jt;
   }
   
   public void setParent(MenuBarInterface parent){
	   this.parent = parent;
   }
   
   public void setTextFieldTitre(JTextField jtf){
	   textFieldTitre = jtf;
   }
}
