package controller;

import Exception.NotVideException;
import dao.I_CategorieDAO;

public class SupprimerCategorieController
{
	private I_CategorieDAO categorie_dao;
	public SupprimerCategorieController(I_CategorieDAO categorie_dao)
	{
		this.categorie_dao = categorie_dao;
	}
	
	public void supprimer_categorie(String nom_categorie) throws NotVideException
	{
		this.categorie_dao.supprimerCategorie(nom_categorie);
	}
}
