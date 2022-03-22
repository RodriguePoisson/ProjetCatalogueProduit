package dao;

import java.sql.Connection;

public class OracleFactoryDAO implements I_FactoryDAO
{
	Connection cn;
	private String catalogue_name;
	public OracleFactoryDAO(Connection cn)
	{
		this.cn = cn;
	}
	@Override
	public I_ProduitDAO createProductDAO(String catalogue_name)
	{
		return new ProduitOracleDAO(this.cn,catalogue_name);
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() 
	{
		
		return new CatalogueOracleDAO(this.cn);
	}
	@Override
	public I_CategorieDAO createCategorieDAO()
	{
		return new CategorieOracleDAO(this.cn);
	}

}
