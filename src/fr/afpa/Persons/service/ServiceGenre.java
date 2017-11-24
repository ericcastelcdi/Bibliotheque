package fr.afpa.Persons.service;

import java.util.ArrayList;

import fr.afpa.Persons.dao.IDaoGenre;
import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;

public class ServiceGenre implements IServiceGenre{
	
	private IDaoGenre dao;
	
// constructeur
	public ServiceGenre(IDaoGenre dao) {
		this.dao = dao;
	}

	public ArrayList<Genre> obtenirTousLesGenres(){
		return dao.obtenirTousLesGenres();
	}
	
	public void ajouterGenre(Genre genre) {
		dao.ajouterGenre(genre);
	}
	
	public void modifierGenre(Genre genre, String nomGenre) {
		dao.modifierGenre(genre, nomGenre);
	}
	
	public Genre obtenirGenre(String nomGenre) {
		return dao.obtenirGenre(nomGenre);
	}
	
	public void effacerGenre(String nomGenre) {
		dao.effacerGenre(nomGenre);
	}

	public ArrayList<Oeuvre> obtenirToutesLesOeuvresParGenre(String nomGenre) {
		return dao.obtenirToutesLesOeuvresParGenre(nomGenre);
	}
	
	public ArrayList<Oeuvre> obtenirToutesLesOeuvres(){
		return dao.obtenirToutesLesOeuvres();
	}

	public void ajouterOeuvreAuGenre(String[] TabIsbn, String nomGenre) {
		dao.ajouterOeuvreAuGenre(TabIsbn, nomGenre);
	}

	public int obtenirNbreOeuvresParGenre(String nomGenre) {
		return dao.obtenirNbreOeuvresParGenre(nomGenre);
	}

}
