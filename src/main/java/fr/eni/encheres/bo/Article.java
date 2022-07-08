package fr.eni.encheres.bo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEnchere;
	private LocalDateTime dateFinEnchere;
	private int prixInitial;
	private int prixVente;
	private int noVendeur;
	private int noCategorie;
	private int noAcheteur;
	private String nomVendeur;
	private Utilisateur Vendeur;
	private Categorie catagorie; 
	private Enchere enchere; 
	private Retrait retrait; 



	public final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	/**
	 *   LocalDate date = LocalDate.now();
  String text = date.format(formatter);
  LocalDate parsedDate = LocalDate.parse(text, formatter);

	 */

	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEnchere=" + dateDebutEnchere + ", dateFinEnchere=" + dateFinEnchere + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", noVendeur=" + noVendeur + ", noCategorie=" + noCategorie
				+ ", noAcheteur=" + noAcheteur + nomVendeur + "]";
	}

	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int prixInitial, int prixVente, int noVendeur, int noCategorie,
			int noAcheteur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noVendeur = noVendeur;
		this.noCategorie = noCategorie;
		this.noAcheteur = noAcheteur;
	}
	
	public Article(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			int prixInitial, int prixVente, int noUtilisateur, int noCategorie, int noAcheteur) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noVendeur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.noAcheteur = noAcheteur;
		
	}
	
	public Article(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			int prixInitial, int noUtilisateur, int noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		
		this.noVendeur = noUtilisateur;
		this.noCategorie = noCategorie;
		
		
	}
	
	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int prixInitial, int prixVente, int noVendeur, int noCategorie,
			int noAcheteur, Categorie catagorie) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noVendeur = noVendeur;
		this.noCategorie = noCategorie;
		this.noAcheteur = noAcheteur;
		this.catagorie = catagorie;
	}

	public Article(String nomArticle) {
		super();
		this.nomArticle = nomArticle;
	}


	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDateDebutEnchere() {
		return this.dateDebutEnchere;
	}
	public String getSTRDateDebutEnchere() {
		String date = this.dateDebutEnchere.format(FORMAT);

		return date;
	}
	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}
	public LocalDateTime getDateFinEnchere() {
		return this.dateFinEnchere;
	}
	public String getSTRDateFinEnchere() {
		String date = this.dateFinEnchere.format(FORMAT);

		return date;
	}
	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
		
		this.dateFinEnchere = dateFinEnchere;
	}
	
	
	public String getDiffDate() {
		Duration diff = Duration.between(LocalDateTime.now(),this.dateFinEnchere);
		String hms = String.format("%d:%02d", 
                diff.toHours(), 
                diff.toMinutesPart());
		
		return format(diff);
	}
	
	public static String format(Duration d) {
	    long days = d.toDays();
	    d = d.minusDays(days);
	    long hours = d.toHours();
	    d = d.minusHours(hours);
	    long minutes = d.toMinutes();
	    d = d.minusMinutes(minutes);
	    long seconds = d.getSeconds() ;
	    return 
	            (days ==  0?"":days+" jours,")+ 
	            (hours == 0?"":hours+" heures");
	}
	
	public int getPrixInitial() {
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getNoVendeur() {
		return noVendeur;
	}

	public void setNoVendeur(int noVendeur) {
		this.noVendeur = noVendeur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public int getNoAcheteur() {
		return noAcheteur;
	}

	public void setNoAcheteur(int noAcheteur) {
		this.noAcheteur = noAcheteur;
	}
	
	public String getNomVendeur() {
		return nomVendeur;
	}

	public void setNomVendeur(String nomVendeur) {
		this.nomVendeur = nomVendeur;
	}
	

	public Utilisateur getVendeur() {
		return Vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		Vendeur = vendeur;
	}
	
	public Categorie getCatagorie() {
		return catagorie;
	}

	public void setCatagorie(Categorie catagorie) {
		this.catagorie = catagorie;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	
	
	
	
}
