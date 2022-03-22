package dao;

import java.util.List;

import Exception.NotVideException;
import entite.I_Categorie;


public interface I_CategorieDAO 
{
	public abstract boolean ajouterCategorie(String nom_catalogue,int taxe);
	public abstract void supprimerCategorie(String nom_catalogue) throws NotVideException;
	public abstract List<I_Categorie> getAllCategories();
}
