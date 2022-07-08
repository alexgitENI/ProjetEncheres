package fr.eni.encheres.dal;

import java.util.List;


public interface SelectByDateInterface<T> {

	public List<T> selectDateEnCours();
	public List<T> selectUnsellArticle();

}
