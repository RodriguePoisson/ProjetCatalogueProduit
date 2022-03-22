package entite;

import java.util.List;

public interface I_ProduitAjoutableEtSupressable 
{
	public abstract boolean addProduit(I_Produit produit);
	public abstract boolean addProduit(String nom, double prix, int qte,int taxe);
	public abstract int addProduits(List<I_Produit> l);
	public abstract boolean removeProduit(String nom);
	public abstract List<I_Produit> getListProduit();
	public abstract String[] getNomProduits();
	public abstract I_Produit getProduitByName(String name);
	public abstract void clear();
}
