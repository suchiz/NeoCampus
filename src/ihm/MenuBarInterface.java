package ihm;

import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

@SuppressWarnings("serial")
public class MenuBarInterface extends JMenuBar {
	// Attributs
	private FrameInterface frameInterface;
	private JMenu fichierMenu = new JMenu("Fichier");
	private JMenuItem connexionItem = new JMenuItem("Connexion");
	private JMenuItem deconnexionItem = new JMenuItem("Deconnexion");
	private JMenuItem quitterItem = new JMenuItem("Quitter");
	private JMenuItem nouveauItem = new JMenuItem("Nouveau message");
	private JPopupMenu.Separator separator = new JPopupMenu.Separator();
	private FrameLogin frameLogin;
	private boolean frameLoginOpened = false;
	private FrameFirstMessage frameFirstMessage;
	private boolean frameFirstMessageOpened = false;
	private JTree arbreFilDeDiscussion;
	private boolean isConnected = false;

	// Constructor
	public MenuBarInterface(FrameInterface frameInterface) {
		this.frameInterface = frameInterface;
		initcomponent();
	}

	public void initcomponent() {
		// Inits
		connexionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_E,
				java.awt.event.InputEvent.CTRL_MASK));
		fichierMenu.add(connexionItem);
		deconnexionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_D,
				java.awt.event.InputEvent.CTRL_MASK));
		fichierMenu.add(deconnexionItem);
		fichierMenu.add(separator);
		nouveauItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_N,
				java.awt.event.InputEvent.CTRL_MASK));
		fichierMenu.add(nouveauItem);
		quitterItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Q,
				java.awt.event.InputEvent.CTRL_MASK));
		fichierMenu.add(quitterItem);
		add(fichierMenu);
		setDisconnected();

		// Events
		nouveauItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nouveauItemActionPerformed(evt);
			}
		});
		quitterItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				quitterItemActionPerformed(evt);
			}
		});
		deconnexionItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					deconnexionItemActionPerformed(evt);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frameInterface, "Disconnected !");
				}
			}
		});
		connexionItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				connexionItemActionPerformed(evt);
			}
		});

		// Layout
	}

	// Events
	private void quitterItemActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void connexionItemActionPerformed(java.awt.event.ActionEvent evt) {
		if (frameLoginOpened) {
			frameLogin.toFront();
			frameLogin.repaint();
		} else {
			frameLogin = new FrameLogin(frameInterface);
			frameLogin.setVisible(true);
			frameLoginOpened = true;
		}
	}

	private void deconnexionItemActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		frameInterface.getPanelFil().setArbreEmpty();
		if(frameInterface.getTube() != null)
			frameInterface.getTube().disconnect();
		setDisconnected();
	}

	private void nouveauItemActionPerformed(java.awt.event.ActionEvent evt) {
		if (frameFirstMessageOpened) {
			frameFirstMessage.toFront();
			frameFirstMessage.repaint();
		} else {
			frameFirstMessage = new FrameFirstMessage(frameInterface, arbreFilDeDiscussion);
			frameFirstMessage.setVisible(true);
			frameFirstMessageOpened = true;
		}
	}

	// Others
	public void setFrameLoginOpened(boolean b) {
		frameLoginOpened = b;
	}

	public void setFrameFirstMessageOpened(boolean b) {
		frameFirstMessageOpened = b;
	}

	public void initFrameLogin() {
		frameLogin = new FrameLogin(frameInterface);
		frameLogin.setVisible(true);
		frameLoginOpened = true;
	}

	public void setArbreFilDeDiscussion(JTree arbreFilDeDiscussion) {
		this.arbreFilDeDiscussion = arbreFilDeDiscussion;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected() {
		connexionItem.setEnabled(false);
		deconnexionItem.setEnabled(true);
		nouveauItem.setEnabled(true);
		isConnected = true;
	}

	public void setDisconnected() {
		connexionItem.setEnabled(true);
		deconnexionItem.setEnabled(false);
		nouveauItem.setEnabled(false);
		isConnected = false;
	}

}
