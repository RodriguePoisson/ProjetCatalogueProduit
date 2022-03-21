package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import entite.I_Produit;
import entite.Produit;

class TestProduitDAO 
{
	
	@Test
	void testGetNomProduitExiste() 
	{
		I_ProduitDAO produitDAO= new ProduitDAO();
		I_Produit produit1 = new Produit("blaz",4,4);
		
		I_Produit produit2 = produitDAO.getProduit("blaz");
		
		assertEquals(produit1.toString(),produit2.toString());
	}
	@Test
	void testGetNomProduitExistePas() 
	{
		I_ProduitDAO produitDAO= new ProduitDAO();
		I_Produit produit1 = new Produit("Marse",10,5);
		
		I_Produit produit2 = produitDAO.getProduit("Marse");
		
		assertEquals(null,produit2);
	}
	
	/*@Test
	void addProduitAndGet()
	{
		I_ProduitDAO produitDAO= new ProduitDAO();
		I_Produit produit1 = new Produit("blaa",4.4,4);
		produitDAO.createProduit(produit1);
		
		I_Produit produit2 = produitDAO.getProduit("blaa");
		
		assertEquals(produit1.toString(),produit2.toString());
	}*/
	
	@Test
	void ModifyProductBlaaAndGetIt()
	{
		I_ProduitDAO produitDAO= new ProduitDAO();
		I_Produit produit1 = new Produit("blaa",5.65,90);
		
		produitDAO.modifyProduit("blaa",produit1);
		
		I_Produit produit2 = produitDAO.getProduit("blaa");
		
		assertEquals(produit1.toString(),produit2.toString());
	}
}
