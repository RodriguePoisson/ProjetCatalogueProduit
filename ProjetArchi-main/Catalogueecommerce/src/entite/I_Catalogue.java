package entite;

import java.util.List; 

public interface I_Catalogue extends I_ProduitAjoutableEtSupressable
{

	public abstract boolean acheterStock(String nomProduit, int qteAchetee);
	public abstract boolean vendreStock(String nomProduit, int qteVendue);
	public abstract double getMontantTotalTTC();
	public abstract String toString();
	public abstract String getName();

	public abstract void clear();

}