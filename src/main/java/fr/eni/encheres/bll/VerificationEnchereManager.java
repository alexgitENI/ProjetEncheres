package fr.eni.encheres.bll;

import java.time.LocalDateTime;

import fr.eni.encheres.BusinessException;

public class VerificationEnchereManager {

	public void validerNoUtilisateur(int noUtilistaeur, BusinessException businessException) {
		if (noUtilistaeur <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_NOM);
		}
	}

	public void validerNoArticle(int noArticle, BusinessException businessException) {
		if (noArticle <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_N_ARTICLE);
		}
	}

	public void validerDateEnchere(LocalDateTime dateEnchere, BusinessException businessException) {
		if (dateEnchere == null) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_DATE_ENCHERE);
		}
	}

	public void validerMonttantEnchere(int montantEnchere, BusinessException businessException) {
		if (montantEnchere <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PRIX_INITIAL);
		}
	}
	
	public void validerCreditSupEnchere(boolean resultat) throws BusinessException{
		BusinessException businessException = new BusinessException();
		if (resultat = false) {
			businessException.ajouterErreur(CodesResultatBLL.SOLDE_INSUFISSANT);
		}

	}
}
