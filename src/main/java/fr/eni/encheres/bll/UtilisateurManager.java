package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.ObjetsEnchereDAO;

public class UtilisateurManager extends VerificationUtilisateurManager {

	private ObjetsEnchereDAO<Utilisateur> utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();

	}

	public Utilisateur ajouterutilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws BusinessException, DALException {

		BusinessException businessException = new BusinessException();

		this.validerPseudo(pseudo, businessException);
		this.validerNom(nom, businessException);
		this.validerPrenom(prenom, businessException);
		this.validerEmail(email, businessException);
		this.validerTelephone(telephone, businessException);
		this.validerRue(rue, businessException);
		this.validercodePostal(codePostal, businessException);
		this.validerVille(ville, businessException);
		this.validermotDePasse(motDePasse, businessException);

		Utilisateur utilisateur = null;

		if (!businessException.hasErreurs()) {
			utilisateur = new Utilisateur();

			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasse);

			try {
				this.utilisateurDAO.insert(utilisateur);
			} catch (DALException e) {
				throw new DALException(e);
			}

		} else {
			throw businessException;

		}
		return utilisateur;
	}

	public List<Utilisateur> selectionnerTousLesUtilisateurs() throws BusinessException, DALException {
		return this.utilisateurDAO.selectAllDiscret();
	}

	public Utilisateur selectionnerToutesInformationsDunUtilisateurAvecSonID(int id)
			throws BusinessException, DALException {
		return this.utilisateurDAO.selectByIdFull(id);
	}

	public Utilisateur selectionnerInformationDiscret(int id) throws BusinessException, DALException {
		return this.utilisateurDAO.selectByIdDiscret(id);
	}

	public Utilisateur VerificationLogin(String login, String mdp) throws DALException {
		return this.utilisateurDAO.verificationLogin(login, mdp);
	}

	public Utilisateur VerificationSiPseudoExistant(String pseudo) throws BusinessException, DALException {
		return this.utilisateurDAO.verificationPseudo(pseudo);
	}

	public Utilisateur UdpateUtilisateurComplet(int noUtilisateur, String pseudo, String nom, String prenom,
			String email, String telephone, String rue, String codePostal, String ville)
			throws BusinessException, DALException {

		BusinessException businessException = new BusinessException();

		/**
		 * this.validerPseudo(pseudo, businessException); this.validerNom(nom,
		 * businessException); this.validerPrenom(prenom, businessException);
		 * this.validerEmail(email, businessException); this.validerTelephone(telephone,
		 * businessException); this.validerRue(rue, businessException);
		 * this.validercodePostal(codePostal, businessException);
		 * this.validerVille(ville, businessException);
		 **/

		Utilisateur utilisateur = null;

		if (!businessException.hasErreurs()) {
			utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(noUtilisateur);
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			System.out.println(utilisateur);
			this.utilisateurDAO.update(utilisateur, false);

		} else {
			throw businessException;

		}
		return utilisateur;

	}

	public Utilisateur UdpateUtilisateurMdp(int noUtilisateur, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, String motDePasse)
			throws BusinessException, DALException {
		BusinessException businessException = new BusinessException();

		/**
		 * this.validerPseudo(pseudo, businessException); this.validerNom(nom,
		 * businessException); this.validerPrenom(prenom, businessException);
		 * this.validerEmail(email, businessException); this.validerTelephone(telephone,
		 * businessException); this.validerRue(rue, businessException);
		 * this.validercodePostal(codePostal, businessException);
		 * this.validerVille(ville, businessException);
		 * this.validermotDePasse(motDePasse, businessException);
		 **/
		Utilisateur utilisateur = null;

		if (!businessException.hasErreurs()) {
			utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(noUtilisateur);
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasse);

			this.utilisateurDAO.update(utilisateur, true);

		} else {
			throw businessException;

		}
		return utilisateur;

	}

	public int VerifCreditUtilisateur(int noUtilisateur) throws DALException {
		return this.utilisateurDAO.VerifCreditUtilisateur(noUtilisateur);
	}

	public void crediterAncienEncherisseur(int noUtilisateur, int montant) throws DALException {
		this.utilisateurDAO.crediterAncienEncherisseur(noUtilisateur, montant);

	}

	public void debiterEncherisseur(int noUtilisateur, int credit, int offreUtilisateur) throws DALException {
		this.utilisateurDAO.debiterEncherisseur(noUtilisateur, credit, offreUtilisateur);

	}

}
