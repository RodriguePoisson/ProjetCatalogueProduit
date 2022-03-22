package dao;

public interface I_FactoryDAO 
{
	public abstract  I_ProduitDAO createProductDAO(String catalogue_name);
	public abstract  I_CatalogueDAO createCatalogueDAO();
	public abstract I_CategorieDAO createCategorieDAO();
	
}
