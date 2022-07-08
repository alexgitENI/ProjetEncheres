package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.ObjetsEnchereDAO;

public class RetraitManager {
	
	private ObjetsEnchereDAO<Retrait> retraitDAO;
	
	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}

	public Retrait addRetrait(int noArticle, String rue, String codePostal, String ville)  throws DALException, BusinessException {
			Retrait r = new Retrait(noArticle, rue, codePostal, ville);
			return retraitDAO.insert(r);
	 }
	
	public Retrait selectRetrait(int id) throws DALException, BusinessException {
		Retrait r = null;
			r = retraitDAO.selectByIdFull(id);			
		return r;
	}
	
	public List<Retrait> selectAllRetrait() throws DALException{
		List<Retrait> retraits= new ArrayList<>();
		retraits = retraitDAO.selectAllFull();
		return retraits;
	}
	
	public void deleteRetrait(int id) throws DALException {
		retraitDAO.delete(id);
	}
	
	public void updateRetrait(Retrait r) throws DALException {
		retraitDAO.update(r, false);
	}

}
