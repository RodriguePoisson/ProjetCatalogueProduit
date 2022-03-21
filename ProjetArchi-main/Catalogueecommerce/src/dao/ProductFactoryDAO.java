package dao;

public class ProductFactoryDAO
{
	public ProductFactoryDAO()
	{
		
	}
	
	public static I_ProduitDAO getDAO()
	{
		return new AdaptaterXmlDAO();
	}
}
