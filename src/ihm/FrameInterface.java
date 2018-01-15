package ihm;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classes.FilDeDiscussion;
import classes.Groupe;
import classes.Utilisateur;
import utilisateur.Tube;

@SuppressWarnings("serial")
public class FrameInterface extends JFrame {
	// Attributs
	private Utilisateur user;
	private JPanel mainPanel = new JPanel();
	private PanelFilDeDiscussion panelFil;
	private PanelTextFieldSend panelText;
	private PanelMessageDisplay panelMsg;
	private MenuBarInterface menuBar;
	private Tube tube;
	private ArrayList<Groupe> tousLesGroupes = new ArrayList<>();
	private ArrayList<FilDeDiscussion> tousLesFils = new ArrayList<>();

	// Constructor
	public FrameInterface() {
		initcomponent();
	}

	public void initcomponent() {
		// Inits
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		setMinimumSize(new Dimension(500, 400));
		setPreferredSize(new Dimension(1000, 700));
		panelFil = new PanelFilDeDiscussion(this);
		panelText = new PanelTextFieldSend(this);
		panelMsg = new PanelMessageDisplay(this);
		menuBar = new MenuBarInterface(this);
		menuBar.setArbreFilDeDiscussion(panelFil.getFilsArbre());
		panelText.setArbreFilDeDiscussion(panelFil.getFilsArbre());
		panelText.setPanelMessageDisplay(panelMsg);
		panelFil.setPanelMessageDisplay(panelMsg);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Events
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					if (tube != null)
						tube.disconnect();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(menuBar, "Disconnected !");
				} finally {
					System.exit(0);
				}
			}
		});

		// Layout
		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup()
						.addComponent(panelFil, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(panelMsg).addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addContainerGap()
						.addComponent(panelMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panelText,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addComponent(panelFil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		setJMenuBar(menuBar);
		getContentPane().add(mainPanel);
		pack();
	}
	// Events

	// Others
	public void initTousLesFils(List<FilDeDiscussion> list) {
		tousLesFils = (ArrayList<FilDeDiscussion>) list;
		for (FilDeDiscussion filDeDiscussion : list) {
			panelFil.ajouterFilDeDisussion(filDeDiscussion);
		}
	}

	public MenuBarInterface getMenuBarInterface() {
		return menuBar;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public PanelFilDeDiscussion getPanelFil() {
		return panelFil;
	}

	public PanelTextFieldSend getPanelText() {
		return panelText;
	}

	public PanelMessageDisplay getPanelMsg() {
		return panelMsg;
	}

	public Tube getTube() {
		return tube;
	}

	public void setTube(Tube tube) {
		this.tube = tube;
	}

	public ArrayList<FilDeDiscussion> getTousLesFils() {
		return tousLesFils;
	}

	public void setTousLesFils(ArrayList<FilDeDiscussion> tousLesFils) {
		this.tousLesFils = tousLesFils;
	}

	public ArrayList<Groupe> getTousLesGroupes() {
		return tousLesGroupes;
	}

	public void setTousLesGroupes(List<Groupe> list) {
		this.tousLesGroupes = (ArrayList<Groupe>) list;
	}
}
