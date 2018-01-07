package ihm;

import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utilisateur.Tube;
import utilisateur.Utilisateur;

@SuppressWarnings("serial")
public class FrameInterface extends JFrame{
	//Attributs
	private Utilisateur user;
	private JPanel mainPanel = new JPanel();
	private PanelFilDeDiscussion panelFil = new PanelFilDeDiscussion(this);
	private PanelTextFieldSend panelText = new PanelTextFieldSend(this);
	private PanelMessageDisplay panelMsg = new PanelMessageDisplay();
	private MenuBarInterface menuBar = new MenuBarInterface(this);
	private Tube tube;
	
	//Constructor
	public FrameInterface(){
		initcomponent();
	}
	
	public void initcomponent(){
		//Inits
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        setMinimumSize(new Dimension(500,400));
        setPreferredSize(new Dimension(1000,700));
        menuBar.setArbreFilDeDiscussion(panelFil.getFilsArbre());
        panelText.setArbreFilDeDiscussion(panelFil.getFilsArbre());
        panelText.setPanelMessageDisplay(panelMsg);
        panelFil.setPanelMessageDisplay(panelMsg);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //Events
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	try {
            		if (tube != null)
            			tube.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					dispose();
				}
            }
        });
        
        //Layout
        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(panelFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMsg)
                    .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelFil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setJMenuBar(menuBar);
        getContentPane().add(mainPanel);
        pack();
    }
	//Events
		
	//Others
	public MenuBarInterface getMenuBarInterface(){
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
	
}
