package fr.afpa.Persons.service;

import java.util.ArrayList;

import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;

public interface IServiceOeuvre {

	public ArrayList<Copie> obtenirToutesLesCopiesOeuvre(Oeuvre oeuvre);
	public int obtenirNbreCopiesParOeuvre(Oeuvre oeuvre);
	public void ajouterCopie(String isbn);	
	public void emprunterCopie(Copie copie);
	public void retournerCopie(Copie copie);
	public void reparerCopie(Copie copie);
	public void supprimerCopie(int IdCopie);

	public ArrayList<Auteur> obtenirTousLesAuteurs();
	public Auteur obtenirAuteur(String idAuteur);
	
	public ArrayList<Genre> obtenirTousLesGenres();
	
	public ArrayList<Oeuvre> obtenirToutesLesOeuvres();	
	public void creerOeuvre(Oeuvre oeuvre);
	public Oeuvre obtenirOeuvre(String isbn);
	public void effacerOeuvre(String isbn);
	public void modifierOeuvre(Oeuvre oeuvre);
	


}
