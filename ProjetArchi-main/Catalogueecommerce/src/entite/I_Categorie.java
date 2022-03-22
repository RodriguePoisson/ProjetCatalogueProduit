package entite;

import java.util.List;

public interface I_Categorie extends I_ProduitAjoutableEtSupressable
{
	public abstract int getTaxe();
	public abstract String getName();

}
