package entite;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Catalogue extends ProduitAjoutableEtSupressable implements I_Catalogue
{

	private final DecimalFormat df = new DecimalFormat("0.00");
	private String name;
	
	public Catalogue(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) 
	{
		for (I_Produit i_Produit : super.getListProduit()) 
		{
			if(i_Produit.getNom().equals(nomProduit))
			{
				if(qteAchetee>0)
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) 
	{
		for (I_Produit i_Produit :  super.getListProduit()) 
		{
			if(i_Produit.getNom().equals(nomProduit))
			{
				if(qteVendue<=i_Produit.getQuantite() && qteVendue >0)
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public double getMontantTotalTTC()
	{
		double prixTotal = 0;
		for (I_Produit i_Produit :  super.getListProduit()) 
		{
			
			prixTotal+= i_Produit.getPrixStockTTC();
		}
		return roundPrice(prixTotal);
	}
	
	private boolean verificationProduitConforme(I_Produit produit)
	{
		return !produitNul(produit) && !isIn(produit) && !produitStockNegatif(produit) && !produitPrixNul(produit);
	}
	
	private boolean produitNul(I_Produit produit)
	{
		if(Objects.isNull(produit))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean produitStockNegatif(I_Produit produit)
	{
		return produit.getQuantite()<0;
	}
	
	private boolean isIn(I_Produit produit)
	{
		for(I_Produit p : super.getListProduit())
		{
			if(produit.getNom().equals(p.getNom()))
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean produitPrixNul(I_Produit produit)
	{
		return produit.getPrixUnitaireHT()<=0;
	}
	
	private void traitementNameProduit(I_Produit produit)
	{
		if(!produitNul(produit))
		{
			produit.setNom(produit.getNom().trim());
			produit.setNom(produit.getNom().replaceAll("\\s+", " "));

		}
	}
	
	private void traitementPriceProduit(I_Produit produit)
	{
		if(!produitNul(produit))
		{
			double priceProduit = produit.getPrixUnitaireHT();
			produit.setPrixUnitaireHT(roundPrice(priceProduit));
			
		}
	}
	
	private double roundPrice(double price)
	{
		String prixTotalFromatString = df.format(price);
		prixTotalFromatString = prixTotalFromatString.replace(",", ".");
		return Double.parseDouble(prixTotalFromatString);
	}
	
	@Override
	public String toString()
	{
		String catalogueString = "Catalogue : "+this.name + "\n"+"\n";
		for (I_Produit i_Produit :  super.getListProduit()) 
		{
			catalogueString+= i_Produit.toString() + "\n";
		}
		catalogueString+= "\n" + "Montant total TTC du stock : "+df.format(this.getMontantTotalTTC())+" €";
		return catalogueString;
	}
	@Override
	public String getName() 
	{
		
		return this.name;
	}
	
	

}
