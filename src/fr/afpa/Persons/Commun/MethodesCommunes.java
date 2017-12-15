package fr.afpa.Persons.Commun;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MethodesCommunes {

// Methodes pour la conversion des dates
	public Calendar dateEnCalendar(Date date) {    
		
		// formatage en calendar pour insertion dans l'objet personne
		Calendar dateFormatee = new GregorianCalendar();
		dateFormatee.setTime(date);
		//System.out.println("date au format calendar " + dateFormatee);
		
		return dateFormatee;
	}	
	public String dateEnString(Date date) {  //("dd/MM/yyyy")  

		/// reformatage de la date avant de la mettre dans une variable string
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String dateString = dateFormat.format(date);
		
		return dateString;
	}	
	public String calendarEnStringSql(Calendar date) {
		
		Date dateDate = date.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateDeNaissanceString = dateFormat.format(dateDate);
        
		return dateDeNaissanceString;
	}
	public String dateEnStringSql(Date date) {  //("yyyy-MM-dd")  

		/// reformatage de la date avant de la mettre dans une variable string
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = dateFormat.format(date);
	    System.out.println("date au format string " + dateString);

		return dateString;
	}
	public Calendar toCalendar(String dateString, String pattern) throws java.text.ParseException {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = format.parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
