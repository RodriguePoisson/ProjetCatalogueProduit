package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entite.Catalogue;
import entite.I_Catalogue;

public class CatalogueOracleDAO  implements I_CatalogueDAO
{
	private Connection cn;
	public CatalogueOracleDAO(Connection cn)
	{
		this.cn = cn;
	}
	@Override
	public boolean ajouterCatalogue(String nom_catalogue)
	{
		try
		{
			CallableStatement cs = cn.prepareCall("CALL(add_catalogue(?))");
			cs.setString(1, nom_catalogue);
			cs.executeQuery();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
		
		
	}
	@Override
	public void supprimerCatalogue(String nom_catalogue)
	{
		try {
			PreparedStatement ps = cn.prepareStatement("DELETE Catalogues WHERE catalogue_nom = ?");
			
			ps.setString(1, nom_catalogue);
			
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	@Override
	public List<I_Catalogue> getAllCatalogues() 
	{
		List <I_Catalogue> list_catalogue = new ArrayList<I_Catalogue>();
		try {
			String sql = "SELECT catalogue_nom FROM Catalogues";
			PreparedStatement prepared =cn.prepareStatement(sql);
			ResultSet rs = prepared.executeQuery();
			
			while(rs.next())
			{
				String nomCata = rs.getString(1);
				
				list_catalogue.add(new Catalogue(nomCata));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list_catalogue;
		
	}
}
