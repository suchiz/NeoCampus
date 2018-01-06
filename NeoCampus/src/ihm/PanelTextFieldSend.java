package ihm;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import utilisateur.Etudiant;
import utilisateur.FilDeDiscussion;
import utilisateur.Message;

@SuppressWarnings("serial")
public class PanelTextFieldSend extends JPanel{	
	//Attributs
	private JTextArea messageTextField = new javax.swing.JTextArea("Ecrivez votre message...");
	private JButton sendButton = new javax.swing.JButton("Envoyer");
	private JScrollPane scrollPanelText = new JScrollPane();
	private JTree arbreFilDeDiscussion;
	private PanelMessageDisplay panelMessageDisplay;
	private FrameInterface frameInterface;
	
	//Constructor
	public PanelTextFieldSend(FrameInterface frameInterface){
		this.frameInterface = frameInterface;
		initcomponent();
	}
	
	public void initcomponent(){
        //Inits
		messageTextField.setColumns(20);
        messageTextField.setRows(5);
        messageTextField.setLineWrap(true);
        scrollPanelText.setViewportView(messageTextField);
        
        
        //Events
        messageTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageTextFieldMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                messageTextFieldMouseExited(evt);
            }
        });
        messageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
					messageTextFieldKeyPressed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					sendButtonActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        //Layout
        javax.swing.GroupLayout panelTextLayout = new javax.swing.GroupLayout(this);
        setLayout(panelTextLayout);
        panelTextLayout.setHorizontalGroup(
            panelTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTextLayout.createSequentialGroup()
                .addComponent(scrollPanelText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addGap(16, 16, 16))
        );
        panelTextLayout.setVerticalGroup(
            panelTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextLayout.createSequentialGroup()
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelTextLayout.createSequentialGroup()
                .addComponent(scrollPanelText)
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
    
    private void messageTextFieldKeyPressed(java.awt.event.KeyEvent evt) throws IOException {                                            
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
        	envoyerMessage();
        }
        
    }
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                           
        envoyerMessage();
    } 

    //Others
    public void envoyerMessage() throws IOException{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                arbreFilDeDiscussion.getLastSelectedPathComponent();
		if(node == null)
			return;
		if (node.getUserObject() instanceof FilDeDiscussion){
			FilDeDiscussion f = (FilDeDiscussion) node.getUserObject();
			Message temp = new Message(frameInterface.getUser(), messageTextField.getText());
			frameInterface.getTube().send(temp);
			f.getConversation().add(temp);
			panelMessageDisplay.displayMessage(f);
			panelMessageDisplay.refresh();
			messageTextField.setText("");
		}
		
    }
    
    public void setArbreFilDeDiscussion(JTree arbreFilDeDiscussion) {
		this.arbreFilDeDiscussion = arbreFilDeDiscussion;
	} 
    
	public void setPanelMessageDisplay(PanelMessageDisplay panelMessageDisplay) {
		this.panelMessageDisplay = panelMessageDisplay;
	}
}
