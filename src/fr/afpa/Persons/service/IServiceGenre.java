package fr.afpa.Persons.service;

import java.util.ArrayList;

import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;

public interface IServiceGenre {

	public ArrayList<Genre> obtenirTousLesGenres();
	
	public void ajouterGenre(Genre genre);
	
	public void modifierGenre(Genre genre, String nomGenre);
	
	public Genre obtenirGenre(String nomGenre);
	
	public void effacerGenre(String nomGenre);
	
	public ArrayList<Oeuvre> obtenirToutesLesOeuvresParGenre(String nomGenre);

	public ArrayList<Oeuvre> obtenirToutesLesOeuvres();	
	
	public void ajouterOeuvreAuGenre(String [] TabIsbn, String nomGenre);
	
	public int obtenirNbreOeuvresParGenre(String nomGenre);
	
}
