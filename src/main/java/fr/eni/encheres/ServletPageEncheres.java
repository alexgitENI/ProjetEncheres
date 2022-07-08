package fr.eni.encheres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class ServletPageEncheres
 */

public class ServletPageEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		try {
			//Récupération des articles en cour de vente et des informations du vendeur
			ArticleManager artManager = new ArticleManager();
			UtilisateurManager userManager = new UtilisateurManager(); 
			EnchereManager enchereManager = new EnchereManager(); 
			
			
			List<Article> listeArticle = artManager.selectUnsellArticle();	
					
			request.setAttribute("listeArticle", listeArticle); 
		
			
			for(Article article : listeArticle) {
				Utilisateur user = userManager.selectionnerInformationDiscret(article.getNoVendeur()); 
				Enchere enchere = enchereManager.selectEnchere(article.getNoArticle()); 
				//System.out.println(user.getPseudo());
				article.setNomVendeur(user.getPseudo()); 
				article.setEnchere(enchere); 
			}
			
			//récupération de l'ensemble des catégorie sur la bdd
			
			CategorieManager catManager = new CategorieManager(); 
			List<Categorie> listeCategories = catManager.selectAllCategrorie(); 			
			request.setAttribute("Categories", listeCategories); 
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/encheres.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String achatVenteRadio = request.getParameter("achatVente");
		Utilisateur user=  (Utilisateur) request.getSession(false).getAttribute("connectedUser"); 
		
		List<Article> listeArticles = new ArrayList<Article>(); 
		
		//scenartio pour la selection des achats Utilisateurs
		if("achat".equals(achatVenteRadio)) {
			
		
			
			String[] enchereCheckBox = request.getParameterValues("enchere");			
			List<String> encheList = Arrays.asList(enchereCheckBox); 						
			
			for(String checkBoxValue : encheList) {				
				listeArticles.addAll(recuperationEnchere(checkBoxValue, user)); 				
			}
			
			
			//redispatch
					
		}
		
		
		//scenartio pour la selection des vente utilisateurs
		if("vente".equals(achatVenteRadio)) {
			
			
			String[] venteCheckBox = request.getParameterValues("vente");			
			List<String> encheListVente = Arrays.asList(venteCheckBox); 						
			
			for(String checkBoxValue : encheListVente) {	
				List<Article> tempArticle = recuperationEnchere(checkBoxValue, user); 
				if(tempArticle != null) {
					listeArticles.addAll(tempArticle); 
				}
				
				
								
			}
			
			
		}
		
		
		CategorieManager catManager = new CategorieManager(); 
		List<Categorie> listeCategories;
		try {
			listeCategories = catManager.selectAllCategrorie();
			request.setAttribute("Categories", listeCategories); 
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
		
		
			request.setAttribute("listeArticle", listeArticles); 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/encheres.jsp");
			rd.forward(request, response);
		
		
		
	}
	
	protected List<Article> recuperationEnchere(String requete, Utilisateur user){
		 
		
		List<Article> listArticles = null;
		try {
			
			ArticleManager artManager = new ArticleManager();
			UtilisateurManager userManager = new UtilisateurManager(); 
			EnchereManager enchereManager = new EnchereManager(); 
			
			
			switch(requete){
			   
		       case "ouverte": 
		    	   listArticles = artManager.selectUnsellArticle();	
		           break;
		           
		       case "enCour":
		    	   listArticles = artManager.selectAchatEnCour(user.getNoUtilisateur());		    	  
		       	  break;
		       	  
		       case "termine": 
			       listArticles = artManager.selectAchatTermines(user.getNoUtilisateur());	
			     break;
			     
		       case "venteEnCour":
		    	   listArticles = artManager.selectVenteUtilisateurEncour(user.getNoUtilisateur());
		       break;
		       
		       case "venteNonDebute":
		    	   listArticles = artManager.selectVenteUtilisateurNonDebute(user.getNoUtilisateur());
			   break;
			   
		       case "venteTermine":
		    	   listArticles = artManager.selectVenteUtilisateurTermine(user.getNoUtilisateur());
		       break;
		       
		       
		   }
			 
		//recupération des information complémentaire des articles à afficher			
		 if(listArticles != null) {
			 for(Article article : listArticles) {
					Utilisateur userSell = userManager.selectionnerInformationDiscret(article.getNoVendeur()); 
					Enchere enchere = enchereManager.selectEnchere(article.getNoArticle()); 
					article.setNomVendeur(userSell.getPseudo()); 
					article.setEnchere(enchere); 
				}
				
		 }
		
			
			
		
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listArticles; 
	}

}
