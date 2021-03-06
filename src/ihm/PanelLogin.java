package ihm;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classes.Message;
import classes.TypeMessage;
import classes.Utilisateur;
import utilisateur.Connexion;

@SuppressWarnings("serial")
public class PanelLogin extends JPanel {
	// Attributs
	private JLabel usernameLabel = new javax.swing.JLabel("Username:");
	private JLabel passwordLabel = new javax.swing.JLabel("Password:");
	private JTextField textFieldUser = new javax.swing.JTextField();
	private JPasswordField textFieldPassword = new javax.swing.JPasswordField();
	private JButton loginButton = new javax.swing.JButton("Login");
	private FrameInterface frameInterface;
	private Utilisateur user;
	private ActionEvent ae;

	// Constructor
	public PanelLogin(FrameInterface frameInterface) {
		this.frameInterface = frameInterface;
		initcomponent();
	}

	public void initcomponent() {
		// Inits
		loginButton.setBackground(new java.awt.Color(238, 221, 255));

		// Events
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					loginButtonActionPerformed(evt);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frameInterface, "Unable to reach server");
					frameInterface.getMenuBarInterface().setDisconnected();
				}
			}
		});

		// Layout
		javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(this);
		setLayout(panelLoginLayout);
		panelLoginLayout.setHorizontalGroup(panelLoginLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
						.addContainerGap(27, Short.MAX_VALUE)
						.addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(usernameLabel)
								.addGroup(panelLoginLayout.createSequentialGroup()
										.addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(28, 28, 28)
										.addGroup(panelLoginLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(textFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE,
														163, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
														163, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addGap(21, 21, 21))
				.addGroup(panelLoginLayout.createSequentialGroup().addGap(119, 119, 119)
						.addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelLoginLayout.setVerticalGroup(panelLoginLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLoginLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(usernameLabel)
								.addComponent(textFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(passwordLabel).addComponent(textFieldPassword,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(20, 20, 20).addComponent(loginButton).addContainerGap()));
	}

	// Events
	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) throws UnknownHostException, IOException {
		if (checkFields()) {
			initUser();
			ae = evt;
		}
	}

	// Others

	public void closeWindow(java.awt.event.ActionEvent evt) {
		JComponent comp = (JComponent) evt.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
		frameInterface.getMenuBarInterface().setFrameLoginOpened(false);
	}

	private void initUser() throws UnknownHostException, IOException {
		try {

			Connexion login = new Connexion(frameInterface, this, new Socket("127.0.0.1", 7777));
			Thread t = new Thread(login);
			t.start();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frameInterface, "Unable to reach server");
		}
	
	}

	public void errorMessage() {
		JOptionPane.showMessageDialog(frameInterface, "Donnees invalides");
	}

	private boolean checkFields() {
		if (textFieldPassword.getText().equals("") || textFieldUser.getText().equals("")) {
			JOptionPane.showMessageDialog(frameInterface, "Donnees invalides");
			return false;
		}
		return true;
	}

	public Message createRequestLogin() {
		return new Message(textFieldUser.getText() + "#" + textFieldPassword.getText() + "#",
				TypeMessage.REQUETE_LOGIN);
	}

	public JButton getOkButton() {
		return loginButton;
	}

	public ActionEvent getAe() {
		return ae;
	}
}
