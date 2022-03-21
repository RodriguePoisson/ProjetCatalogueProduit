package dao;

import java.util.List;

import entite.I_Produit;
import entite.Produit;

public class AdaptaterXmlDAO implements I_ProduitDAO
{
	private ProduitDAO_XML dao_xml;
	public AdaptaterXmlDAO()
	{
		this.dao_xml = new ProduitDAO_XML();
	}

	@Override
	public void createProduit(I_Produit produit) 
	{
		this.dao_xml.creer(produit);
		
	}
	// Je me suis rendu compte ici que mon nomProduit ne servait à rien
	@Override
	public void modifyProduit(String nomProduit, I_Produit produit)
	{
		this.dao_xml.maj(produit);
		
	}

	@Override
	public void deleteProduit(String nomProduit) 
	{
		this.dao_xml.supprimer(new Produit(nomProduit,0,0));
		
	}

	@Override
	public I_Produit getProduit(String nomProduit)
	{
		
		return this.dao_xml.lire(nomProduit);
	}

	@Override
	public List<I_Produit> getAllProduits() 
	{
		return this.dao_xml.lireTous();
	}
}
