package fr.afpa.Persons.service;

import java.util.ArrayList;

import fr.afpa.Persons.dao.IDaoPersonnes;
import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;

public class ServicePersonnes implements IServicePersonnes{
	
	private IDaoPersonnes dao;
	
// constructeur
	public ServicePersonnes(IDaoPersonnes dao) {
		this.dao = dao;
	}

// PERSONNES
	public synchronized ArrayList<Personne> getAll() {
		return dao.getAll();
	}

	public synchronized void addPerson(Personne personne) {
		dao.addPerson(personne);
	}

	public synchronized void deletePerson(String id) {
		dao.deletePerson(id);	
	}
	
	public synchronized void modifyPerson(Personne personne) {
		dao.modifyPerson(personne);	
	}

	public synchronized Personne getPerson(String id) {
		return dao.getPerson(id);		
	}

// ABONNES
	public synchronized ArrayList<Abonne> obtenirTousLesAbonnes() {
		 return dao.obtenirTousLesAbonnes();
	}

	public synchronized void ajouterAbonne(Abonne abonne) {
		dao.ajouterAbonne(abonne);
	}
	
	public synchronized void modifierAbonne(Abonne abonne) {
		dao.modifierAbonne(abonne);	
	}

	public synchronized Abonne obtenirAbonne(String id) {
		return dao.obtenirAbonne(id);		
	}
	
	public synchronized void effacerAbonne(String idAbonne, String idPersonne) {
		dao.effacerAbonne(idAbonne, idPersonne);
	}
	
	public synchronized ArrayList<Copie> consulterAbonne(String idAbonne) {
		return dao.consulterAbonne(idAbonne);
	}
	
	public synchronized void retournerCopieAbonne(Copie copie) {
		dao.retournerCopieAbonne(copie);
	}
	
// AUTEURS
	public synchronized ArrayList<Auteur> obtenirTousLesAuteurs() {
		 return dao.obtenirTousLesAuteurs();
	}
	
	public synchronized int ajouterAuteur(Auteur auteur) {
		return dao.ajouterAuteur(auteur);
	}
	
	public synchronized void modifierAuteur(Auteur auteur) {
		dao.modifierAuteur(auteur);	
	}
	
	public synchronized Auteur obtenirAuteur(String id) {
		return dao.obtenirAuteur(id);		
	}

	public synchronized void effacerAuteur(String idAuteur, String idPersonne) {
		dao.effacerAuteur(idAuteur, idPersonne);	
	}

	public synchronized Auteur obtenirAuteurParNom(String nomAuteur) {
		return dao.obtenirAuteurParNom(nomAuteur);
	}

	public synchronized ArrayList<Oeuvre> consulterAuteur(String idAuteur) {
		return dao.consulterAuteur(idAuteur);
	}
 

	
	
}
