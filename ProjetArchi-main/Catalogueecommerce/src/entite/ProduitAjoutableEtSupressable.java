package entite;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ProduitAjoutableEtSupressable implements I_ProduitAjoutableEtSupressable
{
	private List <I_Produit> list_produits;
	private final DecimalFormat df = new DecimalFormat("0.00");
	public ProduitAjoutableEtSupressable()
	{
		list_produits = new ArrayList<I_Produit>() ;
	}

	@Override
	public boolean addProduit(I_Produit produit) 
	{
		traitementNameProduit(produit);
		traitementPriceProduit(produit);
		if(this.verificationProduitConforme(produit))
		{
			
			list_produits.add(produit);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte,int taxe) 
	{
		return addProduit(new Produit(nom,prix,qte,taxe));
	}

	@Override
	public int addProduits(List<I_Produit> l) 
	{
		int compteur_produit_ajoute = 0;
		if(!Objects.isNull(l))
		{
			for(I_Produit produit : l)
			{
				if(this.addProduit(produit))
				{
					compteur_produit_ajoute++;
				}
			}
			// TODO Auto-generated method stub
		}
		return compteur_produit_ajoute;
	}

	@Override
	public boolean removeProduit(String nom) 
	{
		for (Iterator<I_Produit> iterator = list_produits.iterator(); iterator.hasNext();)
		{
			I_Produit i_Produit = (I_Produit) iterator.next();
			if(i_Produit.getNom().equals(nom))
			{
				iterator.remove();
				return true;
			}
			
		}
		return false;
	}

	@Override
	public List<I_Produit> getListProduit() 
	{
		return this.list_produits;
	}

	@Override
	public String[] getNomProduits()
	{
		String[] liste_nom_produits = new String[list_produits.size()];
		int compteur_produit = 0;
		for (I_Produit i_Produit : list_produits) 
		{
			liste_nom_produits[compteur_produit] = i_Produit.getNom();
			compteur_produit++;
		}
		Arrays.sort(liste_nom_produits);
		return liste_nom_produits;
	}

	@Override
	public I_Produit getProduitByName(String name) 
	{
		for(I_Produit produit : this.list_produits)
		{
			if(produit.getNom().equals(name)) return produit;
		}
		return null;
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
		for(I_Produit p : this.list_produits)
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

	@Override
	public void clear() {
		list_produits.clear();
		
	}

}
