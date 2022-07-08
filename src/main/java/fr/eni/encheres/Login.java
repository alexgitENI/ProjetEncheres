package fr.eni.encheres;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on verifie si une session existe deja

		/**
		 * if(request.getSession(false) != null){ // on envoie un message sur l'accueil
		 * String message = "Vous etes deja connecter";
		 * response.setCharacterEncoding("UTF-8" ); response.addCookie(
		 * CookieUtils.SetCookie("message", message, 10) );
		 * response.sendRedirect(request.getContextPath());
		 * 
		 * } else {
		 **/
		// on redirige vers la connexion

		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");
		if (login == null)
			login = "inscrire";
		if (password == null)
			password = "";
		// Stocker dans le modele
		HttpSession session = request.getSession(true); // creer session meme si elle n'existe pas
		session.setAttribute("login", login);
		session.setAttribute("password", password);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
		// }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager userManager = new UtilisateurManager();
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		String email = request.getParameter("email");
		String mot_de_passe = request.getParameter("password");

		try {
			Utilisateur user = userManager.VerificationLogin(email, mot_de_passe);

			if (user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("connectedUser", user);
				String message = "Bonjour " + user.getNom();
				System.out.println(user.toString());
				response.setCharacterEncoding("UTF-8");
				response.addCookie(CookieUtils.SetCookie("message", message, 10));
				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("erreurLogin","Identifiant ou mot de passe incorrect");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
				rd.forward(request, response);

			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
