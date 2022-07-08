package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.ObjetsEnchereDAO;

public class CategorieDAOJdbcImpl implements ObjetsEnchereDAO<Categorie> {
	private final String insertCategorie = "insert into 'categories' (libelle) values(?);";
	private final String selectByIdCategorie = "SELECT `no_categorie`, `libelle` FROM `CATEGORIES` where no_categorie = ?; ";
	private final String selectAllCategorie= "SELECT `no_categorie`, `libelle` FROM `CATEGORIES`; ";
	private final String deleteCategorie = "delete from 'categories' where no_categorie = ?;";
	private final String updateEnchere = "UPDATE 'RETRAITS' SET 'nom'=? WHERE 'no_categorie'=?";

	@Override
	public Categorie insert(Categorie c) throws DALException {
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(insertCategorie, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, c.getLibelle());
			
			int rowsInserted = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys(); rs.next();
			if (rowsInserted > 0) {
				System.out.println(rowsInserted + " nouvelle Catégorie inséré");
				c.setNoCategorie(rs.getInt(1));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Categorie selectByIdFull(int id) throws DALException {
		Categorie c = null;
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(selectByIdCategorie);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				c = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}else{
				throw new DALException("Aucune Categorie ne correspont à l'id "+ id);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return c;
	}

	
	@Override
	public List<Categorie> selectAllDiscret() throws DALException {
		List<Categorie> categories = new ArrayList<>();
		Categorie c = null;
		
			try(Connection cnx = ConnectionProvider.getConnection();){
				Statement stmt = cnx.createStatement();
				ResultSet rs = stmt.executeQuery(selectAllCategorie);
				while (rs.next()) {
					c = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					categories.add(c);
				}
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return categories;
	}

	
	@Override
	public void delete(int id) throws DALException {
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(deleteCategorie);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println(rowsAffected+ " Categorie suprimmée");
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();		
			}
	}
	
	@Override
	public void update(Categorie c, boolean fullOrNot) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt = cnx.prepareStatement(updateEnchere);
			pstmt.setString(1, c.getLibelle());
			pstmt.setInt(2, c.getNoCategorie());
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected> 0) {
				System.out.println(rowsAffected+ " Article inséré");
			}
			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}

	@Override
	public List<Categorie> selectDateEnCours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> selectUnsellArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie selectByIdDiscret(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> selectAllFull() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie verificationLogin(String a, String b) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie verificationPseudo(String login) throws BusinessException, DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAchatEnCour(int no_utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAchatTermines(int no_utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectVenteUtilisateurEncour(int noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectVenteUtilisateurNonDebute(int noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectVenteUtilisateurTermine(int noUtilisateur) {
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
	public void verifFinEncheres(Article article) throws BusinessException {
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


	

