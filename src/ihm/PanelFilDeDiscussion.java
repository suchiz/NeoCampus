package ihm;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import classes.Etudiant;
import classes.FilDeDiscussion;
import classes.Groupe;

@SuppressWarnings("serial")
public class PanelFilDeDiscussion extends JPanel {
	// Attributs
	private JTree arbreFilDeDiscussion = new JTree();
	private JScrollPane treePane;
	private FrameInterface frameInterface;
	private PanelMessageDisplay panelMessageDisplay;

	// Constructor
	public PanelFilDeDiscussion(FrameInterface parent) {
		this.frameInterface = parent;
		initcomponent();
	}

	public void initcomponent() {
		// Inits
		setArbreEmpty();

		// Events
		arbreFilDeDiscussion.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				setTitle();
				displayFilDeDiscussion();
			}
		});

		// Layout
		javax.swing.GroupLayout panelFilLayout = new javax.swing.GroupLayout(this);
		setLayout(panelFilLayout);
		panelFilLayout.setHorizontalGroup(panelFilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelFilLayout.createSequentialGroup().addContainerGap()
						.addComponent(treePane, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addContainerGap()));
		panelFilLayout.setVerticalGroup(
				panelFilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelFilLayout
						.createSequentialGroup().addContainerGap().addComponent(treePane).addContainerGap()));
	}

	// Events

	// Others
	private void setTitle() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbreFilDeDiscussion.getLastSelectedPathComponent();
		if (node == null)
			return;
		frameInterface.setTitle(node.toString());
	}

	private void displayFilDeDiscussion() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbreFilDeDiscussion.getLastSelectedPathComponent();
		if (node != null) {
			if (node.getUserObject() != null) {
				if (node.getUserObject() instanceof FilDeDiscussion) {
					FilDeDiscussion fdd = (FilDeDiscussion) node.getUserObject();
					panelMessageDisplay.displayMessage(fdd);
				}
			}
		}
	}


	public void ajouterFilDeDisussion(FilDeDiscussion fdd) {
		DefaultTreeModel model = (DefaultTreeModel) arbreFilDeDiscussion.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		DefaultMutableTreeNode newGroupe = new DefaultMutableTreeNode(fdd.getGroupe());
		newGroupe.add(new DefaultMutableTreeNode(fdd));
		root.add(newGroupe);
		model.reload(root);
	}

	public void setArbreEmpty() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tickets");
		arbreFilDeDiscussion = new JTree(root);
		arbreFilDeDiscussion.setRootVisible(false);
		arbreFilDeDiscussion.setCellRenderer(new RenduJTree());
		arbreFilDeDiscussion.repaint();
		treePane = new JScrollPane(arbreFilDeDiscussion);
		add(treePane);
		treePane.repaint();
		repaint();
	}

	public void clear() {
		DefaultTreeModel model = (DefaultTreeModel) arbreFilDeDiscussion.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		root.removeAllChildren();
		model.reload();
	}

	public JTree getFilsArbre() {
		return arbreFilDeDiscussion;
	}

	public void setPanelMessageDisplay(PanelMessageDisplay panelMessageDisplay) {
		this.panelMessageDisplay = panelMessageDisplay;
	}

}
