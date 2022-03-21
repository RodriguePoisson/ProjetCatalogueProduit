package controller;

import dao.I_ProduitDAO;
import dao.ProduitOracleDAO;
import entite.I_Catalogue;

public class SupprimerProduitController 
{
	private I_ProduitDAO produit_dao;
	public SupprimerProduitController(I_ProduitDAO produit_dao)
	{
		this.produit_dao = produit_dao;
	}
	
	public void supprimerProduit(String nom_produit,I_Catalogue catalogue)
	{
		this.produit_dao.deleteProduit(nom_produit);
		catalogue.removeProduit(nom_produit);
	}
}
