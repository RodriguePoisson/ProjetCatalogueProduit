package dao;

import java.util.List;

import entite.I_Catalogue;

public interface I_CatalogueDAO 
{
	public abstract boolean ajouterCatalogue(String nom_catalogue);
	public abstract void supprimerCatalogue(String nom_catalogue);
	public abstract List<I_Catalogue> getAllCatalogues();
}
