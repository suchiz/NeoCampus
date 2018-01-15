package ihm;

import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPhoto extends JPanel{
	//Attributs
	URL url = PanelPhoto.class.getResource("/imgs/login-icon.png");
	private JLabel icon = new JLabel();
	
	public PanelPhoto(){
		initcomponent();
	}

	public void initcomponent(){
		//Layout
        icon.setIcon(new javax.swing.ImageIcon(url)); // NOI18N

        javax.swing.GroupLayout panelPhotoLayout = new javax.swing.GroupLayout(this);
        setLayout(panelPhotoLayout);
        panelPhotoLayout.setHorizontalGroup(
            panelPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPhotoLayout.setVerticalGroup(
            panelPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );
	}
}
