package fr.eni.encheres;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;

import fr.eni.encheres.bo.Article;


public class FichiersUtils {
	private final String DOSSIERIMAGE = "D:\\projets-web\\Projets Java\\ProjetEnchere\\ProjetEncheres\\src\\main\\webapp\\asset\\img\\ImgArticles";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	String nomArticle;
	String description;
	int categorie;  
	int prixInit;
	String dateDebut;
	LocalDateTime dateDebutEncheres;
	LocalDateTime dateFinEncheres;
	String dateFin;
	String rueRetrait;
	String CPReatrait;
	String villeRetrait;
	FileItem imgArticle;
	
	public void lecteurFormulaire(List<FileItem> multiparts) throws IOException {
		//Itération item de Multipart    
	    for(FileItem item : multiparts){
	//Si item = champ de formulaire    	
	    	if (item.isFormField()) 
	    	{
	//Test item pour determiner contenu et mettre dans la bonne variable   		
	    	switch (item.getFieldName()) 
	        	{
				case "nomArticle": this.nomArticle = item.getString("UTF-8") ; break;
				case "description": this.description = item.getString("UTF-8"); break;
				case "categorie": this.categorie = Integer.parseInt(item.getString());break;
				case "prix": this.prixInit = Integer.parseInt(item.getString()); break;
				case "debutEncheres": this.dateDebut = item.getString(); break;
				case "finEncheres": this.dateFin = item.getString(); break;
				case "rue": this.rueRetrait = item.getString(); break;
				case "codePostal": this.CPReatrait = item.getString(); break;
				case "ville": this.villeRetrait = item.getString(); break;
				default: System.out.println("problème survenu");
				}
			}
	//Si item = fichier
	        if(!item.isFormField()){
	        	this.imgArticle = item;
	        }
	    }
	     dateDebut = dateDebut.replace("T", " ");
				dateFin = dateFin.replace("T", " ");
				this.dateDebutEncheres = LocalDateTime.parse(this.dateDebut, formatter);
				this.dateFinEncheres = LocalDateTime.parse(this.dateFin, formatter);
	}
	
	public void createurImgArticle(Article a ) throws Exception {
		//Création fichier image "ArticleX.ext"
        String  fileName = new File(imgArticle.getName()).getName();			 //recuperation nom.ext
        String [] split = fileName.split("\\.");						 //séparation
        System.out.println(split[0]);System.out.println(split[1]);		 //test
       // fileName = "Article"+a.getNoAcheteur()+split[split.length - 1];
        fileName= "Article".concat(String.valueOf(a.getNoArticle())).concat(".").concat(split[split.length - 1]);
        System.out.println(fileName);
			imgArticle.write(new File(DOSSIERIMAGE + File.separator + fileName));
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public int getCategorie() {
		return categorie;
	}

	public int getPrixInit() {
		return prixInit;
	}

	public String getRueRetrait() {
		return rueRetrait;
	}

	public String getCPReatrait() {
		return CPReatrait;
	}

	public String getVilleRetrait() {
		return villeRetrait;
	}

	public FileItem getImgArticle() {
		return imgArticle;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	

	
	
}
