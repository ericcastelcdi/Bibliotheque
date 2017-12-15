package fr.afpa.Persons.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Abonne extends Personne{
	
// attributs
	private String idAbonne;

// constructeurs
	public Abonne(String id, String prenom, String nom, Calendar dateDeNaissance, String idAbonne) {
		super(id, prenom, nom, dateDeNaissance);
		this.setIdAbonne(idAbonne) ;
	}
	
	public Abonne(String id, String prenom, String nom, Calendar dateDeNaissance) {
		super(id, prenom, nom, dateDeNaissance);
	}
	
// methode to string
	public String toString() {
	
		if (super.getDateDeNaissance() != null) {	
			
			Date date =  super.getDateDeNaissance().getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = sdf.format(date);
			
			if (super.getPrenom() != null) {
				return super.getPrenom() + " " + super.getNom() + " - " + dateString ;
			}else {
				return super.getNom() + " - " + dateString ;
			}
		}else {
			if (super.getPrenom() != null) {
				return super.getPrenom() + " " + super.getNom();
			}else {
				return super.getNom();
			}	
		}
	}
		
// getters et setters
	
	public String getIdAbonne() {
		return idAbonne;
	}

	public void setIdAbonne(String idAbonne) {
		this.idAbonne = idAbonne;
	}
	
}
