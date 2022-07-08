package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ObjetsEnchereDAO;
import fr.eni.encheres.dal.SelectByDateInterface;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

//TODO definir la/les requêtes de sélection par  date

public class ArticleDAOJDBCImpl implements ObjetsEnchereDAO<Article>, SelectByDateInterface<Article>{
	private final String selectByDateArticle = "SELECT * from 'ARTICLES' where  date_fin_encheres is null;";
	private final String SELECT_UNSELL_ARTICLE = "SELECT * FROM `ARTICLES` WHERE no_acheteur is NULL";
	private final String selectAllArticles = "SELECT * from 'ARTICLES'; ";
	private final String selectByIdArticles = "SELECT `no_article`, `nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_vendeur`, `no_categorie`, `no_acheteur` FROM `ARTICLES` WHERE `no_article` = ?; ";
	private final String insertArticle = "INSERT INTO `ARTICLES`(`nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_vendeur`, `no_categorie`) VALUES(?, ?, ?, ?, ?, ?, ?,?);";
	private final String deleteArticle = "DELETE FROM `ARTICLES` WHERE `no_article` = ?;";
	private final String selectUnsellByUser = "SELECT ARTICLES.no_article, `nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_vendeur`, `no_categorie`, `no_acheteur` FROM `ARTICLES` LEFT JOIN `LISTENCHERES` ON ARTICLES.no_article = LISTENCHERES.no_article WHERE LISTENCHERES.no_utilisateur = ? AND no_acheteur is NULL;" ;
	private final String selectSellByUser = "SELECT ARTICLES.no_article, `nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_vendeur`, `no_categorie`, `no_acheteur` FROM `ARTICLES` LEFT JOIN `LISTENCHERES` ON ARTICLES.no_article = LISTENCHERES.no_article WHERE LISTENCHERES.no_utilisateur = ? AND no_acheteur is  NOT NULL;" ;
	
	private final String selectVenteUtilisateurEncour = "SELECT * FROM `ARTICLES` WHERE  no_vendeur = ? AND no_acheteur is NULL;";
	private final String selectVenteUtilisateurNondebute = "SELECT * FROM `ARTICLES` WHERE no_vendeur = ? AND date_debut_encheres > ? ;";
	private final String selectVenteUtilisateurTermine = "SELECT * FROM `ARTICLES` WHERE  no_vendeur = ? AND no_acheteur is  NOT NULL;";
	//TODO
	private final String updateArticle = "UPDATE `ARTICLES` SET `nom_article`=?,`description`=?,`date_debut_encheres`=?,`date_fin_encheres`=?,`prix_initial`=?,`prix_vente`=?,`no_vendeur`=?,`no_categorie`=?,`no_acheteur`=? WHERE `no_article` = ?";

	public final SimpleDateFormat formatDateFR = new SimpleDateFormat("DD/MM/YY");
	public final LocalTime localTime = LocalTime.now();
	

	@Override
	public  Article insert(Article a) {
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			//Todo avant insert article: insert Categorie et Utilisateur
			PreparedStatement pstmt = cnx.prepareStatement(insertArticle, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, a.getNomArticle());
			pstmt.setString(2, a.getDescription());
			pstmt.setObject(3, a.getDateDebutEnchere());
			pstmt.setObject(4, a.getDateFinEnchere());
			pstmt.setInt(5, a.getPrixInitial());	
			pstmt.setInt(6, a.getPrixInitial());
			pstmt.setInt(7, a.getNoVendeur());
			pstmt.setInt(8, a.getNoCategorie());
			
			
			int rowsInserted = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys(); rs.next();
			if (rowsInserted > 0) {
				System.out.println(rowsInserted + " Article inséré");
				a.setNoArticle(rs.getInt(1));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return a; 
	}

	
	@Override
	public Article selectByIdFull(int id) {
		Article a = null;
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(selectByIdArticles);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
			}else{
				throw new DALException("Aucun Article ne correspont à l'id "+ id);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();		
			}
		

		return a;
	}

