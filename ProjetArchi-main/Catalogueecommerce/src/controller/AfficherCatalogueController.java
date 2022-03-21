package controller;

import dao.I_ProduitDAO;
import entite.I_Catalogue;

public class AfficherCatalogueController
{

	public AfficherCatalogueController(I_ProduitDAO produit_dao) 
	{

	}



	public String catalogue_to_string(I_Catalogue catalogue) 
	{
		return catalogue.toString();
	}

}
