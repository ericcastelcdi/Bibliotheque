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
	public ArrayList<Personne> getAll() {
		return dao.getAll();
	}

	public void addPerson(Personne personne) {
		dao.addPerson(personne);
	}

	public void deletePerson(String id) {
		dao.deletePerson(id);	
	}
	
	public void modifyPerson(Personne personne) {
		dao.modifyPerson(personne);	
	}

	public Personne getPerson(String id) {
		return dao.getPerson(id);		
	}

// ABONNES
	public ArrayList<Abonne> obtenirTousLesAbonnes() {
		 return dao.obtenirTousLesAbonnes();
	}

	public void ajouterAbonne(Abonne abonne) {
		dao.ajouterAbonne(abonne);
	}
	
	public void modifierAbonne(Abonne abonne) {
		dao.modifierAbonne(abonne);	
	}

	public Abonne obtenirAbonne(String id) {
		return dao.obtenirAbonne(id);		
	}
	
	public void effacerAbonne(String idAbonne, String idPersonne) {
		dao.effacerAbonne(idAbonne, idPersonne);
	}
	
	public ArrayList<Copie> consulterAbonne(String idAbonne) {
		return dao.consulterAbonne(idAbonne);
	}
	
	public void retournerCopieAbonne(Copie copie) {
		dao.retournerCopieAbonne(copie);
	}
	
// AUTEURS
	public ArrayList<Auteur> obtenirTousLesAuteurs() {
		 return dao.obtenirTousLesAuteurs();
	}
	
	public int ajouterAuteur(Auteur auteur) {
		return dao.ajouterAuteur(auteur);
	}
	
	public void modifierAuteur(Auteur auteur) {
		dao.modifierAuteur(auteur);	
	}
	
	public Auteur obtenirAuteur(String id) {
		return dao.obtenirAuteur(id);		
	}

	public void effacerAuteur(String idAuteur, String idPersonne) {
		dao.effacerAuteur(idAuteur, idPersonne);	
	}

	public Auteur obtenirAuteurParNom(String nomAuteur) {
		return dao.obtenirAuteurParNom(nomAuteur);
	}


	
	
}
