package dao;

import java.util.List;

import entite.I_Produit;

public interface I_ProduitDAO 
{
	public abstract void createProduit(I_Produit produit);
	public abstract void modifyProduit(String nomProduit,I_Produit produit);
	public abstract void deleteProduit(String nomProduit);
	public abstract I_Produit getProduit(String nomProduit);
	public abstract List<I_Produit> getAllProduits();
}
