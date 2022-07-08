package fr.eni.encheres.dal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestPoolConnection
 */
@WebServlet("/TestPoolConnection")
public class TestPoolConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out  = response.getWriter();
		
		try {
			Context context = new InitialContext();
			//recherche de la datasource
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/eniencheres_bdd"); 
			//Demande de la connexion
			Connection cnx = dataSource.getConnection();  
			
			out.println("la connextion est "+(cnx.isClosed()?"fermé":"ouverte")); 
			
			//liberation de la connextion
			cnx.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
			out.println("erreur sur le context ou SQL"+ e.getMessage());
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
