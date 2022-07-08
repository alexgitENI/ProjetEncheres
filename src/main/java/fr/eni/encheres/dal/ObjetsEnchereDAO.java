package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface ObjetsEnchereDAO<T> extends SelectByDateInterface<T> {

	public T insert(T objet) throws DALException;

	public T selectByIdFull(int id) throws DALException;

	public T selectByIdDiscret(int id) throws DALException;

	public List<T> selectAllFull() throws DALException;

	public List<T> selectAllDiscret() throws DALException;

	public void delete(int id) throws DALException;

	public T verificationLogin(String a, String b) throws DALException;

	public T verificationPseudo(String login) throws BusinessException, DALException;

	public void update(T type, boolean fullOrNot) throws DALException;

	public List<Article> selectAchatEnCour(int no_utilisateur);

	public List<Article> selectAchatTermines(int no_utilisateur);

	public List<Article> selectVenteUtilisateurEncour(int noUtilisateur);

	public List<Article> selectVenteUtilisateurNonDebute(int noUtilisateur);

	public List<Article> selectVenteUtilisateurTermine(int noUtilisateur);

	public int VerifCreditUtilisateur(int noUtilisateur) throws DALException;

	public int VerifMontantEnchere(int idArticle) throws DALException;

	public void VerifCreditSuperieurEncheres(int montantDeniereEnchere, int creditVerifierBDD) throws BusinessException;

	public void VerifMontantMinimum(int offreUtilisateur, int montantDeniereEnchere) throws BusinessException;

	public void verifFinEncheres(Article article) throws BusinessException;

	public void crediterAncienEncherisseur(int noUtilisateur, int montant) throws DALException;

	public void debiterEncherisseur(int noUtilisateur,int credit ,int offreUtilisateur) throws DALException;

	public void updateLorsDeEncheres(Enchere e);

}
