package fr.eni.encheres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class ServletProfilUtilisateur
 */

public class ServletProfilUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UtilisateurManager userManager = new UtilisateurManager(); 
	
		int id = Integer.parseInt(request.getParameter("id") ); 
		try {
			Utilisateur user = userManager.selectionnerInformationDiscret(id);
			request.setAttribute("userProfil", user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Utilisateur userConnected=  (Utilisateur) request.getSession(false).getAttribute("connectedUser"); 
		
		int noUtilisateur = userConnected.getNoUtilisateur(); 
		System.out.println(noUtilisateur);
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
			setAttributeAndRedirect(request, response, userConnected);	
		}else {
			
			try {
				
				Utilisateur userUpdated = null;	
				
				if(motDePasse!="") {
					System.out.println("modification mdp");
					 userUpdated = userManager.UdpateUtilisateurMdp(noUtilisateur,pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				}else {
					System.out.println("mot de passe null");
					 userUpdated = userManager.UdpateUtilisateurComplet(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
				}
				
				request.getSession().setAttribute("connectedUser", userUpdated);
				
				
				String message = "Votre profil a été modifié";
				response.setCharacterEncoding("UTF-8" );				
				response.addCookie( CookieUtils.SetCookie("message", message, 10)  );				
				
				setAttributeAndRedirect(request, response, userUpdated);
				
				
				
				
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				setAttributeAndRedirect(request, response, userConnected);
				e.printStackTrace();
				
			} catch (DALException e) {
				setAttributeAndRedirect(request, response, userConnected);
				e.printStackTrace();
			} 
		}
		
		
	}

	private void setAttributeAndRedirect(HttpServletRequest request, HttpServletResponse response,
			Utilisateur userUpdated) throws IOException {
			request.setAttribute("userProfil", userUpdated);
			response.sendRedirect(request.getContextPath()+"/utilisateur?id="+userUpdated.getNoUtilisateur());
	}

}
