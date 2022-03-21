package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Exception.QuantiteException;
import dao.FactoryDAO;
import dao.I_ProduitDAO;
import dao.ProduitDAO;
import entite.Catalogue;
import entite.I_Catalogue;
import entite.I_Produit;

public class CentralController 
{
	private I_Catalogue catalogue;
	private I_ProduitDAO produit_dao;
	private AjouterProduitController ajouter_produit_controller;
	private SupprimerProduitController supprimer_produit_controller;
	private AcheterProduitController acheter_produit_controller;
	private VendreProduitController vendre_produit_controller;
	private AfficherCatalogueController afficher_catalogue_controller;
	public CentralController()
	{
		this.catalogue = new Catalogue();
		this.produit_dao = FactoryDAO.getDAO();
		this.catalogue.addProduits(produit_dao.getAllProduits());
		this.ajouter_produit_controller = new AjouterProduitController(this.produit_dao);
		this.supprimer_produit_controller = new SupprimerProduitController(this.produit_dao);
		this.acheter_produit_controller = new AcheterProduitController(this.produit_dao);
		this.vendre_produit_controller = new VendreProduitController(this.produit_dao);
		this.afficher_catalogue_controller = new AfficherCatalogueController(this.produit_dao);
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
	
	public String[] getNomProduitsFromCatalogue()
	{
		return this.catalogue.getNomProduits();
	}
	
}
