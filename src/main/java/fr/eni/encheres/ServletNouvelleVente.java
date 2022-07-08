package fr.eni.encheres;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;


/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
//Dossier d'enregistrment adapter a son emplacement (phase dev)
	public final String imgArticlesPath = "D:\\projets-web\\Projets Java\\ProjetEnchere\\ProjetEncheres\\src\\main\\webapp\\asset\\img\\ImgArticles";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategorieManager catManager = new CategorieManager(); 
		List<Categorie> listeCategories;
		try {
			listeCategories = catManager.selectAllCategrorie();
			request.setAttribute("Categories", listeCategories); 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
			rd.forward(request, response);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Récuperation User Courant
		Utilisateur vendeur = (Utilisateur) request.getSession(false).getAttribute("connectedUser");
	//Conversion request ---> Multipart TODO RESTE A REGLER LIMTE TAILLE FICHIER ET FORMAT OU CONVERSION EN JPG (PAS TROP COMPLIQUé) reorga exception
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> multiparts = upload.parseRequest(new ServletRequestContext(request));
	//Création des Managers
		ArticleManager artManager = new ArticleManager(); 
		RetraitManager retraitManager = new RetraitManager();
		EnchereManager enchereManager = new EnchereManager();
	//Inits Outils et variables
		FichiersUtils lecteur = new FichiersUtils();
	//lecture du formulaire, et valeurs stcockés par FichierUtils ---> récupération via getters	
		lecteur.lecteurFormulaire(multiparts);
		//création + insert d'un nouvel article
			try {
				Article a = artManager.addArticle(lecteur.getNomArticle(), lecteur.getDescription(), lecteur.dateDebutEncheres, lecteur.dateFinEncheres, lecteur.getPrixInit() , vendeur.getNoUtilisateur(), lecteur.getCategorie());
				//création d'une enchère vierge + insert
				Enchere e = enchereManager.addEnchere(a.getNoArticle(), a.getPrixInitial());
				a.setEnchere(e);
				//création d'un point de retrait + insert
				Retrait r = retraitManager.addRetrait(a.getNoArticle(), lecteur.getRueRetrait(), lecteur.getCPReatrait(), lecteur.getVilleRetrait());
				a.setRetrait(r);
				
				lecteur.createurImgArticle(a);
	            String message = "Votre article est maintenant en vente";
				response.setCharacterEncoding("UTF-8" );				
				response.addCookie(CookieUtils.SetCookie("message", message, 10));				
				response.sendRedirect(request.getContextPath());
				
			} catch (DALException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);	
				e.printStackTrace();
				
			} catch (BusinessException e1) {
				request.setAttribute("listeCodesErreur", e1.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);	
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
}
