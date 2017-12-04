package interfaceUtilisateur;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class PanelFilDeDiscussion extends JPanel{
	//Attributs
	private JTree filsArbre;
	private JScrollPane treePane;
	
	//Constructor
	public PanelFilDeDiscussion(){
		initcomponent();
	}
	
	public void initcomponent(){
		//Inits
		buildTree();
		
		//Events
	
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
