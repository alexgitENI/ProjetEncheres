package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.jdbc.ArticleDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.EnchereDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.RetraitDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOImpl;

public class DAOFactory {
	

	public static ObjetsEnchereDAO<Utilisateur> getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}

	public static ObjetsEnchereDAO<Article> getArticleDAO(){
		return new ArticleDAOJDBCImpl();
		
	}
	
	public static ObjetsEnchereDAO<Categorie> getCategorieDAO(){
		return new CategorieDAOJdbcImpl();
		
	}
	
	public static ObjetsEnchereDAO<Enchere> getEnchereDAO(){
		return new EnchereDAOJDBCImpl();
		
	}
	
	public static ObjetsEnchereDAO<Retrait> getRetraitDAO(){
		return new RetraitDAOJDBCImpl();
	}
	

}
