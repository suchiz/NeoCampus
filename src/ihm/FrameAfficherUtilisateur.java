package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import classes.Agent;
import classes.Enseignant;
import classes.Etudiant;
import classes.Groupe;
import classes.Utilisateur;

public class FrameAfficherUtilisateur extends javax.swing.JFrame {
	// Variables declaration - do not modify
	/*
	 * private javax.swing.JList<JPanel> listeUtilisateur; private
	 * javax.swing.JScrollPane listeUtilisateurScrollPane; private
	 * javax.swing.DefaultListModel<JPanel> lmRef;
	 */
	private JPanel mainList = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private Color green = new Color(152, 230, 152);
	private Color red = new Color (255,153,153);
	private ImageIcon userRouge = new ImageIcon(RenduUtilisateurCell.class.getResource("/imgs/userRouge.png"));
	private ImageIcon userBleu = new ImageIcon(RenduUtilisateurCell.class.getResource("/imgs/userBleu.png"));
	private ImageIcon userVert = new ImageIcon(RenduUtilisateurCell.class.getResource("/imgs/userVert.png"));
	
	// End of variables declaration
	public FrameAfficherUtilisateur() {
		initComponents();
	}

	private void initComponents() {

		/*
		 * listeUtilisateurScrollPane = new javax.swing.JScrollPane(); listeUtilisateur
		 * = new javax.swing.JList<>(); lmRef = new DefaultListModel<>();
		 * listeUtilisateur.setModel(lmRef); listeUtilisateur.setCellRenderer(new
		 * RenduUtilisateurCell());
		 * listeUtilisateurScrollPane.setViewportView(listeUtilisateur);
		 */

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());

		mainList = new JPanel(new GridBagLayout());
		//initPanel();
		

		add(new JScrollPane(mainList));

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dispose();
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(mainList, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(mainList,
								javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	public void createPanel(Utilisateur user, boolean read) {
		JPanel panel = new JPanel();
		JLabel icon = null;
		JLabel nom = new JLabel(user.toString());
		if (user instanceof Etudiant) {
			icon = new JLabel(userBleu);
		} else if (user instanceof Enseignant) {
			icon = new JLabel(userRouge);
		} else if (user instanceof Agent) {
			icon = new JLabel(userVert);
		}

		if (read)
			panel.setBackground(green);
		else
			panel.setBackground(red);
		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(icon);
		panel.add(nom);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainList.add(panel, gbc, 0);
	}

	public void addUserInModel(Groupe g, Utilisateur user) {
		if(!g.getListeUtilisateur().contains(user)) 
			createPanel(user, false);
	}
	public void initPanel() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainList.add(new JPanel(), gbc);
	}
	public void initModel(Groupe g) {
		mainList.removeAll();
		initPanel();
		if (g != null) {
			for (Utilisateur u : g.getListeUtilisateur()) {
				createPanel(u, true);
			}
			validate();
			repaint();
		}
	}
}
