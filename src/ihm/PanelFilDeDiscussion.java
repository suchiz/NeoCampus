package ihm;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import classes.FilDeDiscussion;
import classes.Groupe;
import classes.Message;
import classes.TypeMessage;

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
				//readMessages();

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
		boolean alreadyAdded = false;
		int temp = fdd.getGroupe().getIdGroupe();
		DefaultTreeModel model = (DefaultTreeModel) arbreFilDeDiscussion.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		for (Groupe g : frameInterface.getTousLesGroupes()) {
			DefaultMutableTreeNode nodeTemp = parcourirArbre(root, temp);
			if (nodeTemp != null) {
				nodeTemp.add(new DefaultMutableTreeNode(fdd));
				alreadyAdded = true;
				break;
			}
		}
		if (!alreadyAdded) {
			DefaultMutableTreeNode newGroupe = new DefaultMutableTreeNode(fdd.getGroupe());
			newGroupe.add(new DefaultMutableTreeNode(fdd));
			root.add(newGroupe);
		}
		model.reload(root);

	}

	private DefaultMutableTreeNode parcourirArbre(DefaultMutableTreeNode root, int idGroupRef) {
		DefaultMutableTreeNode nodeToReturn = null;
		for (int i = 0; i < root.getChildCount(); i++) {
			DefaultMutableTreeNode temp = (DefaultMutableTreeNode) root.getChildAt(i);
			if (temp.getUserObject() instanceof Groupe) {
				Groupe groupeTemp = (Groupe) temp.getUserObject();
				if (groupeTemp.getIdGroupe() == idGroupRef) {
					nodeToReturn = temp;
					break;
				}
			} else {
				parcourirArbre((DefaultMutableTreeNode) root.getChildAt(i), idGroupRef);
			}
		}
		return nodeToReturn;
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


	public void readMessages() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbreFilDeDiscussion.getLastSelectedPathComponent();
		if (node != null) {
			if (node.getUserObject() != null) {
				if (node.getUserObject() instanceof FilDeDiscussion) {
					FilDeDiscussion fdd = (FilDeDiscussion) node.getUserObject();
					for (Message msg : fdd.getConversation()) {
						if (msg.getType() != TypeMessage.READ_BY_ALL || msg.getType() != TypeMessage.READ) {
							msg.setType(TypeMessage.READ);
							try {
								msg.setAuteur(frameInterface.getUser());
								frameInterface.getTube().send(msg);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

}
