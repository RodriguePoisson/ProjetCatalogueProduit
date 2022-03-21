package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.CentralController;
import entite.I_Produit;



public class FenetrePrincipale extends JFrame implements ActionListener,
		WindowListener {

	private JButton btAfficher;
	private JButton btNouveauProduit;
	private JButton btSupprimerProduit;
//	private JButton btNouvelleCategorie;
//	private JButton btSupprimerCategorie;
	private JButton btAchat;
	private JButton btVente;
	private JButton btQuitter;
	private JButton btRetour;
	private CentralController centralController;

	
	public FenetrePrincipale(String catalogue_name)
	{
		centralController = new CentralController(catalogue_name);
		setTitle("Catalogue : "+catalogue_name);
		setBounds(500, 500, 320, 250);
		JPanel panAffichage = new JPanel();
		JPanel panNouveauSupprimerProduit = new JPanel();
//		JPanel panNouveauSupprimerCategorie = new JPanel();
		JPanel panAchatVente = new JPanel();
		JPanel panQuitter = new JPanel();
		JPanel panRetour = new JPanel();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAfficher = new JButton("Quantité en stock");
		btNouveauProduit = new JButton("Nouveau Produit");
		btSupprimerProduit = new JButton("Supprimer Produit");
//		btNouvelleCategorie = new JButton("Nouvelle Categorie");
//		btSupprimerCategorie = new JButton("Supprimer Categorie");
		btAchat = new JButton("Achat Produits");
		btVente = new JButton("Vente Produits");
		btQuitter = new JButton("Quitter");
		btRetour = new JButton("Retour au choix de catalogues");
		panAffichage.add(btAfficher);
		panNouveauSupprimerProduit.add(btNouveauProduit); 
		panNouveauSupprimerProduit.add(btSupprimerProduit);
//		panNouveauSupprimerCategorie.add(btNouvelleCategorie); 
//		panNouveauSupprimerCategorie.add(btSupprimerCategorie);
		panAchatVente.add(btAchat); 
		panAchatVente.add(btVente);  
		panQuitter.add(btQuitter);
		panRetour.add(btRetour);

		contentPane.add(panAffichage);
//		contentPane.add(panNouveauSupprimerCategorie);
		contentPane.add(panNouveauSupprimerProduit);
		contentPane.add(panAchatVente);
		contentPane.add(panRetour);
		contentPane.add(panQuitter);
	
	

		btAfficher.addActionListener(this);
		btNouveauProduit.addActionListener(this);
		btSupprimerProduit.addActionListener(this);
//		btNouvelleCategorie.addActionListener(this);
//		btSupprimerCategorie.addActionListener(this);
		btAchat.addActionListener(this);
		btVente.addActionListener(this);
		btQuitter.addActionListener(this);
		btRetour.addActionListener(this);
		
		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String[] lesProduits = this.centralController.getNomProduitsFromCatalogue();

/* Mï¿½me chose pour tabCategories (partie 4) */ 		
//		String[] tabCategories = new String[] {"Bio", "Luxe" };
		
		if (e.getSource() == btAfficher)
			new FenetreAffichage(this.centralController);
		if (e.getSource() == btNouveauProduit)
//			new FenetreNouveauProduit(tabCategories);
			new FenetreNouveauProduit(this.centralController);
		if (e.getSource() == btSupprimerProduit)
		{
			
			new FenetreSuppressionProduit(lesProduits,this.centralController);
		}
//		if (e.getSource() == btNouvelleCategorie)
//			new FenetreNouvelleCategorie();
//		if (e.getSource() == btSupprimerCategorie)
//			new FenetreSuppressionCategorie(tabCategories);
		if (e.getSource() == btAchat)
			new FenetreAchat(lesProduits,this.centralController);
		if (e.getSource() == btVente)
			new FenetreVente(lesProduits,this.centralController);
		if (e.getSource() == btQuitter){
			System.out.println("Au revoir");
			System.exit(0);
		}
		if(e.getSource() == btRetour)
		{
			new FenetreAccueil();
			dispose();
		}
	}

	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
