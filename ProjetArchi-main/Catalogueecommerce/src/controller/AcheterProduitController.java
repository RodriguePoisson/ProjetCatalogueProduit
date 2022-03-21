package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dao.I_ProduitDAO;
import entite.I_Catalogue;
import entite.I_Produit;

public class AcheterProduitController
{
	private I_ProduitDAO produit_dao;
	public AcheterProduitController(I_ProduitDAO produit_dao)
	{
		this.produit_dao = produit_dao;
	}
	
	public void acheterProduit(String nom_produit,int quantite,I_Catalogue catalogue)
	{
			
		I_Produit produit = catalogue.getProduitByName(nom_produit);
		produit.setQuantite(produit.getQuantite()+quantite);
		this.produit_dao.modifyProduit(nom_produit, produit);
		
	}
}
