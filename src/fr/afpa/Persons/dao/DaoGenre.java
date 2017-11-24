package fr.afpa.Persons.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;

public class DaoGenre implements IDaoGenre{

	private String url = "jdbc:mysql://localhost:3306/bibliotheque?useSSL=false";
	private String login = "root";
	private String passwordConnection = "YD456Moj";
	
	private Connection connection = null;
	
	private Statement statement = null;
	private Statement statement2 = null;
	
	private ResultSet result = null;	
	private ResultSet result2 = null;
	
	public DaoGenre() {
		init();
	}
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
// methiodes anexes
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

// genres
	public ArrayList<Genre> obtenirTousLesGenres() {
		
		ArrayList<Genre> genres = null;
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from catalog ;";
		
			result = statement.executeQuery(query);
			
			genres = new ArrayList<Genre>();
			while (result.next()) {
				String nomGenre = result.getString("catalog_name");
			
			// 2eme requete
				statement2 = connection.createStatement();
				
				String query2 = " select count(isbn_book) as nbBooks "
							  + " from book"
							  + " where book_catalog_name = '" + nomGenre + "';";
			
				result2 = statement2.executeQuery(query2);
				result2.next();
				
				int nbreOeuvres = result2.getInt("nbBooks");
				
				// création de l'objet genre
				Genre genre = new Genre (nomGenre, 0, nbreOeuvres);
				genres.add(genre);
				
				}
		
			result.close();
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return genres;	
	}
	public void ajouterGenre(Genre genre) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();

			String nomGenre = genre.getNomGenre();
			
			String query = " insert into catalog (catalog_name)" 
						   + " values ('" + nomGenre + "');";
					
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void modifierGenre(Genre genre, String nomGenre) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " update catalog " 
						 + " set catalog_name = '" + genre.getNomGenre() + "'" 
						 + " where catalog_name = '" + nomGenre + "' ;";
			
