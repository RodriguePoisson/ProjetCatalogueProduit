package dao;

public class FactoryDAO
{
	public FactoryDAO()
	{
		
	}
	
	public static I_ProduitDAO getDAO()
	{
		return new AdaptaterXmlDAO();
	}
}
