package loginScreen;

import interfaceUtilisateur.MenuBarInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrameLogin extends JFrame{
	//Attributs
	private JPanel mainPanel = new javax.swing.JPanel();
	private PanelPhoto panelPhoto = new PanelPhoto();
	private PanelLogin panelLogin = new PanelLogin();
	private MenuBarInterface frameInterface;
	
	//Constructor
	public FrameLogin(MenuBarInterface frameInterface){
		this.frameInterface = frameInterface;
		initcomponent();
	}
	
	
	public void initcomponent(){
		//Inits
		panelLogin.setParent(frameInterface);
        mainPanel.setBackground(new java.awt.Color(182, 182, 182));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //Events
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               frameInterface.setFrameLoginOpened(false);
               dispose();
            }
        });
        
		//Layout
		 javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
	        mainPanel.setLayout(mainPanelLayout);
	        mainPanelLayout.setHorizontalGroup(
	            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(mainPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(panelPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        mainPanelLayout.setVerticalGroup(
	            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(panelPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );

	        pack();
	}
	
	//Events
	
	//Others

}
