package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.ObjetsEnchereDAO;

public class EnchereManager extends VerificationEnchereManager {

	private ObjetsEnchereDAO<Enchere> enchereDAO;

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void addEnchere(int idUser, int idArticle, LocalDateTime dateEnchere, int montant)
			throws DALException, BusinessException {
		BusinessException exception = new BusinessException();
		this.validerNoUtilisateur(montant, exception);
		this.validerNoArticle(idArticle, exception);
		this.validerDateEnchere(dateEnchere, exception);
		this.validerMonttantEnchere(montant, exception);
		if (!exception.hasErreurs()) {
			Enchere e = new Enchere(idUser, idArticle, dateEnchere, montant);
			enchereDAO.insert(e);
		}
		if (exception.hasErreurs()) {
			throw exception;
		}
	}

	public void VerifCreditSuperieurEncheres(int montantDeniereEnchere, int creditVerifierBDD)
			throws BusinessException {
		enchereDAO.VerifCreditSuperieurEncheres(montantDeniereEnchere, creditVerifierBDD);
	}

	public Enchere selectEnchere(int id) throws DALException {
		return this.enchereDAO.selectByIdFull(id);
	}

	public List<Enchere> selectAllEnchere() throws DALException {
		return this.enchereDAO.selectAllDiscret();
	}

	public void deleteEnchere(int id) throws DALException {
		this.enchereDAO.delete(id);
	}

	public void updateEnchere(Enchere e) throws DALException {
		this.enchereDAO.update(e, false);
	}
	
	public void updateEnchereAchat(Enchere e) throws DALException {
		this.enchereDAO.updateLorsDeEncheres(e);
	}
	
	

	public Enchere addEnchere(int idArticle, int montant) throws DALException {
		Enchere e = new Enchere(idArticle, montant);
		return this.enchereDAO.insert(e);

	}

	public int VerifMontantDerniereEncheres(int idArticle) throws DALException {
		return enchereDAO.VerifMontantEnchere(idArticle);

	}

	public void VerifMontantMini(int offreUtilisateur, int montantDeniereEnchere) throws BusinessException {
		enchereDAO.VerifMontantMinimum(offreUtilisateur, montantDeniereEnchere);
	}



}
