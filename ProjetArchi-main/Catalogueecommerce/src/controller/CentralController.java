package controller;



import java.util.List;

import Exception.QuantiteException;

import dao.I_CatalogueDAO;
import dao.I_FactoryDAO;
import dao.I_ProduitDAO;
import dao.OracleFactoryDAO;
import entite.Catalogue;
import entite.I_Catalogue;
import oracleBD.Oracle;

public class CentralController 
{
	private I_Catalogue catalogue;
	private AjouterProduitController ajouter_produit_controller;
	private SupprimerProduitController supprimer_produit_controller;
	private AcheterProduitController acheter_produit_controller;
	private VendreProduitController vendre_produit_controller;
	private AfficherCatalogueController afficher_catalogue_controller;
	private SupprimerCatalogueController supprimer_catalogue_controller;
	private AjouterCatalogueController ajouter_catalogue_controller;
	private I_CatalogueDAO catalogue_dao;
	private I_ProduitDAO produit_dao;
	private I_FactoryDAO factoryDAO;
	public CentralController()
	{
		this.factoryDAO = new OracleFactoryDAO(Oracle.getCn());
		this.catalogue_dao = factoryDAO.createCatalogueDAO();
	}
	public CentralController(String catalogue_name)
	{
		
		this();
		this.catalogue = new Catalogue(catalogue_name);
		this.produit_dao = this.factoryDAO.createProductDAO(this.catalogue.getName());
		
		this.catalogue.addProduits(produit_dao.getAllProduits());
		this.ajouter_produit_controller = new AjouterProduitController(this.produit_dao);
		this.supprimer_produit_controller = new SupprimerProduitController(this.produit_dao);
		this.acheter_produit_controller = new AcheterProduitController(this.produit_dao);
		this.vendre_produit_controller = new VendreProduitController(this.produit_dao);
		this.afficher_catalogue_controller = new AfficherCatalogueController(this.produit_dao);
		this.ajouter_catalogue_controller = new AjouterCatalogueController(this.catalogue_dao);
		this.supprimer_catalogue_controller = new SupprimerCatalogueController(this.catalogue_dao);
	}
	
	public boolean ajouterProduit(String nom_produit,float prix_ht_produit,int quantite_en_stock)
	{
		return this.ajouter_produit_controller.ajouterProduit(nom_produit, prix_ht_produit, quantite_en_stock,this.catalogue);
	}
	
	public void supprimerProduit(String nomProduit)
	{
		this.supprimer_produit_controller.supprimerProduit(nomProduit,this.catalogue);
	}
	
	public void acheterProduit(String nom_produit, int quantite_achete)
	{
		this.acheter_produit_controller.acheterProduit(nom_produit, quantite_achete, this.catalogue);
	}
	
	public void vendreProduit(String nom_produit, int quantite_vendu) throws QuantiteException
	{
		this.vendre_produit_controller.vendreProduit(nom_produit, quantite_vendu, this.catalogue);
	}
	
	public String CatalogueToString()
	{
		return this.afficher_catalogue_controller.catalogue_to_string(this.catalogue);
	}
	
	public boolean ajouterCatalogue(String nom_catalogue)
	{
		return this.ajouter_catalogue_controller.ajouterCatalogue(nom_catalogue);
	}
	
	public void suprimerCatalogue(String nom_catalogue)
	{
		this.supprimer_catalogue_controller.supprimer_catalogue(nom_catalogue);
	}
	
	public String[] getNomProduitsFromCatalogue()
	{
		return this.catalogue.getNomProduits();
	}
	
	public String[] getNomCatalogues()
	{
		List<I_Catalogue> catalogues = this.catalogue_dao.getAllCatalogues();
		
		int compteur_catalogue = 0;
		String[] nom_catalogues = new String[catalogues.size()];
		for(I_Catalogue catalogue : catalogues)
		{
			nom_catalogues[compteur_catalogue] = catalogue.getName();
			compteur_catalogue++;
		}
		return nom_catalogues;
		
	}
}
