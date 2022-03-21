package entite;

import java.text.DecimalFormat;

public class Produit implements I_Produit 
{
	private String nom;
	private double prixHt;
	private int quantite;
	private final double TAUX_TVA = 0.2;
	private final DecimalFormat df = new DecimalFormat("#.00");
	public Produit(String nom,double prixHt,int quantite)
	{
		this.nom = nom;
		this.prixHt = prixHt;
		this.quantite = quantite;
	}

	@Override
	public boolean ajouter(int qteAchetee) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enlever(int qteVendue)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNom() 
	{
		
		return this.nom; 
	}
	
	@Override
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	@Override
	public int getQuantite()
	{
		
		return this.quantite;
	}

	@Override
	public double getPrixUnitaireHT()
	{
		
		return this.prixHt;
	}
	
	@Override
	public void setPrixUnitaireHT(double prixHT)
	{
		this.prixHt = prixHT;
	}

	@Override
	public double getPrixUnitaireTTC() 
	{
		
		return this.getPrixUnitaireHT() *(1+TAUX_TVA);
	}

	@Override
	public double getPrixStockTTC() 
	{
		return this.getPrixUnitaireTTC()*this.getQuantite();
	}
	
	@Override
	public String toString()
	{
		return this.getNom()+" - prix HT : "+ df.format(this.getPrixUnitaireHT())+" € - prix TTC : "+df.format(this.getPrixUnitaireTTC())+" € - quantité en stock : "+String.valueOf(this.getQuantite());
	}

	@Override
	public boolean setQuantite(int quantite) 
	{
		if(quantite>=0) 
		{
			this.quantite = quantite;
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
