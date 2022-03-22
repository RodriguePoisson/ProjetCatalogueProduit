package entite;

import java.util.List;

public class Categorie extends ProduitAjoutableEtSupressable implements I_Categorie
{
	private String nom;
	private int taxe;
	
	public Categorie(String nom,int taxe)
	{
		this.taxe = taxe;
		this.nom = nom;
	}
	@Override
	public int getTaxe() 
	{
		return this.taxe;
	}

	@Override
	public String getName() 
	{
		return this.nom;
	}

}
