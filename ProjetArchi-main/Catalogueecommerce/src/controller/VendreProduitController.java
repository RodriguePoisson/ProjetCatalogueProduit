package controller;

import Exception.QuantiteException;
import dao.I_ProduitDAO;
import entite.I_Catalogue;
import entite.I_Produit;

public class VendreProduitController 
{
	private I_ProduitDAO produit_dao;
	public VendreProduitController(I_ProduitDAO produit_dao)
	{
		this.produit_dao = produit_dao;
	}
	
	public void vendreProduit(String nom_produit,int quantite,I_Catalogue catalogue) throws QuantiteException
	{
		I_Produit produit = catalogue.getProduitByName(nom_produit);
		int new_quantite = produit.getQuantite()-quantite;
		if(new_quantite<0)
		{
			throw(new QuantiteException("Nous n'avons pas assez de produit en stock, le produit a : "+produit.getQuantite()+" de stock"));
		}
		else
		{			
			produit.setQuantite(new_quantite);
			this.produit_dao.modifyProduit(nom_produit, produit);
		}
	}
}
