package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {

	private static DataSource dataSource; 
	
	
	//le static permettra defectueux sans appel le code ci-dessous
	static {
		Context context; 
		
		try {
			context = new InitialContext(); 
			ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/eniencheres_bdd"); 
		} catch (NamingException e) {
			e.printStackTrace(); 
			throw new RuntimeException("impossible d'accéder la la bdd"); 
		}
		
	}
	
	public static Connection getConnection() throws SQLException {
		
		return ConnectionProvider.dataSource.getConnection(); 
	}
	
	
}