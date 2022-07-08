package fr.eni.encheres;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageAccueil
 */

public class ServletPageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/**
		 * @author AlexG
		 */
		
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}