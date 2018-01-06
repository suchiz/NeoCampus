package ihm;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import utilisateur.Groupe;

@SuppressWarnings("serial")
public class RenduJTree extends DefaultTreeCellRenderer {
	ImageIcon groupeIcon = new ImageIcon ("groupIcon.png");
	ImageIcon discussionIcon = new ImageIcon ("mailIcon.jpg");
	
	public Component getTreeCellRendererComponent(JTree tree,
	    Object value, boolean selected, boolean expanded,
	    boolean leaf, int row, boolean hasFocus) {
	        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	        
	        if (node.getUserObject() instanceof Groupe) {
	           Image i = groupeIcon.getImage().getScaledInstance(30, -1, Image.SCALE_AREA_AVERAGING);
	           setIcon(new ImageIcon(i));
	        } else {
	        	Image i = discussionIcon.getImage().getScaledInstance(20, -1, Image.SCALE_DEFAULT);
	        	setIcon(new ImageIcon(i));
	        }
	        return this;
	}

}
