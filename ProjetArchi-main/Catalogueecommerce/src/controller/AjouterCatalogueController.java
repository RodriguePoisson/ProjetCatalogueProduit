package controller;

import dao.I_CatalogueDAO;

public class AjouterCatalogueController 
{
	private I_CatalogueDAO catalogueDAO;
	public AjouterCatalogueController(I_CatalogueDAO catalogue_dao)
	{
		this.catalogueDAO = catalogue_dao;
	}
	
	public boolean ajouterCatalogue(String nom_catalogue)
	{
		
		return this.catalogueDAO.ajouterCatalogue(nom_catalogue);
		 

	}
}
