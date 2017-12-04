package interfaceUtilisateur;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelTextFieldSend extends JPanel{	
	//Attributs
	private JTextField messageTextField = new javax.swing.JTextField("Ecrivez votre message...");
	private JButton sendButton = new javax.swing.JButton("Envoyer");
	
	//Constructor
	public PanelTextFieldSend(){
		initcomponent();
	}
	
	public void initcomponent(){
        //Inits
        
        
        //Events
        messageTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageTextFieldMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                messageTextFieldMouseExited(evt);
            }
        });
        
        //Layout
        javax.swing.GroupLayout panelTextLayout = new javax.swing.GroupLayout(this);
        setLayout(panelTextLayout);
        panelTextLayout.setHorizontalGroup(
            panelTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTextLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(messageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sendButton)
                .addGap(16, 16, 16))
        );
        panelTextLayout.setVerticalGroup(
            panelTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextLayout.createSequentialGroup()
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelTextLayout.createSequentialGroup()
                .addComponent(messageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
	}
	
	//Events
    private void messageTextFieldMouseClicked(java.awt.event.MouseEvent evt) {                                              
         if (messageTextField.getText().equals("Ecrivez votre message..."))
        	 messageTextField.selectAll();
    }                                             

    private void messageTextFieldMouseExited(java.awt.event.MouseEvent evt) {                                             
    	  if (messageTextField.getText().equals(""))
          	messageTextField.setText("Ecrivez votre message...");
    }       

    //Others
}
