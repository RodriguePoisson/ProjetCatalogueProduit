package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import controller.CentralController;

// J'ai prit la liberté de faire l'énnoncé d'une autre manière que je préfère, je vois les détails des catalogues quand je les sélectionnes et pas le détail de tous les catalogues je trouve ça plus pertinent.
// REMARQUE : les produits ne sont chargés que lorsqu'on séléctionne le catalogue qu'on veut
public class FenetreAccueil extends JFrame implements ActionListener {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;
	private CentralController centralController;
	private String[] tab_nom_catalogues;
	private boolean est_ajout_ou_suppression = false;

	public FenetreAccueil() {
		this.centralController = new CentralController();
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);
		
		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);
		
		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);
		
		panGestionCatalogues.setLayout (new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");
		
		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");
				
		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

		btAjouter.addActionListener(this);
		btSupprimer.addActionListener(this);
		btSelectionner.addActionListener(this);
		cmbSelectionner.addActionListener(this);
		
		this.tab_nom_catalogues  = this.centralController.getNomCatalogues();
		modifierListesCatalogues(this.tab_nom_catalogues);
		String[] tab2 = this.centralController.CatalogueToString().split("\n");
		modifierDetailCatalogues(tab2);
		modifierNbCatalogues(this.centralController.getNomCatalogues().length);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter)
		{
			this.est_ajout_ou_suppression = true;
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals(""))
			{

				if(!this.centralController.ajouterCatalogue(texteAjout))
				{
					JOptionPane.showMessageDialog(null, "Ce catalogue existe déjà","erreur",JOptionPane.ERROR_MESSAGE);
				}
				else
				{					
					modifierListesCatalogues(this.centralController.getNomCatalogues());
				}
				txtAjouter.setText(null);
			}
		}
		if (e.getSource() == btSupprimer)
		{
			this.est_ajout_ou_suppression =true;
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null)
			{
				this.centralController.suprimerCatalogue(texteSupprime);
				modifierListesCatalogues(this.centralController.getNomCatalogues());
			}
				
		}
		if (e.getSource() == btSelectionner)
		{
			String texteSelection = cmbSelectionner.getSelectedItem().toString();
			if (texteSelection != null) 
			{
				new FenetrePrincipale(texteSelection);
				this.dispose();
			}
		}
		if(e.getSource() == cmbSelectionner)
		{
			if(!this.est_ajout_ou_suppression)
			{
				this.centralController = new CentralController(cmbSelectionner.getSelectedItem().toString());
				modifierDetailCatalogues(this.centralController.CatalogueToString().split("\n"));
			}
			
		}
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		Arrays.sort(nomsCatalogues);
		if (nomsCatalogues != null)
			for (int i=0 ; i<nomsCatalogues.length; i++)
			{
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
		modifierNbCatalogues(nomsCatalogues.length);
		this.est_ajout_ou_suppression = false;
	}
	
	private void modifierNbCatalogues(int nb)
	{
		lbNbCatalogues.setText(nb + " catalogues");
	}
	
	private void modifierDetailCatalogues(String[] detailCatalogues) {
		taDetailCatalogues.setText("");
		if (detailCatalogues != null) {
			for (int i=0 ; i<detailCatalogues.length; i++)
				taDetailCatalogues.append(detailCatalogues[i]+"\n");
		}
	}
	
	public static void main(String[] args) {
		new FenetreAccueil();
	}
	
	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		System.exit(0);
	}
	
}
