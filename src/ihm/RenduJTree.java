package ihm;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import classes.FilDeDiscussion;
import classes.Groupe;

@SuppressWarnings("serial")
public class RenduJTree extends DefaultTreeCellRenderer {
	ImageIcon groupeIcon = new ImageIcon(RenduJTree.class.getResource("/imgs/groupIcon.png"));
	ImageIcon discussionIcon = new ImageIcon(RenduJTree.class.getResource("/imgs/mailIcon.jpg"));
	Font normal = getFont();
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		Font gras = new Font(getFont().getFontName(), Font.BOLD, getFont().getSize());
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node != null) {
			if (node.getUserObject() != null) {
				if (node.getUserObject() instanceof Groupe) {
					Image i = groupeIcon.getImage().getScaledInstance(30, -1, Image.SCALE_AREA_AVERAGING);
					setIcon(new ImageIcon(i));
				} else if (node.getUserObject() instanceof FilDeDiscussion){
					FilDeDiscussion fddTemp = (FilDeDiscussion) node.getUserObject();
					if (fddTemp.getMessageNonLu() > 0)
						setFont(gras);
					else
						setFont(normal);

					Image i = discussionIcon.getImage().getScaledInstance(20, -1, Image.SCALE_DEFAULT);
					setIcon(new ImageIcon(i));
				}
			}
		}
		return this;
	}

}
