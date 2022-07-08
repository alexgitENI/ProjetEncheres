package fr.eni.encheres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.dal.DALException;


/**
 * Servlet implementation class ServletInscription
 */

public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
	
		
		UtilisateurManager userManager = new UtilisateurManager(); 
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		
		
		/**
		 * @author AlexG
		 */
		if(!motDePasse.equals(confirmation)) {			
			listeCodesErreur.add(CodeResultatServlet.CONFIRMATION_MDP);					
		}
		
		//traitement
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			rd.forward(request, response);		
		}else {
			
			try {
				
				userManager.ajouterutilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				//création du cookie pour l'affichage du message
				//TODO création methode et fichier proprietes 
				String message = "Votre inscription est réussie, bienvenue parmis nous.";
				response.setCharacterEncoding("UTF-8" );				
				response.addCookie( CookieUtils.SetCookie("message", message, 10)  );				
				response.sendRedirect(request.getContextPath());
				
				
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				rd.forward(request, response);	
				e.printStackTrace();
				
			} catch (DALException e) {
				//request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				rd.forward(request, response);	
				e.printStackTrace();
			} 
		}
		
		
		
}
	
}
