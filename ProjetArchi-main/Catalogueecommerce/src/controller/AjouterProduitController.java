package controller;

import dao.ProductFactoryDAO;
import dao.I_ProduitDAO;
import dao.ProduitOracleDAO;
import entite.I_Catalogue;
import entite.I_Produit;
import entite.Produit;

public class AjouterProduitController
{

	private I_ProduitDAO produit_dao;
	public AjouterProduitController(I_ProduitDAO produit_dao)
	{
		this.produit_dao = produit_dao;
	}
	
	public boolean ajouterProduit(String nom_produit,float prix_ht_produit,int quantite_en_stock_produit,I_Catalogue catalogue)
	{
		I_Produit produit_a_ajouter = new Produit(nom_produit,prix_ht_produit,quantite_en_stock_produit);
		produit_dao.createProduit(produit_a_ajouter);
		
		return catalogue.addProduit(produit_a_ajouter);
	}
}
