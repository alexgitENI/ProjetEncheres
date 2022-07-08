package fr.eni.encheres.bll;

import java.time.LocalDateTime;

import fr.eni.encheres.BusinessException;

public class VerificationArticleManager {
	
	public void validerNoArticle(int id, BusinessException businessException) {
		if (id <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_N_ARTICLE);
		}
	}
	
	public void validerNom(String nom, BusinessException businessException) {
		if (nom.isBlank() ^ nom.isEmpty() ^ nom.length() > 30 ^ nom.length() < 3) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_NOM);
		}
	}

	public void validerDescription(String description, BusinessException businessException) {
		if (description.isBlank() ^ description.isEmpty() ^ description.length() > 300 ^ description.length() < 10) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_DESCRIPTION);
		}
	}

	public void validerDateDebut(LocalDateTime dateDebut, BusinessException businessException) {
		if (dateDebut.compareTo(LocalDateTime.now()) < 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_DATE_JOUR_DEBUT);
		}
	}

	//Date fin inférieure a 3j?
	public void validerDateFin(LocalDateTime dateFin, BusinessException businessException) {
		if (dateFin.compareTo(LocalDateTime.now().plusDays(3)) < 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_DATE_JOUR_FIN);
		}
	}
	
	public void validerPrixInitial(int prixInitial, BusinessException businessException) {
		if (prixInitial <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PRIX_INITIAL);
		}
	}

	public void validerPrixVente(int prixVente, int prixInitial ,BusinessException businessException) {
		if (prixVente < prixInitial ^ prixVente <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PRIX_VENTE);
		}
	}
	
	//Verif BDD? non si création simultanée
	public void validerVendeur(int vendeur, BusinessException businessException) {
		if (vendeur == 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_VENDEUR);
		}
	}
	
	//Verif BDD? non si création simultanée
	public void validerCategorie(int categorie ,BusinessException businessException) {
		if (categorie == 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_CATEGORIE);
		}
	}
	//Verif BDD? non si création simultanée
	public void validerAcheteur(int acheteur, BusinessException businessException) {
		if (acheteur ==0 ) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_ACHETEUR);
		}
	}
}
