package fr.afpa.Persons.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Personne {

	
	private String id ;
	private String prenom;
	private String nom;
	private Calendar dateDeNaissance ;
	

	public Personne(String id, String prenom, String nom, Calendar dateDeNaissance) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;

	}
	
	public Personne(String prenom, String nom, Calendar dateDeNaissance) {
		this.prenom = prenom;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		
	}
			
	public String toString() {
		return "methode toString a definir dans la classe de l'objet";
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	public Calendar getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Calendar dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	
	
}
