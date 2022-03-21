package entite;

public interface I_Produit {

	public abstract boolean ajouter(int qteAchetee);
	public abstract boolean enlever(int qteVendue);
	public abstract String getNom();
	public abstract void setNom(String nom);
	public abstract int getQuantite();
	public abstract boolean setQuantite(int quantite);
	public abstract double getPrixUnitaireHT();
	public abstract void setPrixUnitaireHT(double prixHT);
	public abstract double getPrixUnitaireTTC();
	public abstract double getPrixStockTTC();
	public abstract String toString();

}