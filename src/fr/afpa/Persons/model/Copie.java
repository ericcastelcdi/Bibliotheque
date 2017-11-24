package fr.afpa.Persons.model;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class Copie {
	
	private int idCopie = 1;
	private Calendar dateEmprunt ;
	private Calendar dateRetour ;
	private boolean copieEstDisponible = true;
	private boolean copieEstEnReparation = false;
	private boolean copieVientDEtreReparee = false;
	private String isbnOeuvre;
	private String titreOeuvre;
	private String soustitreOeuvre;
	private int idAbonne;
	private String prenomAbonne;
	private String nomAbonne;
	
// Constructeur 1	
	public Copie(int idCopie, Calendar dateEmprunt, Calendar dateRetour, boolean copieEstDisponible, boolean copieVientDEtreReparee,
					boolean copieEstEnReparation, String isbnOeuvre, String titreOeuvre, String soustitreOeuvre, int idAbonne, String prenomAbonne, String nomAbonne) {
		this.idCopie = idCopie;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		this.copieEstDisponible = copieEstDisponible;
		this.copieEstEnReparation = copieEstEnReparation;
		this.copieVientDEtreReparee = copieVientDEtreReparee;
		this.isbnOeuvre = isbnOeuvre;
		this.titreOeuvre = titreOeuvre;
		this.soustitreOeuvre = soustitreOeuvre;
		this.prenomAbonne = prenomAbonne;
		this.nomAbonne = nomAbonne;
		this.idAbonne = idAbonne;
	}
	
// Constructeur 2	
	public Copie(int idCopie, Calendar dateEmprunt, int idAbonne) {
		this.idCopie = idCopie;
		this.dateEmprunt = dateEmprunt;
		this.idAbonne = idAbonne;
	}
	
// Constructeur 3	
	public Copie(int idCopie, Calendar dateRetour) {
		this.idCopie = idCopie;
		this.dateRetour = dateRetour;
	}	
	
// Constructeur 4	
	public Copie(int idCopie, Calendar dateEmprunt, boolean copieEstEnReparation, boolean copieVientDEtreReparee) {
		this.idCopie = idCopie;
		this.dateEmprunt = dateEmprunt;
		this.copieEstEnReparation = copieEstEnReparation;
		this.copieVientDEtreReparee = copieVientDEtreReparee;
	}	

	public String toString(){
		// dateFormatSymbols sert a obtenir par la suite année , jour , mois etc... de manière séparée (par exemple .get(Calendar.YEAR))
		DateFormatSymbols symbols = new DateFormatSymbols(Locale.FRANCE);
		
// EMPRUNTS
	int anneeEmprunt = 0;
	int chiffreMoisEmprunt = 0;
	String nomMoisEmprunt = "";
	int numeroJourAnneeEmprunt = 0;
	int numeroJourSemaineEmprunt = 0;
	String nomJourSemaineEmprunt = "";
	
	if (dateEmprunt != null) {
		anneeEmprunt = dateEmprunt.get(Calendar.YEAR);
		
		chiffreMoisEmprunt = dateEmprunt.get(Calendar.MONTH) + 1;
		switch (chiffreMoisEmprunt){
		  case 1:nomMoisEmprunt = "janvier";break;
		  case 2:nomMoisEmprunt = "février";break;
		  case 3:nomMoisEmprunt = "mars";break;
		  case 4:nomMoisEmprunt = "avril";break;
		  case 5:nomMoisEmprunt = "mai";break;
		  case 6:nomMoisEmprunt = "juin";break;
		  case 7:nomMoisEmprunt = "juillet";break;
		  case 8:nomMoisEmprunt = "août";break;
		  case 9:nomMoisEmprunt = "septembre";break;
		  case 10:nomMoisEmprunt = "octobre";break;
		  case 11:nomMoisEmprunt = "novembre";break;
		  case 12:nomMoisEmprunt = "décembre";break;
		}
		
		numeroJourAnneeEmprunt = dateEmprunt.get(Calendar.DATE);
	
		numeroJourSemaineEmprunt = dateEmprunt.get(Calendar.DAY_OF_WEEK);
		nomJourSemaineEmprunt = symbols.getWeekdays()[numeroJourSemaineEmprunt];
	}
		
// RETOURS	
	int anneeRetour = 0;
	int chiffreMoisRetour = 0;
	String nomMoisRetour = "";
	int numeroJourAnneeRetour = 0;
	int numeroJourSemaineRetour = 0;
	String nomJourSemaineRetour = "";
	
	if (dateRetour != null) {
		anneeRetour = dateRetour.get(Calendar.YEAR);

		chiffreMoisRetour = dateRetour.get(Calendar.MONTH) + 1;
		switch (chiffreMoisRetour){
		  case 1:nomMoisRetour = "janvier";break;
		  case 2:nomMoisRetour = "février";break;
		  case 3:nomMoisRetour = "mars";break;
		  case 4:nomMoisRetour = "avril";break;
		  case 5:nomMoisRetour = "mai";break;
		  case 6:nomMoisRetour = "juin";break;
		  case 7:nomMoisRetour = "juillet";break;
		  case 8:nomMoisRetour = "août";break;
		  case 9:nomMoisRetour = "septembre";break;
		  case 10:nomMoisRetour = "octobre";break;
		  case 11:nomMoisRetour = "novembre";break;
		  case 12:nomMoisRetour = "décembre";break;
		}
		
		numeroJourAnneeRetour = dateRetour.get(Calendar.DATE);	
		
		numeroJourSemaineRetour = dateRetour.get(Calendar.DAY_OF_WEEK);
		nomJourSemaineRetour = symbols.getWeekdays()[numeroJourSemaineRetour];
	}
		
		if (copieEstDisponible == true){
			if (dateRetour != null){
				if (copieVientDEtreReparee == false) {
					return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Disponible - Retournée depuis " 
								+ nomJourSemaineRetour + " " + numeroJourAnneeRetour + " " + nomMoisRetour + " " + anneeRetour
								+ " par " + prenomAbonne + " " + nomAbonne + " ( id " + idAbonne + " ) ";
				}else {
					return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Disponible - Copie restaurée, revenue de réparation depuis " 
							+ nomJourSemaineRetour + " " + numeroJourAnneeRetour + " " + nomMoisRetour + " " + anneeRetour;
				}
			}else{
				if (copieVientDEtreReparee == false) {
					if (dateEmprunt == null){
						return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Disponible - Copie neuve, jamais empruntée";
					}else{
						return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Disponible - date de retour non renseignée ";
					}
				}else {
					return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Disponible - Copie restaurée, revenue de réparation depuis " 
							+ nomJourSemaineRetour + " " + numeroJourAnneeRetour + " " + nomMoisRetour + " " + anneeRetour;
				}
			}
		}else{	
			if (copieEstEnReparation == true ){
				return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Indisponible - En réparation depuis "
							+ nomJourSemaineEmprunt + " " + numeroJourAnneeEmprunt + " " + nomMoisEmprunt + " " + anneeEmprunt;
			}
			
			if (dateEmprunt != null){
				if (dateRetour != null){
					return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Indisponible - Empruntée depuis " 
								+ nomJourSemaineEmprunt + " " + numeroJourAnneeEmprunt + " " + nomMoisEmprunt + " " + anneeEmprunt 
								+ " par " + prenomAbonne + " " + nomAbonne + " ( id " + idAbonne + " ) ";
				}else{
					return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Indisponible - Copie neuve, premier emprunt en cours depuis "
								+ nomJourSemaineEmprunt + " " + numeroJourAnneeEmprunt + " " + nomMoisEmprunt + " " + anneeEmprunt 
								+ " par " + prenomAbonne + " " + nomAbonne + " ( id " + idAbonne + " ) ";
				}		
			}else{
				return titreOeuvre.toUpperCase() + " - " + soustitreOeuvre + " - N°" + idCopie + " / Indisponible ";
			}
		}	
	}
	
	// getters et setters

	public int getIdCopie() {
		return idCopie;
	}
	public void setIdCopie(int idCopie) {
		this.idCopie = idCopie;
	}

	
	public Calendar getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Calendar dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	
	
	public Calendar getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Calendar dateRetour) {
		this.dateRetour = dateRetour;
	}

	
	public boolean isCopieEstDisponible() {
		return copieEstDisponible;
	}
	public void setCopieEstDisponible(boolean copieEstDisponible) {
		this.copieEstDisponible = copieEstDisponible;
	}

	
	public boolean isCopieEstEnReparation() {
		return copieEstEnReparation;
	}
	public void setCopieEstEnReparation(boolean copieEstEnReparation) {
		this.copieEstEnReparation = copieEstEnReparation;
	}

	
	public String getIsbnOeuvre() {
		return isbnOeuvre;
	}
	public void setIsbnOeuvre(String isbnOeuvre) {
		this.isbnOeuvre = isbnOeuvre;
	}

	
	public int getIdAbonne() {
		return idAbonne;
	}
	public void setIdAbonne(int idAbonne) {
		this.idAbonne = idAbonne;
	}
	

}
