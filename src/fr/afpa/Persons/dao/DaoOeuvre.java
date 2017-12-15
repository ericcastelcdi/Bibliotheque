package fr.afpa.Persons.dao;

//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import fr.afpa.Persons.Commun.MethodesCommunes;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;

public class DaoOeuvre extends MethodesCommunes implements IDaoOeuvre {
	
// Attributs
	// adresse du serveur de la base de données
	private String url = "jdbc:mysql://localhost:3306/bibliotheque?useSSL=false";
	// info de connection de mon mysql (on ne doit pas normalement les mettres la)
	private String login = "root";
	
	private String passwordConnection = "YD456Moj";
	// sert a etablir la connection a la base
	private java.sql.Connection connection = null;
	// se charge de prendre la requete de l'executer et renvoyer le resultat
	private java.sql.Statement statement = null;
	private java.sql.Statement statement2 = null;
	// objet qui va contenir le resultat de la requete
	private ResultSet result = null;
	private ResultSet result2 = null;

	int lastId = 0;
	int lastIdAbonne = 0;
	int lastIdAuteur = 0;
	
// Constructeur
	public DaoOeuvre() {

		init();
	}	
	public void init() {
		try {
			// Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

// copies
	public ArrayList<Copie> obtenirToutesLesCopiesOeuvre(Oeuvre oeuvre) {
		
		System.out.println("entré dans 'obtenirToutesLesCopiesOeuvre' de daoOeuvre ok ");
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " select copy.*"
						 + " from copy, book"
						 + " where title_book = '" + oeuvre.getTitreOeuvre() + "' "
						 + " and isbn_book = copy_isbn_book ;";
			
			result = statement.executeQuery(query);
			
			ArrayList<Copie> listCopies = new ArrayList<Copie>();
			
			// tant que j'ai des resultats (copies), je les parcours et je fais ce qui suit pour chacun
			while (result.next()) {
				
				// On construit l'objet Copie
				int idCopie = result.getInt("id_copy");
				
				Date dateEmprunt = result.getDate("date_of_borrowing");
					Calendar dateEmpruntFormatee = new GregorianCalendar();	
					
					if ( dateEmprunt != null ){	
						dateEmpruntFormatee.setTime(dateEmprunt);
					}else{
						dateEmpruntFormatee = null;
					}
				
				Date dateRetour = result.getDate("date_of_back");
					Calendar dateRetourFormatee = new GregorianCalendar();	
					if ( dateRetour != null ){							
						dateRetourFormatee.setTime(dateRetour);
					}else{
						dateRetourFormatee = null;
					}
				
				boolean copieEstDisponible = false;		
					int copieEstDisponibleTinyInt = result.getInt("copy_is_available");
						if (copieEstDisponibleTinyInt == 1){
							copieEstDisponible = true;
						}else{
							copieEstDisponible = false;
						}
					
				boolean copieEstEnReparation = false;	
					int copieEstEnReparationTinyInt = result.getInt("copy_is_on_repairing");
						if (copieEstEnReparationTinyInt == 1){
							copieEstEnReparation = true;
						}else{
							copieEstEnReparation = false;
						}
						
				boolean copieVientDEtreReparee = false;	
				int copieVientDEtreRepareeTinyInt = result.getInt("copy_is_freshly_repeared");
					if (copieVientDEtreRepareeTinyInt == 1){
						copieVientDEtreReparee = true;
					}else{
						copieVientDEtreReparee = false;
					}
				
				String idAbonne = result.getString("copy_id_subscriber");
				int idAbonneInt = 0;
				String prenomAbonne = null;
				String nomAbonne = null;
				
				System.out.println("id abonné : " +  idAbonne);
				
				if (idAbonne != null) {
				// 2eme requete pour avoir les infos des abonnés si la copie est louée
					
					statement2 = connection.createStatement();
					
					String query2 = " select last_name, first_name "
									+ " from subscriber, person"
									+ " where subscriber_person_id = id_person"
									+ " and id_subscriber = '" + idAbonne + "';";
					
					result2 = statement2.executeQuery(query2);
					result2.next();
		
					idAbonneInt = Integer.valueOf(idAbonne);
					prenomAbonne = result2.getString("last_name");
					nomAbonne = result2.getString("first_name");
				}
			
				String titreOeuvre = oeuvre.getTitreOeuvre();
				String soustitreOeuvre = oeuvre.getSousTitreOeuvre();	
				String isbnOeuvre = oeuvre.getIsbn();
								
				Copie copie = new Copie (idCopie, dateEmpruntFormatee, dateRetourFormatee, copieEstDisponible, copieVientDEtreReparee,
						copieEstEnReparation, isbnOeuvre, titreOeuvre, soustitreOeuvre, idAbonneInt , prenomAbonne, nomAbonne);
				
				listCopies.add(copie);				
			}
			
			result.close();
			statement.close();
			result2.close();
			statement2.close();	
			connection.close();
			
			return listCopies;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	public int obtenirNbreCopiesParOeuvre(Oeuvre oeuvre) {
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select count(id_copy) as nbCopy"
							+ " from copy, book"
							+ " where isbn_book = '" + oeuvre.getIsbn() + "' "
							+ " and copy_isbn_book = isbn_book;";
			
			result = statement.executeQuery(query);
			result.next();
			
			int nbCopies = result.getInt("nbCopy");
			System.out.println(nbCopies);
			
			statement.close();
			connection.close();
			
			return nbCopies;

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return 0;	
	}
	public void ajouterCopie(String isbn) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
		
			// pas besoin d 'incrementer ni de renseigner l'id de la copie lors de sa creation puisque elle s'auto incremente automatiquement depuis la base sql

			String query = " insert into copy (copy_is_available, copy_is_on_repairing, copy_isbn_book)" 
						 + " values (1, 0, '" + isbn + "');";
					
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void emprunterCopie(Copie copie) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();

			String query = " select copy_is_available, copy_is_on_repairing"
						 + " from copy"
						 + " where id_copy = " + copie.getIdCopie() + ";";
			
			result = statement.executeQuery(query);
			result.next();
			
			int available = result.getInt("copy_is_available");
			int repairing = result.getInt("copy_is_on_repairing");
			
			statement2 = connection.createStatement();
			
			statement2 = connection.createStatement();
			
			if (available == 1 & repairing == 0) {
				
				String dateSql = calendarEnStringSql(copie.getDateEmprunt()); // calendarEnString est une methode de la classe mere "MethodesCommunes"
				
				String query2 = " update copy"
							 + " set date_of_borrowing = '" + dateSql + "',"
							 + " copy_id_subscriber = '" + copie.getIdAbonne() + "',"
							 + " copy_is_available = 0 "
							 + " where id_copy = " + copie.getIdCopie() + ";";
				
				System.out.println("requete emprunt effectuée");
				
				statement2.executeUpdate(query2);
			}
			
			statement.close();	
			statement2.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void retournerCopie(Copie copie) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " select copy_is_on_repairing, copy_is_available "
						 + " from copy"
						 + " where id_copy = " + copie.getIdCopie() + ";";
			
			result = statement.executeQuery(query);
			result.next();
			
			int disponible = result.getInt("copy_is_available");
			int repairing = result.getInt("copy_is_on_repairing");	
						
			String dateSql = calendarEnStringSql(copie.getDateRetour());
			
			statement2 = connection.createStatement();
			if (disponible == 0) {
				if (repairing == 0) {
					
					String query2 = " update copy"
								  + " set date_of_back = '" + dateSql + "',"
								  + " copy_is_available = 1 ,"
								  + " copy_is_freshly_repeared = 0 "
								  + " where id_copy = " + copie.getIdCopie() + ";";
					
					System.out.println("requete retour effectuée");
					
					statement2.executeUpdate(query2);
					
				}else {
					
					String query2 = " update copy"
								  + " set date_of_back = '" + dateSql + "',"
								  + " copy_is_available = 1 ,"
								  + " copy_is_on_repairing = 0 ,"
								  + " copy_is_freshly_repeared = 1 "
								  + " where id_copy = " + copie.getIdCopie() + ";";
				
					System.out.println("requete retour effectuée");
				
					statement2.executeUpdate(query2);
				}
			}
			
			statement.close();	
			statement2.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void reparerCopie(Copie copie) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();

			String query = " select copy_is_on_repairing, copy_is_available"
						 + " from copy"
						 + " where id_copy = " + copie.getIdCopie() + ";";
			
			result = statement.executeQuery(query);
			result.next();
			
			int repairing = result.getInt("copy_is_on_repairing");
			int available = result.getInt("copy_is_available");
			
			statement2 = connection.createStatement();
			
			if (repairing == 0 & available == 1) {
				String dateSql = calendarEnStringSql(copie.getDateEmprunt());
				
				String query2 = " update copy"
							  + " set date_of_borrowing = '" + dateSql + "',"
							  + " copy_is_available = 0 ,"
							  + " copy_is_on_repairing = 1 "
							  + " where id_copy = " + copie.getIdCopie() + ";";
				
				System.out.println("requete reparation effectuée");
				
				statement2.executeUpdate(query2);
			}
			
			statement.close();	
			statement2.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void supprimerCopie(int idCopie) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();

			String query = " delete from copy"
						 + " where id_copy = " + idCopie + ";";
			
			System.out.println("requete suppression effectuée");
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Auteurs	
	public ArrayList<Auteur> obtenirTousLesAuteurs(){

		ArrayList<Auteur> auteurs = null;
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from author, person"
						 + " where id_person = author_person_id;";
		
			result = statement.executeQuery(query);
			
			auteurs = new ArrayList<Auteur>();
			
			while (result.next()) {
				String id = result.getString("id_person");
				String prenom = result.getString("first_name");
				String nom = result.getString("last_name");
				String idAuteur = result.getString("id_author");
				
				Date dateDeNaissance = result.getDate("date_of_birth");
				// appel de la methode pour formater en calendar la date
				Calendar dateFormatee = dateEnCalendar(dateDeNaissance);
				
				Auteur auteur = new Auteur (id, prenom, nom, dateFormatee, idAuteur);
				
				auteurs.add(auteur);
				if (Integer.valueOf(auteur.getIdAuteur()) > lastIdAuteur) {
					lastIdAuteur = Integer.valueOf(auteur.getIdAuteur());	
				}
			}
			
			System.out.println("dernier index de la liste auteur dans obtenir tous les auteurs de dao : " + lastIdAuteur);
			
			result.close();
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return auteurs;	
	}
	public Auteur obtenirAuteur(String id){
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from author, person "
						 + " where id_author = " + id 
						 + " and author_person_id = id_person ;";
		
			result = statement.executeQuery(query);
			result.next();
		
			String idPersonne = result.getString("author_person_id"); 	
			String prenom = result.getString("first_name");
			String nom = result.getString("last_name");
			
			Date dateDeNaissance = result.getDate("date_of_birth");
			// appel de la methode pour formater en calendar la date
			Calendar dateFormatee = dateEnCalendar(dateDeNaissance);
			
			// System.out.println(dateFormatee);
			Auteur auteur = new Auteur (idPersonne, prenom, nom, dateFormatee,  id);			
		
			statement.close();
			connection.close();
			
			return auteur;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
	}

// genres	
	public ArrayList<Genre> obtenirTousLesGenres(){
		
		ArrayList<Genre> genres = null;
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from catalog";
		
			result = statement.executeQuery(query);
			
			genres = new ArrayList<Genre>();
			
			while (result.next()) {
				
				String nomGenre = result.getString("catalog_name");
		
				Genre genre = new Genre (nomGenre);
				
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

// oeuvres	
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
				Calendar dateDeNaissancePersonneFormatee = null;
				if ( dateDeNaissancePersonne != null) {
					dateDeNaissancePersonneFormatee = dateEnCalendar(dateDeNaissancePersonne);
				}
				
				
				Auteur auteur = new Auteur(idPersonne, prenomPersonne, nomPersonne, dateDeNaissancePersonneFormatee, idAuteur);
				
				System.out.println("auteur : "+ prenomPersonne + " " + nomPersonne);
				// On construit l'objet Oeuvre
				String isbnOeuvre = result.getString("isbn_book");
				String titreOeuvre = result.getString("title_book");
				String soutitreOeuvre = result.getString("subtitle_book");
				
				Date dateOeuvre = result.getDate("book_date");	
				// pour avoir l'heure en plus : La classe "Date" est remplacée par "Timestamp" ,
				// et dans sql "date" devient "datetime".
				// il suffi de rajouter comme valeur dans sql hh::m::ss à la suite de la date yyy-MM-dd
				// Timestamp dateOeuvre = result.getTimestamp("book_datetime"); 	
				// il faut aussi modifier le format d'ecriture de la date "calendar" ,
				// dans la methode to string de la classe de l objet qui comporte la ddate 
				Calendar dateOeuvreFormatee = null;
				if ( dateOeuvre != null) {
					dateOeuvreFormatee = new GregorianCalendar();
					dateOeuvreFormatee.setTime(dateOeuvre);
				}
				
				
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
			System.out.println("Oups ! Une erreur est survenue : requete nulle");
			e.printStackTrace();
			return null;
		}	
	}
	public void creerOeuvre(Oeuvre oeuvre) {	
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			Calendar dateOeuvreCalendar = oeuvre.getDateOeuvre();
			String dateString = calendarEnStringSql(dateOeuvreCalendar);
			
				
			String query = " insert into book (isbn_book, title_book, subtitle_book, book_date, book_Is_available, book_id_author, book_catalog_name)" 
						   + " values ('" + oeuvre.getIsbn() + "', '" + oeuvre.getTitreOeuvre() + "', '" + oeuvre.getSousTitreOeuvre() + "', '" 
						   + dateString + "', 1, '" + oeuvre.getAuteur().getIdAuteur() + "', '" + oeuvre.getNomDuGenre() + "' );";
		
			System.out.println("Oeuvre dans la methode ajouterOeuvre de dao : " + oeuvre.getIsbn() + " " + oeuvre.getTitreOeuvre() + " " + oeuvre.getSousTitreOeuvre() + " " + dateString);
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Oeuvre obtenirOeuvre(String isbn) {
	
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select book.*, last_name, first_name, count(id_copy) as nbCopy ,id_person, date_of_birth"
							+ " from author, catalog, person, book left join copy on isbn_book = copy_isbn_book"
							+ " where id_person = author_person_id"
							+ " and id_author = book_id_author"
							+ " and catalog_name = book_catalog_name"
							+ " and isbn_book = '" + isbn + "';";
		
			result = statement.executeQuery(query);
			result.next();
	
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
			String titreOeuvre = result.getString("title_book");
			String soutitreOeuvre = result.getString("subtitle_book");
			
			Date dateOeuvre = result.getDate("book_date");	
			// pour avoir l'heure en plus : La classe "Date" est remplac�e par "Timestamp" ,
			// et dans sql "date" devient "datetime".
			// il suffi de rajouter comme valeur dans sql hh::m::ss � la suite de la date yyy-MM-dd
			// Timestamp dateOeuvre = result.getTimestamp("book_datetime"); 	
			// il faut aussi modifier le format d'ecriture de la date "calendar" ,
			// dans la methode to string de la classe de l objet qui comporte la ddate 
			Calendar dateOeuvreFormatee = null;
			if ( dateOeuvre != null) {
				dateOeuvreFormatee = new GregorianCalendar();
				dateOeuvreFormatee.setTime(dateOeuvre);
			}
			
			
			String nomGenre = result.getString("book_catalog_name");
			int nbreDeCopies =  result.getInt("nbCopy");
						
		// requete 2 ( pour connaitre le nombre de copies disponibles )			
			statement2 = connection.createStatement();
			
			String query2 = " select count(copy_is_available) as nbCopyAv from copy"
						  + " where copy_isbn_book = '" + isbn + "'"
						  + " and copy_is_available = 1;";
							
			result2 = statement2.executeQuery(query2);				
			result2.next();		
			
			int nbreDeCopiesDisponibles =  result2.getInt("nbCopyAv");
						
			Oeuvre oeuvre = new Oeuvre(isbn, titreOeuvre, soutitreOeuvre, dateOeuvreFormatee, auteur, nomGenre, nbreDeCopies, nbreDeCopiesDisponibles);
			
			
			statement.close();	
			statement2.close();	
			connection.close();
			
			return oeuvre;
					
		} catch (SQLException e) {
			System.out.println("erreur sql dans obtenirOeuvre de daoOeuvre");
			e.printStackTrace();
		}
		return null;		
		
	}
	public void modifierOeuvre(Oeuvre oeuvre){
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			System.out.println("id de l'auteur de l'oeuvre dans modifierOeuvre de dao oeuvre: id " + oeuvre.getAuteur().getIdAuteur());
			
			String dateSql = calendarEnStringSql( oeuvre.getDateOeuvre() );
			
			String query = " update book " 
						 + " set title_book = '" + oeuvre.getTitreOeuvre() + "' ," 
						 + " subtitle_book = '" + oeuvre.getSousTitreOeuvre() + "' ,"
						 + " book_date = '" + dateSql + "' ,"
						 + " book_catalog_name = '" + oeuvre.getNomDuGenre() + "' ,"
						 + " book_id_author = " + oeuvre.getAuteur().getIdAuteur()
						 + " where isbn_book = '" + oeuvre.getIsbn() + "' ;";
					
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void effacerOeuvre(String isbn) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " delete from book"
						 + " where isbn_book = '" + isbn + "';";
		
			System.out.println("id de l'objet person dans methode deletePerson de dao" + isbn);
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
