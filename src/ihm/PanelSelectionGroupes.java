package ihm;

import java.awt.event.KeyEvent;

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
	private DefaultListModel<Groupe> lmRef = new DefaultListModel<>();
    
	//Constructor
    public PanelSelectionGroupes(){
    	initcomponent();
    }
    
	public void initcomponent(){
		//Inits
	
			
		scrollPanelGroupes.setViewportView(listGroupes);
		test();
		//Events
        textFieldRechercher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            	searchbar(evt);
            }
        });
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
    	recherche();
    }   
    
    private void searchbar(KeyEvent evt){
    	recherche();
    }
	//Others
    private void recherche(){
    	DefaultListModel<Groupe> newModel = new DefaultListModel<>();
    	CharSequence cs = textFieldRechercher.getText().toLowerCase();
    	for (int i = 0; i < lmRef.getSize(); i++){
    		String temp = lmRef.getElementAt(i).toString().toLowerCase();
    		if (cs != null)
    			if (temp.contains(cs))
    				newModel.addElement(lmRef.getElementAt(i));
    	}
    	listGroupes.setModel(newModel);
    	listGroupes.repaint();
    }
    
    
    public void test (){
    	
    	lmRef.addElement(new Groupe("TAD 3.1"));
    	lmRef.addElement(new Groupe("TAD 3.2"));
    	lmRef.addElement(new Groupe("TAD 4.1"));
    	lmRef.addElement(new Groupe("TAD 4.2"));
    	listGroupes.setModel(lmRef);
    	listGroupes.setSelectedIndex(0);
    }

	public JList<Groupe> getListGroupes() {
		return listGroupes;
	}
	
    public Groupe getGroupeSelected(){
    	return listGroupes.getSelectedValue();
    }
}
