package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

public class VerificationUtilisateurManager {

	private static int TAILLE_MINI = 3;
	private static int TAILLE_MAXI = 30;

	private static String REGEX_CODE_POSTAL = "^[0-9]{5}(?:-[0-9]{4})?$";
	private static String REGEX_TELEPHONE = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";
	private static String REGEX_VILLE = "^\\s*[a-zA-Z]{1}[0-9a-zA-Z][0-9a-zA-Z '-.=#/]*$";
	private static String REGEX_MOT_DE_PASSE = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	boolean PseudoOK = true;

	protected void validerPseudo(String pseudo, BusinessException businessException)
			throws BusinessException, DALException {

		UtilisateurManager userManager = new UtilisateurManager();

		Utilisateur u = new Utilisateur();

		u = userManager.VerificationSiPseudoExistant(pseudo);

		if (u.getPseudo() == null) {

		}

		else {
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_DEJA_PRIS);
		}

		if (pseudo.isBlank() ^ pseudo.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}

		if (pseudo.length() < TAILLE_MINI ^ pseudo.length() > TAILLE_MAXI) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PRENOM);
		}

	}

	protected void validerNom(String nom, BusinessException businessException) {

		if (nom.isBlank() ^ nom.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}

		if (nom.length() < TAILLE_MINI ^ nom.length() > TAILLE_MAXI) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_NOM);
		}

	}

	protected void validerPrenom(String prenom, BusinessException businessException) {

		if (prenom.isBlank() ^ prenom.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}

		if (prenom.length() < TAILLE_MINI ^ prenom.length() > TAILLE_MAXI) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PRENOM);
		}

	}

	protected void validerEmail(String email, BusinessException businessException) {

		if (email.isBlank() ^ email.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}

		if (!email.contains("@")) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_EMAIL);
		}
	}

	protected void validerTelephone(String telephone, BusinessException businessException) {

		if (telephone.isBlank() ^ telephone.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}

		if (!telephone.matches(REGEX_TELEPHONE)) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_TELEPHONE);

		}

	}

	protected void validerRue(String rue, BusinessException businessException) {

		if (rue.isBlank() ^ rue.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}
	}

	protected void validercodePostal(String codePostal, BusinessException businessException) {

		if (codePostal.isBlank() ^ codePostal.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}

		if (!codePostal.matches(REGEX_CODE_POSTAL)) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_CODE_POSTAL);

		}

	}

	protected void validerVille(String ville, BusinessException businessException) {

		if (ville.isBlank() ^ ville.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);

		}
		if (!ville.matches(REGEX_VILLE)) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_VILLE);

		}

	}

	protected void validermotDePasse(String motdepasse, BusinessException businessException) {

		if (motdepasse.isBlank() ^ motdepasse.isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VIDE_OU_BLANC);
		}
		/**
		 * if (!motdepasse.matches(REGEX_MOT_DE_PASSE)) {
		 * businessException.ajouterErreur(CodesResultatBLL.REGLE_MDP); }
		 * 
		 * 
		 **/

	}

}
