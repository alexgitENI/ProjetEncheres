package fr.eni.encheres.dal;

import java.util.ArrayList;
import java.util.List;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<Integer> listeCodesErreur;
	
	public DALException() {
		super();
		this.listeCodesErreur=new ArrayList<>();
	}
	public DALException(String message) {
		super(message);
		this.listeCodesErreur=new ArrayList<>();
	}
	public DALException(Throwable cause ) {
		super(cause);
	}
	
	public void ajouterErreur(int code)
	{
		if(!this.listeCodesErreur.contains(code))
		{
			this.listeCodesErreur.add(code);
		}
	}
	
	public boolean hasErreurs()
	{
		return this.listeCodesErreur.size()>0;
	}
	
	public Object getListeCodesErreur() {
		return this.listeCodesErreur;
	}

	}


