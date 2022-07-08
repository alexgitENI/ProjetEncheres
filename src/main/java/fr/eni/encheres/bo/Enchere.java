package fr.eni.encheres.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enchere {

	private int noUtilisateur;
	private int noArticle;
	private LocalDateTime dateEnchere;
	private int montant;
	private Utilisateur encherisseur; 
	
	public final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	
	public Enchere(int noArticle, int montant) {
		this.noArticle = noArticle; 
		this.montant = montant; 
		
	}
	
	public Enchere(int noUtilisateur, int noArticle, LocalDateTime dateEnchere, int montant) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montant = montant;
	}
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getSTRDateEnchere() {
		String date = this.dateEnchere.format(FORMAT);
		return date;
	}
	public LocalDateTime getDateEnchere() {
		return this.dateEnchere ;
		
	}
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public Utilisateur getEncherisseur() {
		return encherisseur;
	}
	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}
}
