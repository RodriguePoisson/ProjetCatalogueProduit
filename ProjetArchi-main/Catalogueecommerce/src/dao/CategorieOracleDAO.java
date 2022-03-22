package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.NotVideException;
import entite.Catalogue;
import entite.Categorie;
import entite.I_Catalogue;
import entite.I_Categorie;

public class CategorieOracleDAO implements I_CategorieDAO
{
	private Connection cn;
	public CategorieOracleDAO(Connection cn)
	{
		this.cn = cn;
	}
	@Override
	public boolean ajouterCategorie(String nom_categorie,int taxe)
	{
		try
		{
			CallableStatement cs = cn.prepareCall("CALL(add_categorie(?,?))");
			cs.setString(1, nom_categorie);
			cs.setInt(2, taxe);
			cs.executeQuery();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}
	@Override
	public void supprimerCategorie(String nom_categorie) throws NotVideException
	{
		try {
			PreparedStatement ps = cn.prepareStatement("DELETE Categories WHERE nom_categorie = ?");
			
			ps.setString(1, nom_categorie);
			
			ps.executeQuery();
		} catch (SQLException e)
		{
			if(e.getErrorCode()==2292)
			{		
				throw(new NotVideException("La catégorie n'est pas vide vous ne pouvez pas la supprimer"));
			}
		};
		
	}
	@Override
	public List<I_Categorie> getAllCategories()
	{
		List <I_Categorie> list_categorie = new ArrayList<I_Categorie>();
		try {
			String sql = "SELECT nom_categorie,taxe_categorie FROM Categories";
			PreparedStatement prepared =cn.prepareStatement(sql);
			ResultSet rs = prepared.executeQuery();
			
			while(rs.next())
			{
				String nomCate = rs.getString(1);
				int taxe_cate = rs.getInt(2);
				
				list_categorie.add(new Categorie(nomCate,taxe_cate));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list_categorie;
	}
}
