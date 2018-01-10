package ihm;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

@SuppressWarnings("serial")
public class FrameFirstMessage extends JFrame{
	//Attributs
    private JPanel mainPanel = new javax.swing.JPanel();
    private PanelSelectionGroupes panelSelectionGroupe;
    private PanelFirstMessage panelFirstMessage = new PanelFirstMessage();
    private JTextField textFieldTitre = new javax.swing.JTextField();
    private JLabel labelTitre = new javax.swing.JLabel("Titre:");
    private FrameInterface frameInterface;
    private JTree arbreFilDeDiscussion;

    //Constructor
    public FrameFirstMessage(FrameInterface frameInterface, JTree arbreFilDeDiscussion){
    	this.frameInterface = frameInterface;
    	this.arbreFilDeDiscussion = arbreFilDeDiscussion;
		initcomponent();
    }
    
	public void initcomponent(){
		//Inits
		panelSelectionGroupe = new PanelSelectionGroupes(frameInterface);
		panelFirstMessage.setParent(frameInterface);
    	panelFirstMessage.setArbreFilDeDiscussion(arbreFilDeDiscussion);
    	panelFirstMessage.setTextFieldTitre(textFieldTitre);
    	panelFirstMessage.setPanelSelectionGroupes(panelSelectionGroupe);
		setMinimumSize(new Dimension(450, 250));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getRootPane().setDefaultButton(panelFirstMessage.getOkButton());
		
		//Events
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               frameInterface.getMenuBarInterface().setFrameFirstMessageOpened(false);
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
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFirstMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSelectionGroupe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(labelTitre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldTitre)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTitre))
                .addGap(18, 18, 18)
                .addComponent(panelSelectionGroupe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelFirstMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    public void setFrameInterface(FrameInterface frameInterface) {
		this.frameInterface = frameInterface;
	}
	
	public void setArbreFilDiscussion(JTree arbreFilDiscussion) {
		this.arbreFilDeDiscussion = arbreFilDiscussion;
	}
	
}
