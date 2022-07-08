package fr.eni.encheres.bll;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.ObjetsEnchereDAO;

//todo manage du DELETE

public class ArticleManager extends  VerificationArticleManager {
	
	private ObjetsEnchereDAO<Article> articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public Article addArticle(String nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, int prixInit, int vendeur, int categorie) throws DALException, BusinessException {
		
		Article a = null; 
		BusinessException exception = new BusinessException();
		this.validerNom(nom, exception);
		this.validerDescription(description, exception);
		this.validerDateDebut(dateDebut, exception);
		this.validerDateFin(dateFin, exception);
		this.validerPrixInitial(prixInit, exception);
		this.validerVendeur(vendeur, exception);
		this.validerCategorie(categorie, exception);
		
	//	if (!exception.hasErreurs()) 
		//{
			a = new Article(nom, description, dateDebut, dateFin, prixInit, vendeur, categorie);
			a = articleDAO.insert(a);
		//}
				
		/*if (exception.hasErreurs()) 
		{
			throw exception;
		}*/
		
		return a; 
		
	}
	
	public Article selectArticle(int id) throws DALException, BusinessException {
		BusinessException exception = new BusinessException();
		Article a = null;
		this.validerNoArticle(id, exception);
		if (!exception.hasErreurs()) {
			a = articleDAO.selectByIdFull(id);			
		}
		
		if (exception.hasErreurs()) {
			throw exception;
		}

		return a;
	}
	
	public void VerificationEtModificationFinEnchere(Article article, LocalDateTime date) throws DALException {
		if(article.getDateFinEnchere().compareTo(date)<0) {
			//System.out.println(article.getNomArticle() + " : l'enchère est terminé");
			Enchere enchere = recuperationMeilleurEnchere(article.getNoArticle());
			article.setNoAcheteur(enchere.getNoUtilisateur()); 
			article.setPrixVente(enchere.getMontant());
			updateArticle(article);
		}else {
			//System.out.println( article.getNomArticle() + " : l'enchère n'est pas terminé");
			
		}
	}
	
	
	public void VerificationSiFinEnchereSuperieurSysDate(Article article) throws DALException, BusinessException {
		 this.articleDAO.verifFinEncheres(article);
	}
		
		
		

	public Enchere recuperationMeilleurEnchere(int idArticle) throws DALException {
		EnchereManager enchereManager = new EnchereManager(); 
		
		return enchereManager.selectEnchere(idArticle);
	}
	
	public List<Article> selectAllArticles() throws DALException{
		return this.articleDAO.selectAllDiscret();
	}
	
	public List<Article> selectArticleEnVente(){
		return this.articleDAO.selectDateEnCours();
	}
	
	public List<Article> selectUnsellArticle(){
		return this.articleDAO.selectUnsellArticle();
		
	}
	
	public List<Article> selectAchatEnCour(int no_utilisateur){
		return this.articleDAO.selectAchatEnCour(no_utilisateur);
	}
	
	public List<Article> selectAchatTermines(int no_utilisateur){
		return this.articleDAO.selectAchatTermines(no_utilisateur);
	}
	
	
	
	public  void deleteArticle(int id) throws DALException {
		articleDAO.delete(id);
	}
	
	public void updateArticle(Article a) throws DALException {
		articleDAO.update(a, false);
	}

	public List<Article> selectVenteUtilisateurEncour(int noUtilisateur) {
		return this.articleDAO.selectVenteUtilisateurEncour(noUtilisateur);
	}

	public List<Article> selectVenteUtilisateurNonDebute(int noUtilisateur) {
		return this.articleDAO.selectVenteUtilisateurNonDebute(noUtilisateur);
	}

	public List<Article> selectVenteUtilisateurTermine(int noUtilisateur) {
		return this.articleDAO.selectVenteUtilisateurTermine(noUtilisateur);
	}

	public void VerificationFinEnchere(Article a) {
		// TODO Auto-generated method stub
		
	}

}