	@Override
	public List<Article> selectAllDiscret() {
	//Création Liste
		List<Article> articles = new ArrayList<>();
		Article a = null;
		
			try(Connection cnx = ConnectionProvider.getConnection();){
				Statement stmt = cnx.createStatement();
				ResultSet rs = stmt.executeQuery(selectAllArticles);
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					articles.add(a);
				}
			
				stmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		return articles;
	}
	//paramatre int ou Article?
	@Override
	public void delete(int id) {
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(deleteArticle);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println(rowsAffected+ " Article suprimmé");
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();		
			}
	}

	public List<Article> selectDateEnCours() {
		Article a = null;
		List<Article> articleEnCours = new ArrayList<Article>();
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(selectByDateArticle);
			if (rs != null) {
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
							rs.getObject("date_debut_encheres", LocalDateTime.class),
							rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"),
							rs.getInt("prix_vente"), rs.getInt("no_vendeur"), rs.getInt("no_categorie"),
							rs.getInt("no_acheteur"));
					articleEnCours.add(a);
				} 
			} else {
				throw new DALException("Aucun Article Correspondant");
			}
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();		
		}
		return articleEnCours;
	}

	@Override
	public List<Article> selectUnsellArticle() {
		List<Article> articles = new ArrayList<>();
		Article a = null;
			try(Connection cnx = ConnectionProvider.getConnection();){
				Statement stmt = cnx.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_UNSELL_ARTICLE);
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					articles.add(a);
				}
				stmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return articles;
	}


	@Override
	public List<Article> selectAchatEnCour(int id) {
		List<Article> articles = new ArrayList<>();
		Article a = null;
			try(Connection cnx = ConnectionProvider.getConnection();){
				PreparedStatement pstmt = cnx.prepareStatement(selectUnsellByUser);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					
					articles.add(a);
				}
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return articles;
		

	}

	public List<Article> selectAchatTermines(int id) {
		List<Article> articles = new ArrayList<>();
		Article a = null;
			try(Connection cnx = ConnectionProvider.getConnection();){
				PreparedStatement pstmt = cnx.prepareStatement(selectSellByUser);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					
					articles.add(a);
				}
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return articles;

	}



		

	public List<Article> selectVenteUtilisateurEncour(int id) {
		List<Article> articles = new ArrayList<>();
		Article a = null;
			try(Connection cnx = ConnectionProvider.getConnection();){
				PreparedStatement pstmt = cnx.prepareStatement(selectVenteUtilisateurEncour);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					
					articles.add(a);
				}
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return articles;

	}
	
	public List<Article> selectVenteUtilisateurNonDebute(int id) {
		List<Article> articles = new ArrayList<>();
		Article a = null;
		
		LocalDateTime dateDuJour =  LocalDateTime.now(); 
			try(Connection cnx = ConnectionProvider.getConnection();){
				PreparedStatement pstmt = cnx.prepareStatement(selectVenteUtilisateurNondebute);
				pstmt.setInt(1, id);
				pstmt.setObject(2, dateDuJour);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					
					articles.add(a);
				}
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return articles;
	}


	@Override
	public List<Article> selectVenteUtilisateurTermine(int id) {
		List<Article> articles = new ArrayList<>();
		Article a = null;
			try(Connection cnx = ConnectionProvider.getConnection();){
				PreparedStatement pstmt = cnx.prepareStatement(selectVenteUtilisateurTermine);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					a = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),  rs.getObject("date_debut_encheres", LocalDateTime.class), rs.getObject("date_fin_encheres", LocalDateTime.class), rs.getInt("prix_initial"), rs.getInt("prix_vente") , rs.getInt("no_vendeur"), rs.getInt("no_categorie"), rs.getInt("no_acheteur"));
					
					articles.add(a);
				}
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return articles;

	}


	@Override
	public void verifFinEncheres(Article article) throws BusinessException {
		
		if(article.getDateFinEnchere().isAfter(LocalDateTime.now())) {
			
		}
		
		else {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.ENCHERE_DEJA_FINI);
			throw be; 
		}
	}
	
	
	@Override
	public void update(Article a, boolean fullOrNot) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(updateArticle);
			pstmt.setString(1, a.getNomArticle());
			pstmt.setString(2, a.getDescription());
			pstmt.setObject(3, a.getDateDebutEnchere());
			pstmt.setObject(4, a.getDateFinEnchere());
			pstmt.setInt(5, a.getPrixInitial());
			pstmt.setInt(6, a.getPrixVente());
			pstmt.setInt(7, a.getNoVendeur());
			pstmt.setInt(8, a.getNoCategorie());
			pstmt.setInt(9, a.getNoAcheteur());
			pstmt.setInt(10, a.getNoArticle());
			
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println(rowsInserted + " Article mis à jour");
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Article selectByIdDiscret(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Article> selectAllFull() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Article verificationLogin(String a, String b) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Article verificationPseudo(String login) throws BusinessException, DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int VerifCreditUtilisateur(int noUtilisateur) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int VerifMontantEnchere(int idArticle) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void VerifCreditSuperieurEncheres(int montantDeniereEnchere, int creditVerifierBDD)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void VerifMontantMinimum(int offreUtilisateur, int montantDeniereEnchere) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void crediterAncienEncherisseur(int noUtilisateur, int montant) throws DALException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void debiterEncherisseur(int noUtilisateur, int credit, int offreUtilisateur) throws DALException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateLorsDeEncheres(Enchere e) {
		// TODO Auto-generated method stub
		
	}
		
	}



