package fr.afpa.Persons.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.afpa.Persons.Commun.MethodesCommunes;

public class Auteur extends Personne {
	
	private String idAuteur;

	public Auteur(String id, String prenom, String nom, Calendar dateDeNaissance, String idAuteur) {
		super(id, prenom, nom, dateDeNaissance);
		this.setIdAuteur(idAuteur) ;
	}
	
	public Auteur(String id, String prenom, String nom, Calendar dateDeNaissance) {
		super(id, prenom, nom, dateDeNaissance);
	}
	
	public Auteur(String prenom, String nom, Calendar dateDeNaissance) {
		super(prenom, nom, dateDeNaissance);
	}
	
	public String toString() {	

        Date date =  super.getDateDeNaissance().getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        
        // System.out.println("date formatee : " + dateString);
        
		return super.getPrenom() + " " + super.getNom().toUpperCase() + " - " + dateString;
		
	}
		
	// getters et setters
	
	public String getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(String idAuteur) {
		this.idAuteur = idAuteur;
	}
	
}
