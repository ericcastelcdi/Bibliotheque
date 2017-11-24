package fr.afpa.Persons.service;

import java.util.ArrayList;

import fr.afpa.Persons.dao.IDaoOeuvre;
import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;

public class ServiceOeuvre implements IServiceOeuvre {
	
// Attributs
	private IDaoOeuvre dao;
	
// Constructeur
	public ServiceOeuvre(IDaoOeuvre dao) {
		this.dao = dao;
	}
	
	public ArrayList<Oeuvre> obtenirToutesLesOeuvres(){
		return dao.obtenirToutesLesOeuvres();	
	}
	
	public ArrayList<Copie> obtenirToutesLesCopiesDisponiblesOeuvre(Oeuvre oeuvre) {
		return dao.obtenirToutesLesCopiesDisponiblesOeuvre(oeuvre);
	}
	
	public ArrayList<Copie> obtenirToutesLesCopiesOeuvre(Oeuvre oeuvre) {
		return dao.obtenirToutesLesCopiesOeuvre(oeuvre);
	}

	public int obtenirNbreCopiesParOeuvre(Oeuvre oeuvre) {
		return dao.obtenirNbreCopiesParOeuvre(oeuvre);
	}

	public void creerOeuvre(Oeuvre oeuvre) {
		dao.creerOeuvre(oeuvre);		
	}

	public Oeuvre obtenirOeuvre(String isbn) {
		return dao.obtenirOeuvre(isbn);
	}

	public Auteur obtenirAuteur(String idAuteur) {		
		return dao.obtenirAuteur(idAuteur);
	}

	public ArrayList<Auteur> obtenirTousLesAuteurs() {
		return dao.obtenirTousLesAuteurs();
	}

	public ArrayList<Genre> obtenirTousLesGenres() {
		return dao.obtenirTousLesGenres();
	}

	public void effacerOeuvre(String isbn) {
		dao.effacerOeuvre(isbn);
	}

	public void modifierOeuvre(Oeuvre oeuvre) {
		dao.modifierOeuvre(oeuvre);	
	}

	public void ajouterCopie(String isbn) {
		dao.ajouterCopie(isbn);
	}

	public void emprunterCopie(Copie copie) {
		dao.emprunterCopie(copie);	
	}

	public void retournerCopie(Copie copie) {
		dao.retournerCopie(copie);
	}

	public void reparerCopie(Copie copie) {
		dao.reparerCopie(copie);
	}

	public void supprimerCopie(int IdCopie) {
		dao.supprimerCopie(IdCopie);
	}

	
}
