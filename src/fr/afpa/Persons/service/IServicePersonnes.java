package fr.afpa.Persons.service;

import java.util.ArrayList;

import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;

public interface IServicePersonnes {

	public ArrayList<Personne> getAll();
	
	public void addPerson(Personne personne);
	
	public void deletePerson(String id);
	
	public void modifyPerson(Personne personne);
	
	public Personne getPerson(String id);
	
	
	public ArrayList<Abonne> obtenirTousLesAbonnes();
	
	public void ajouterAbonne(Abonne abonne);
	
	public void modifierAbonne(Abonne abonne);
	
	public Abonne obtenirAbonne(String id);
	
	public void effacerAbonne(String idAbonne, String idPersonne);
	
	public ArrayList<Copie> consulterAbonne(String idAbonne);
	
	public void retournerCopieAbonne(Copie copie);
	
	
	public ArrayList<Auteur> obtenirTousLesAuteurs();
	
	public int ajouterAuteur(Auteur auteur);
	
	public void modifierAuteur(Auteur auteur);
	
	public Auteur obtenirAuteur(String id);
	
	public Auteur obtenirAuteurParNom(String nomAuteur);
	
	public void effacerAuteur(String idAuteur, String idPersonne);

	public ArrayList<Oeuvre> consulterAuteur(String isbn);
	

}
