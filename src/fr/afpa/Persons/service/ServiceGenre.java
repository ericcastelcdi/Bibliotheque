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

// methodes
	
	// synchronized sert à eviter les erreurs dues à des executions de plus utilisateurs en meme temps et qui donc vont interroger la base de donnée en meme temps
	public synchronized ArrayList<Genre> obtenirTousLesGenres(){
		return dao.obtenirTousLesGenres();
	}
	
	public synchronized void ajouterGenre(Genre genre) {
		dao.ajouterGenre(genre);
	}
	
	public synchronized void modifierGenre(Genre genre, String nomGenre) {
		dao.modifierGenre(genre, nomGenre);
	}
	
	public synchronized Genre obtenirGenre(String nomGenre) {
		return dao.obtenirGenre(nomGenre);
	}
	
	public synchronized void effacerGenre(String nomGenre) {
		dao.effacerGenre(nomGenre);
	}

	public synchronized ArrayList<Oeuvre> obtenirToutesLesOeuvresParGenre(String nomGenre) {
		return dao.obtenirToutesLesOeuvresParGenre(nomGenre);
	}
	
	public synchronized ArrayList<Oeuvre> obtenirToutesLesOeuvres(){
		return dao.obtenirToutesLesOeuvres();
	}

	public synchronized void ajouterOeuvreAuGenre(String[] TabIsbn, String nomGenre) {
		dao.ajouterOeuvreAuGenre(TabIsbn, nomGenre);
	}

	public synchronized int obtenirNbreOeuvresParGenre(String nomGenre) {
		return dao.obtenirNbreOeuvresParGenre(nomGenre);
	}

}
