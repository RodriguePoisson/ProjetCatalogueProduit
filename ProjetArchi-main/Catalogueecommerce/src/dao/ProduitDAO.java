package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entite.I_Produit;
import entite.Produit;
import oracleBD.Oracle;

public class ProduitDAO implements I_ProduitDAO 
{
	private Connection cn;
	public ProduitDAO()
	{
		cn = Oracle.getCn();
	}

	@Override
	public void createProduit(I_Produit produit)
	{
		try 
		{
			CallableStatement cs = cn.prepareCall("CALL(add_produit(?,?,?))");
			cs.setString(1, produit.getNom());
			cs.setDouble(2, produit.getPrixUnitaireHT());
			cs.setInt(3, produit.getQuantite());
			
			cs.executeQuery();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifyProduit(String nomProduit,I_Produit produit) 
	{
		String sql = "UPDATE Produits SET nomProduit = ?, prixUnitaireHTProduit = ?,quantiteProduit = ? WHERE nomProduit = ?";
		try {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, produit.getNom());
			ps.setDouble(2, produit.getPrixUnitaireHT());
			ps.setInt(3, produit.getQuantite());
			ps.setString(4, nomProduit);
			
			ps.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduit(String nomProduit)
	{
		try {
			PreparedStatement ps = cn.prepareStatement("DELETE Produits WHERE nomProduit = ?");
			
			ps.setString(1, nomProduit);
			
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}

	@Override
	public I_Produit getProduit(String nomProduit)
	{
		String sql = "SELECT nomProduit,quantiteProduit,prixUnitaireHTProduit FROM Produits WHERE nomProduit = ?";
		
		I_Produit produit=null;
		try 
		{
			PreparedStatement prepared =cn.prepareStatement(sql);
			prepared.setString(1, nomProduit);
			ResultSet rs = prepared.executeQuery();
		
			rs.next();
			
			String nom = rs.getString(1);
			double prixHT = rs.getDouble(3);
			int quantite = rs.getInt(2);
			
		
			produit = new Produit(nom,prixHT,quantite);
			
		} catch (SQLException e) 
		{
			return null;
			
		}
		return produit;
	}

	@Override
	public List<I_Produit> getAllProduits() 
	{
		List <I_Produit> list_produit = new ArrayList<I_Produit>();
		try {
			Statement cs = cn.createStatement();
			ResultSet rs = cs.executeQuery("SELECT nomProduit,prixUnitaireHTProduit,quantiteProduit FROM Produits");
			
			while(rs.next())
			{
				String nomProduit = rs.getString(1);
				double prixHT = rs.getDouble(2);
				int quantite = rs.getInt(3);
				
				list_produit.add(new Produit(nomProduit,prixHT,quantite));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list_produit;
	}

}
