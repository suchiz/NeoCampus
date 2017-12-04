package interfaceUtilisateur;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class PanelFilDeDiscussion extends JPanel{
	//Attributs
	private JTree filsArbre;
	private JScrollPane treePane;
	private JFrame parent;
	
	//Constructor
	public PanelFilDeDiscussion(JFrame parent){
		initcomponent();
		this.parent = parent;
	}
	
	public void initcomponent(){
		//Inits
		buildTree();
		
		//Events
		filsArbre.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				setTitle();
			}
		});
	
		//Layout
        javax.swing.GroupLayout panelFilLayout = new javax.swing.GroupLayout(this);
        setLayout(panelFilLayout);
        panelFilLayout.setHorizontalGroup(
            panelFilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treePane, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFilLayout.setVerticalGroup(
            panelFilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treePane)
                .addContainerGap())
        );
	}
	
	//Events
	
	//Others
	private void setTitle (){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                filsArbre.getLastSelectedPathComponent();
		if(node == null)
			return;
		parent.setTitle(node.toString());
	}
	
	public void buildTree(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tickets");
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Groupe 1");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Groupe 2");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Discussion 1");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("Discussion 2");
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("Discussion 3");
		
		node.add(node2);
		node1.add(node3);
		node1.add(node4);
		root.add(node);
		root.add(node1);
		
		filsArbre = new JTree(root);
		treePane = new JScrollPane(filsArbre);
		add(treePane);
	}
}