			System.out.println("nom du genre avant modif : " + nomGenre );
			System.out.println("nom du genre modifie : " + genre.getNomGenre() );
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public Genre obtenirGenre(String nomGenre) {

		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from catalog "
						 + " where catalog_name = '" + nomGenre + "' ;";
		
			result = statement.executeQuery(query);
			result.next();

			String nomGenreSql = result.getString("catalog_name");
			
			Genre genre = new Genre (nomGenreSql);			
		
			result.close();
			statement.close();	
			connection.close();
			
			return genre;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	public void effacerGenre(String nomGenre) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " delete from catalog"
						 + " where catalog_name = '" + nomGenre + "';";
		
			System.out.println("id de l'objet person dans methode deletePerson de dao" + nomGenre);
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

// oeuvres du genre
	public ArrayList<Oeuvre> obtenirToutesLesOeuvresParGenre(String nomGenre){
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select book.*, last_name, first_name, count(id_copy) as nbCopy ,id_person, date_of_birth"
							+ " from author, catalog, person, book left join copy on isbn_book = copy_isbn_book"
							+ " where catalog_name = '" + nomGenre + "' "
							+ " and id_person = author_person_id"
							+ " and id_author = book_id_author"
							+ " and catalog_name = book_catalog_name"
							+ " group by isbn_book;";
		
			result = statement.executeQuery(query);
			
			ArrayList<Oeuvre> listeOeuvres = new ArrayList<Oeuvre>();
			
			// tant que j'ai des resultats (oeuvres), je les parcours et je fais ce qui suit pour chacun
			while (result.next()) {
				
				// On construit l'objet Auteur
				String idAuteur = String.valueOf(result.getInt("book_id_author"));
				String idPersonne = result.getString("id_person");
				String prenomPersonne = result.getString("first_name");
				String nomPersonne = result.getString("last_name");
				
				Date dateDeNaissancePersonne = result.getDate("date_of_birth");
				Calendar dateDeNaissancePersonneFormatee = dateEnCalendar(dateDeNaissancePersonne);
				
				Auteur auteur = new Auteur(idPersonne, prenomPersonne, nomPersonne, dateDeNaissancePersonneFormatee, idAuteur);
				
				System.out.println("auteur : "+ prenomPersonne + " " + nomPersonne);
				// On construit l'objet Oeuvre
				String isbnOeuvre = result.getString("isbn_book");
				String titreOeuvre = result.getString("title_book");
				String soutitreOeuvre = result.getString("subtitle_book");
				
				Date dateOeuvre = result.getDate("book_date");	
				// pour avoir l'heure en plus : La classe "Date" est remplac�e par "Timestamp" ,
				// et dans sql "date" devient "datetime".
				// il suffi de rajouter comme valeur dans sql hh::m::ss � la suite de la date yyy-MM-dd
				// Timestamp dateOeuvre = result.getTimestamp("book_datetime"); 	
				// il faut aussi modifier le format d'ecriture de la date "calendar" ,
				// dans la methode to string de la classe de l objet qui comporte la ddate 
				Calendar dateOeuvreFormatee = new GregorianCalendar();
				dateOeuvreFormatee.setTime(dateOeuvre);
			
				int nbreDeCopies =  result.getInt("nbCopy");
							
		// requete 2 ( pour connaitre le nombre de copies disponibles )			
				statement2 = connection.createStatement();
				
				String query2 = "select count(copy_is_available) as nbCopyAv from copy"
								+ " where copy_isbn_book = '" + isbnOeuvre + "'"
								+ " and copy_is_available = 1;";
								
				result2 = statement2.executeQuery(query2);				
				result2.next();		
				
				int nbreDeCopiesDisponibles =  result2.getInt("nbCopyAv");
							
				Oeuvre oeuvre = new Oeuvre(isbnOeuvre, titreOeuvre, soutitreOeuvre, dateOeuvreFormatee, auteur, nomGenre, nbreDeCopies, nbreDeCopiesDisponibles);
				
				listeOeuvres.add(oeuvre);				
			}
			
			statement.close();
			//statement2.close();	
			connection.close();
			
			return listeOeuvres;
			
		} catch (SQLException e) {
			System.out.println("Oups ! Une erreur est survenue : requete nulle");
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Oeuvre> obtenirToutesLesOeuvres() {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select book.*, last_name, first_name, count(id_copy) as nbCopy ,id_person, date_of_birth"
							+ " from author, catalog, person, book left join copy on isbn_book = copy_isbn_book"
							+ " where id_person = author_person_id"
							+ " and id_author = book_id_author"
							+ " and catalog_name = book_catalog_name"
							+ " group by isbn_book;";
		
			result = statement.executeQuery(query);
			
			ArrayList<Oeuvre> listeOeuvres = new ArrayList<Oeuvre>();
			
			// tant que j'ai des resultats (oeuvres), je les parcours et je fais ce qui suit pour chacun
			while (result.next()) {
				
				// On construit l'objet Auteur
				String idAuteur = String.valueOf(result.getInt("book_id_author"));
				String idPersonne = result.getString("id_person");
				String prenomPersonne = result.getString("first_name");
				String nomPersonne = result.getString("last_name");
				
				Date dateDeNaissancePersonne = result.getDate("date_of_birth");
				Calendar dateDeNaissancePersonneFormatee = dateEnCalendar(dateDeNaissancePersonne);
				
				Auteur auteur = new Auteur(idPersonne, prenomPersonne, nomPersonne, dateDeNaissancePersonneFormatee, idAuteur);
				
				System.out.println("auteur : "+ prenomPersonne + " " + nomPersonne);
				// On construit l'objet Oeuvre
				String isbnOeuvre = result.getString("isbn_book");
				String titreOeuvre = result.getString("title_book");
				String soutitreOeuvre = result.getString("subtitle_book");
				
				Date dateOeuvre = result.getDate("book_date");	
				// pour avoir l'heure en plus : La classe "Date" est remplac�e par "Timestamp" ,
				// et dans sql "date" devient "datetime".
				// il suffi de rajouter comme valeur dans sql hh::m::ss � la suite de la date yyy-MM-dd
				// Timestamp dateOeuvre = result.getTimestamp("book_datetime"); 	
				// il faut aussi modifier le format d'ecriture de la date "calendar" ,
				// dans la methode to string de la classe de l objet qui comporte la ddate 
				Calendar dateOeuvreFormatee = new GregorianCalendar();
				dateOeuvreFormatee.setTime(dateOeuvre);
				
				String nomGenre = result.getString("book_catalog_name");
				int nbreDeCopies =  result.getInt("nbCopy");
							
		// requete 2 ( pour connaitre le nombre de copies disponibles )			
				statement2 = connection.createStatement();
				
				String query2 = "select count(copy_is_available) as nbCopyAv from copy"
								+ " where copy_isbn_book = '" + isbnOeuvre + "'"
								+ " and copy_is_available = 1;";
								
				result2 = statement2.executeQuery(query2);				
				result2.next();		
				
				int nbreDeCopiesDisponibles =  result2.getInt("nbCopyAv");
							
				Oeuvre oeuvre = new Oeuvre(isbnOeuvre, titreOeuvre, soutitreOeuvre, dateOeuvreFormatee, auteur, nomGenre, nbreDeCopies, nbreDeCopiesDisponibles);
				
				listeOeuvres.add(oeuvre);				
			}
			
			statement.close();
			//statement2.close();	
			connection.close();
			
			return listeOeuvres;
			
		} catch (SQLException e) {
			System.out.println("Oups ! Une erreur est survenue : requete moisie !");
			e.printStackTrace();
			return null;
		}	
	}

	public void ajouterOeuvreAuGenre(String[] TabIsbn, String nomGenre) {
		
		System.out.println("nom du genre avant : " + nomGenre );
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			for (String isbn :TabIsbn) {
				
				statement = connection.createStatement();
				
				String query = " update book " 
							 + " set book_catalog_name = '" + nomGenre + "'" 
							 + " where isbn_book = '" + isbn + "' ;";
				
				statement.executeUpdate(query);
				statement.close();
			}
			
				
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public int obtenirNbreOeuvresParGenre(String nomGenre) {

		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " select count(isbn_book) as nbBooks "
						 + " from book"
						 + " where book_catalog_name = '" + nomGenre + "';";
		
			result = statement.executeQuery(query);
			
			result.next();
	
			int nbreOeuvres = result.getInt("nbBooks");

			statement.close();
			//statement2.close();	
			connection.close();

			return nbreOeuvres;
			
		} catch (SQLException e) {
			System.out.println("Oups ! Une erreur est survenue : requete nulle");
			e.printStackTrace();
			return 0;
		}
	}

}

