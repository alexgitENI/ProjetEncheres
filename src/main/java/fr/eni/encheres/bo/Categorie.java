package fr.eni.encheres.bo;



public class Categorie {
	
	private int noCategorie;
	private String libelle;
	

	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
