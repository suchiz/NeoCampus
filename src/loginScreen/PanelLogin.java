package loginScreen;

import interfaceUtilisateur.MenuBarInterface;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class PanelLogin extends JPanel{
	//Attributs
	private JLabel usernameLabel = new javax.swing.JLabel("Username:");
	private JLabel passwordLabel = new javax.swing.JLabel("Password:");
	private JTextField textFieldUser = new javax.swing.JTextField();
	private JTextField textFieldPassword = new javax.swing.JTextField();
	private JButton loginButton = new javax.swing.JButton("Login");
	private MenuBarInterface parent;
	
	//Constructor
	public PanelLogin(){
		initcomponent();
	}
	
	
	public void initcomponent(){
		//Inits
        loginButton.setBackground(new java.awt.Color(238, 221, 255));
        
        //Events
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        
		//Layout
	     javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(this);
	        setLayout(panelLoginLayout);
	        panelLoginLayout.setHorizontalGroup(
	            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
	                .addContainerGap(27, Short.MAX_VALUE)
	                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(usernameLabel)
	                    .addGroup(panelLoginLayout.createSequentialGroup()
	                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(28, 28, 28)
	                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(textFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(21, 21, 21))
	            .addGroup(panelLoginLayout.createSequentialGroup()
	                .addGap(119, 119, 119)
	                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        panelLoginLayout.setVerticalGroup(
	            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelLoginLayout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(usernameLabel)
	                    .addComponent(textFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(passwordLabel)
	                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(20, 20, 20)
	                .addComponent(loginButton)
	                .addContainerGap())
	        );
	}
	
	//Events
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	JComponent comp = (JComponent) evt.getSource();
    	Window win = SwingUtilities.getWindowAncestor(comp);
    	win.dispose();
    	parent.setFrameLoginOpened(false);
    }     
    
    //Others
    public void setParent(MenuBarInterface parent){
		this.parent = parent;
    
    }

}
