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

public class ProduitOracleDAO implements I_ProduitDAO 
{
	private Connection cn;
	private String catalogue_name;
	public ProduitOracleDAO(Connection cn, String catalogue_name)
	{
		this.catalogue_name = catalogue_name;
		this.cn = cn;
	}

	@Override
	public void createProduit(I_Produit produit)
	{
		try 
		{
			CallableStatement cs = cn.prepareCall("CALL(add_produit_in_catalogue(?,?,?,?))");
			cs.setString(1, produit.getNom());
			cs.setDouble(2, produit.getPrixUnitaireHT());
			cs.setInt(3, produit.getQuantite());
			cs.setString(4, this.catalogue_name);
			
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
		String sql = "UPDATE produit_catalogue SET prixUnitaireHTProduit = ?,quantiteProduit = ? WHERE id_produit IN (SELECT codeProduit\n"
				+ "FROM Produits\n"
				+ "JOIN produit_catalogue ON produit_catalogue.id_produit = codeProduit\n"
				+ "JOIN Catalogues ON Catalogues.catalogue_id = id_catalogue\n"
				+ "WHERE catalogue_nom = ? AND nomProduit = ?)";
		try {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setDouble(1, produit.getPrixUnitaireHT());
			ps.setInt(2, produit.getQuantite());
			ps.setString(3, this.catalogue_name);
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
			PreparedStatement ps = cn.prepareStatement("DELETE produit_catalogue WHERE id_produit IN(\n"
					+ "SELECT codeProduit\n"
					+ "FROM Produits\n"
					+ "JOIN produit_catalogue ON produit_catalogue.id_produit = codeProduit\n"
					+ "WHERE nomProduit = ?) \n"
					+ "AND id_catalogue IN (\n"
					+ "SELECT catalogue_id\n"
					+ "FROM Catalogues\n"
					+ "JOIN produit_catalogue ON produit_catalogue.id_catalogue = catalogue_id\n"
					+ "WHERE catalogue_nom = ?)");
			
			ps.setString(1, nomProduit);
			ps.setString(2, this.catalogue_name);
			
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
		System.out.println(this.catalogue_name);
		List <I_Produit> list_produit = new ArrayList<I_Produit>();
		try {
			String sql = "SELECT nomProduit,prixUnitaireHTProduit,quantiteProduit \n"
					+ "FROM Produits produit\n"
					+ "JOIN produit_catalogue produit_in_cata ON produit_in_cata .id_produit = produit.codeProduit \n"
					+ "JOIN Catalogues catalogue ON catalogue.catalogue_id = produit_in_cata .id_catalogue\n"
					+ "WHERE catalogue.catalogue_nom= ?";
			PreparedStatement prepared =cn.prepareStatement(sql);
			prepared.setString(1, this.catalogue_name);
			ResultSet rs = prepared.executeQuery();
			
			while(rs.next())
			{
				String nomProduit = rs.getString(1);
				System.out.println(nomProduit);
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
