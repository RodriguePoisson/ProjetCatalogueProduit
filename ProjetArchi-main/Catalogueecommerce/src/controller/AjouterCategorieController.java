package controller;

import dao.I_CategorieDAO;

public class AjouterCategorieController 
{

	private I_CategorieDAO categorie_dao;
	public AjouterCategorieController(I_CategorieDAO categorie_dao) 
	{
		this.categorie_dao = categorie_dao;
	}
	
	public void ajouterCategorie(String categorie_name, int taxe_categorie)
	{
		this.categorie_dao.ajouterCategorie(categorie_name, taxe_categorie);
	}

}
