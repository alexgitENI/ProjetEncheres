package fr.eni.encheres;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class ServletAnnulationVente
 */
@WebServlet("/annulationVente")
public class ServletAnnulationVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAnnulationVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idArticle = Integer.parseInt(request.getParameter("idArticle") ); 
		ArticleManager artManager = new ArticleManager(); 
		 try {
			artManager.deleteArticle(idArticle);
			response.setCharacterEncoding("UTF-8" );				
			response.addCookie( CookieUtils.SetCookie("message", "l'article a été supprimé", 10)  );				
			response.sendRedirect(request.getContextPath());
			
			
		} catch (DALException e) {
			 
			e.printStackTrace();
		} 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
