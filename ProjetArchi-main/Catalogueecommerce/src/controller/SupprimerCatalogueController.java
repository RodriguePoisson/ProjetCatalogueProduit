package controller;

import dao.I_CatalogueDAO;
import dao.I_ProduitDAO;

public class SupprimerCatalogueController
{
	private I_CatalogueDAO catalogueDAO;
	public SupprimerCatalogueController(I_CatalogueDAO catalogue_dao)
	{
		this.catalogueDAO =catalogue_dao;
	}
	public void supprimer_catalogue(String nom_catalogue)
	{
		this.catalogueDAO.supprimerCatalogue(nom_catalogue);
	}
	

}
