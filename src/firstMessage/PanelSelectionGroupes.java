package firstMessage;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import utilisateur.Groupe;

@SuppressWarnings("serial")
public class PanelSelectionGroupes extends JPanel{
	//Attributs
	private JScrollPane scrollPanelGroupes = new javax.swing.JScrollPane();
	private JList<Groupe> listGroupes = new javax.swing.JList<>();
	private JTextField textFieldRechercher = new javax.swing.JTextField();
	private JButton buttonRechercher = new javax.swing.JButton("Rechercher");
    
	//Constructor
    public PanelSelectionGroupes(){
    	initcomponent();
    }
    
	public void initcomponent(){
		//Inits
		scrollPanelGroupes.setViewportView(listGroupes);
		test();
		//Events
        buttonRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRechercherActionPerformed(evt);
            }
        });
        
		//Layout
		javax.swing.GroupLayout panelSelectionGroupeLayout = new javax.swing.GroupLayout(this);
        setLayout(panelSelectionGroupeLayout);
        panelSelectionGroupeLayout.setHorizontalGroup(
            panelSelectionGroupeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanelGroupes)
            .addGroup(panelSelectionGroupeLayout.createSequentialGroup()
                .addComponent(textFieldRechercher)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRechercher))
        );
        panelSelectionGroupeLayout.setVerticalGroup(
            panelSelectionGroupeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSelectionGroupeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSelectionGroupeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldRechercher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRechercher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelGroupes, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
	}
	
	//Events
    private void buttonRechercherActionPerformed(java.awt.event.ActionEvent evt) {                                                 
       
    }    
	//Others
    
    public void test (){
    	DefaultListModel<Groupe> listModel = new DefaultListModel<>();
    	listModel.addElement(new Groupe("TAD 3.1"));
    	listModel.addElement(new Groupe("TAD 3.2"));
    	listModel.addElement(new Groupe("TAD 4.1"));
    	listModel.addElement(new Groupe("TAD 4.2"));
    	listGroupes.setModel(listModel);
    }

	public JList<Groupe> getListGroupes() {
		return listGroupes;
	}
	
    public Groupe getGroupeSelected(){
    	return listGroupes.getSelectedValue();
    }
}
