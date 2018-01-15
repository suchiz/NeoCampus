package ihm;

import javax.swing.DefaultListModel;

import classes.Groupe;
import classes.Utilisateur;

public class FrameAfficherUtilisateur extends javax.swing.JFrame {

	public FrameAfficherUtilisateur() {
		initComponents();
	}

	private void initComponents() {

		listeUtilisateurScrollPane = new javax.swing.JScrollPane();
		listeUtilisateur = new javax.swing.JList<>();
		lmRef = new DefaultListModel<>();
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);

		listeUtilisateur.setModel(lmRef);
		listeUtilisateur.setCellRenderer(new RenduUtilisateurCell());
		listeUtilisateurScrollPane.setViewportView(listeUtilisateur);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dispose();
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(listeUtilisateurScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addComponent(listeUtilisateurScrollPane,
								javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JList<Utilisateur> listeUtilisateur;
	private javax.swing.JScrollPane listeUtilisateurScrollPane;
	private javax.swing.DefaultListModel<Utilisateur> lmRef;
	// End of variables declaration

	public void addUserInModel(Utilisateur user) {
		boolean alreadyIn = false;
		for (int i = 0; i < lmRef.getSize(); i++) {
			Utilisateur temp = lmRef.getElementAt(i);
			if (user.getIdUser() == temp.getIdUser()) {
				alreadyIn = true;
				break;
			}
		}
		if (!alreadyIn) {
			lmRef.addElement(user);
			listeUtilisateur.setModel(lmRef);
		}
	}

	public void initModel(Groupe g) {
		if (g != null) {
			lmRef.removeAllElements();
			for (Utilisateur u : g.getListeUtilisateur())
				lmRef.addElement(u);
			listeUtilisateur.setModel(lmRef);
		}
	}
}
