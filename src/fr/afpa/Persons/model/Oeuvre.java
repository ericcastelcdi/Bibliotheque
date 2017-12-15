package fr.afpa.Persons.model;

import java.util.Calendar;

public class Oeuvre {
	
// Attributs
	private String isbn;
	private String titreOeuvre;
	private String sousTitreOeuvre;
	private Calendar dateOeuvre;
	private boolean OeuvreDisponible = true;
	
	private Auteur auteur;
	private String nomDuGenre;
	
	private int nbreDeCopies = 0;
	private int nbreDeCopiesDisponibles = 0;

// constructeur 
	public Oeuvre(String isbn, String titreOeuvre, String sousTitreOeuvre, Calendar dateOeuvre, 
			Auteur auteur, String nomDuGenre, int nbreDeCopies, int nbreDeCopiesDisponibles){
		this.isbn = isbn;
		this.titreOeuvre = titreOeuvre;
		this.sousTitreOeuvre = sousTitreOeuvre;	
		this.dateOeuvre = dateOeuvre;
		this.auteur = auteur;
		this.nomDuGenre = nomDuGenre;
		this.nbreDeCopies = nbreDeCopies;
		this.nbreDeCopiesDisponibles = nbreDeCopiesDisponibles;
		
		if (nbreDeCopiesDisponibles == 0){
			OeuvreDisponible = false;
		}else{
			OeuvreDisponible = true;
		}
	}
	
// constructeur 2
	public Oeuvre(String isbn, String titreOeuvre, String sousTitreOeuvre, Calendar dateOeuvre, 
			Auteur auteur, String nomDuGenre){
		this.isbn = isbn;
		this.titreOeuvre = titreOeuvre;
		this.sousTitreOeuvre = sousTitreOeuvre;	
		this.dateOeuvre = dateOeuvre;
		this.auteur = auteur;
		this.nomDuGenre = nomDuGenre;

	}
	
// Methode toString
	public String toString(){
		String messageCopiesDisponibles = "";
		if (nbreDeCopiesDisponibles == 0){
			messageCopiesDisponibles = "Indisponible actuellement";
		}else if (nbreDeCopiesDisponibles == 1) {
			messageCopiesDisponibles = "1 seule copie disponible";
		}else{
			messageCopiesDisponibles = nbreDeCopiesDisponibles + " " + " copies disponibles";
		}
		
		String descriptionOeuvre = "  " + titreOeuvre.toUpperCase() + " - " +  sousTitreOeuvre + " ( " + auteur.getNom() + " " 
									+ auteur.getPrenom().toUpperCase()  + " / Genre : "
									+ nomDuGenre.toString() + " ) - " + messageCopiesDisponibles + " - ISBN " + isbn;
		
		return descriptionOeuvre;	
	}
	
// getters et setters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitreOeuvre() {
		return titreOeuvre;
	}
	public void setTitreOeuvre(String titreOeuvre) {
		this.titreOeuvre = titreOeuvre;
	}

	public String getSousTitreOeuvre() {
		return sousTitreOeuvre;
	}
	public void setSousTitreOeuvre(String sousTitreOeuvre) {
		this.sousTitreOeuvre = sousTitreOeuvre;
	}

	public Calendar getDateOeuvre() {
		return dateOeuvre;
	}
	public void setDateOeuvre(Calendar dateOeuvre) {
		this.dateOeuvre = dateOeuvre;
	}

	public boolean isOeuvreDisponible() {
		return OeuvreDisponible;
	}
	public void setOeuvreDisponible(boolean oeuvreDisponible) {
		OeuvreDisponible = oeuvreDisponible;
	}

	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public int getNbreDeCopies() {
		return nbreDeCopies;
	}
	public void setNbreDeCopies(int nbreDeCopies) {
		this.nbreDeCopies = nbreDeCopies;
	}

	public int getNbreDeCopiesDisponibles() {
		return nbreDeCopiesDisponibles;
	}
	public void setNbreDeCopiesDisponibles(int nbreDeCopiesDisponibles) {
		this.nbreDeCopiesDisponibles = nbreDeCopiesDisponibles;
	}

	public String getNomDuGenre() {
		return nomDuGenre;
	}
	public void setNomDuGenre(String nomDuGenre) {
		this.nomDuGenre = nomDuGenre;
	}
	
	
}
