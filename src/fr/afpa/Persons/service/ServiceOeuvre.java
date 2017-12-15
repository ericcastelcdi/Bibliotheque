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
	
// methodes
	public synchronized ArrayList<Oeuvre> obtenirToutesLesOeuvres(){
		return dao.obtenirToutesLesOeuvres();	
	}
	
	public synchronized ArrayList<Copie> obtenirToutesLesCopiesOeuvre(Oeuvre oeuvre) {
		return dao.obtenirToutesLesCopiesOeuvre(oeuvre);
	}

	public synchronized int obtenirNbreCopiesParOeuvre(Oeuvre oeuvre) {
		return dao.obtenirNbreCopiesParOeuvre(oeuvre);
	}

	public synchronized void creerOeuvre(Oeuvre oeuvre) {
		dao.creerOeuvre(oeuvre);		
	}

	public synchronized Oeuvre obtenirOeuvre(String isbn) {
		return dao.obtenirOeuvre(isbn);
	}

	public synchronized Auteur obtenirAuteur(String idAuteur) {		
		return dao.obtenirAuteur(idAuteur);
	}

	public synchronized ArrayList<Auteur> obtenirTousLesAuteurs() {
		return dao.obtenirTousLesAuteurs();
	}

	public synchronized ArrayList<Genre> obtenirTousLesGenres() {
		return dao.obtenirTousLesGenres();
	}

	public synchronized void effacerOeuvre(String isbn) {
		dao.effacerOeuvre(isbn);
	}

	public synchronized void modifierOeuvre(Oeuvre oeuvre) {
		dao.modifierOeuvre(oeuvre);	
	}

	public synchronized void ajouterCopie(String isbn) {
		dao.ajouterCopie(isbn);
	}

	public synchronized void emprunterCopie(Copie copie) {
		dao.emprunterCopie(copie);	
	}

	public synchronized void retournerCopie(Copie copie) {
		dao.retournerCopie(copie);
	}

	public synchronized void reparerCopie(Copie copie) {
		dao.reparerCopie(copie);
	}

	public synchronized void supprimerCopie(int IdCopie) {
		dao.supprimerCopie(IdCopie);
	}

	
}
