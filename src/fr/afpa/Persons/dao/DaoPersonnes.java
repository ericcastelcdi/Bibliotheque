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
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;

public class DaoPersonnes implements IDaoPersonnes{

	private String url = "jdbc:mysql://localhost:3306/bibliotheque?useSSL=false";
	private String login = "root";
	private String passwordConnection = "YD456Moj";
	
	private Connection connection = null;
	
	private Statement statement = null;
	private Statement statement2 = null;
	private Statement statement3 = null;
	
	private ResultSet result = null;	
	private ResultSet result2 = null;
	private ResultSet result3 = null;
	
	int lastId = 0;
	int lastIdAbonne = 0;
	int lastIdAuteur = 0;
	
	public DaoPersonnes() {
		init();
	}
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

// Methodes anexes
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

// personnes	
	public ArrayList<Personne> getAll(){
		
		ArrayList<Personne> personnes = null;
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from person ;";
		
			result = statement.executeQuery(query);
			
			personnes = new ArrayList<Personne>();
			while (result.next()) {
				String id = result.getString("id_person");
				String prenom = result.getString("first_name");
				String nom = result.getString("last_name");
	
				// recuperation de la date au format date de la base sql
				Date dateDeNaissance = result.getDate("date_of_birth");
				Calendar dateFormatee = null;
				if (dateDeNaissance != null) {
					// appel de la methode pour formater en calendar la date
					dateFormatee = dateEnCalendar(dateDeNaissance);
				}
				
				// création de l'objet personne
				Personne personne = new Personne (id, prenom, nom, dateFormatee);
				personnes.add(personne);
				
				// Vérification de la valeur du dernier id utilisé dans la liste de personnes
				if (Integer.valueOf(personne.getId()) > lastId) {
					lastId = Integer.valueOf(personne.getId());	
				}
			}
			
			System.out.println("dernier index de la liste personne dans getAll de dao : " + lastId);
			
			result.close();
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return personnes;	
	}		
	public void addPerson(Personne personne) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			int id = lastId + 1;
			
			// de calendar en date
			Date dateDate = personne.getDateDeNaissance().getTime();
			// de date en string format� en ("yyyy-MM-dd") pour mysql
			String datestring = dateEnStringSql(dateDate);
					
			String query = " insert into person (id_person, first_name, last_name, date_of_birth)" 
						   + " values ('" + id + "', '" + personne.getPrenom() + "', '" + personne.getNom() + "', '" + datestring + "');";
		
			System.out.println("nom et prenom de l'objet person dans methode addperson de dao" + personne.getPrenom() + " " + personne.getNom() + " " + datestring);
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	public void deletePerson(String id) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " delete from person"
						 + " where id_person = '" + id + "';";
		
			System.out.println("id de l'objet person dans methode deletePerson de dao" + id);
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void modifyPerson(Personne personne) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String dateDeNaissanceString = calendarEnStringSql(personne.getDateDeNaissance());
			
			System.out.println(" date formatee : " + dateDeNaissanceString);
				
			String query = " update person " 
						 + " set first_name = '" + personne.getPrenom() + "'," 
						 + " last_name = '" + personne.getNom() + "' ,"
						 + " date_of_birth = '" + dateDeNaissanceString + "'"
						 + " where id_person = '" + personne.getId() + "' ;";
					
			System.out.println("objet person dans methode modifyPerson de dao - id : " + personne.getId());
			System.out.println("objet person dans methode modifyPerson de dao - id : " + personne.getId() + ", prenom : " + personne.getPrenom() + ", nom : " 
																					   + personne.getNom() + ", date : " + dateDeNaissanceString );
			
			statement.executeUpdate(query);
			
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Personne getPerson(String id) {

		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from person "
						 + " where id_person = '" + id + "' ;";
		
			result = statement.executeQuery(query);
			result.next();
			
			String prenom = result.getString("first_name");
			String nom = result.getString("last_name");
			
			Date dateDeNaissance = result.getDate("date_of_birth");
			
			// appel de la methode pour formater en calendar la date
			Calendar dateFormatee = dateEnCalendar(dateDeNaissance);
			
			Personne personne = new Personne (id, prenom, nom, dateFormatee);			
		
			result.close();
			statement.close();	
			connection.close();
			
			return personne;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
		
// auteurs	
	public ArrayList<Auteur> obtenirTousLesAuteurs() {
		
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
	public int ajouterAuteur(Auteur auteur) {
		
		 obtenirTousLesAuteurs();
		 getAll();
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			int id = 0;	

			if (auteur.getId() == null) {

				statement = connection.createStatement();
				
				id = lastId + 1;	
				
				Date dateDate = (Date) auteur.getDateDeNaissance().getTime();
				String datestring = dateEnStringSql(dateDate);
				
				String query = " insert into person (id_person, first_name, last_name, date_of_birth)" 
							   + " values ('" + id + "', '" + auteur.getPrenom() + "', '" + auteur.getNom() + "', '" + datestring + "');";
				
					System.out.println("nom et prenom de l'objet person dans methode addperson de dao : " + auteur.getPrenom() + " " + auteur.getNom() + " " + datestring );
				
				statement.executeUpdate(query);
				statement.close();
				
			}else {	
				id = Integer.valueOf(auteur.getId());	
				System.out.println("id personne existante = " + id);
			}
			
			statement2 = connection.createStatement();

			int idAuteur = lastIdAuteur + 1;
			
			String query2 = " insert into author (id_author, author_person_id)" 
						   + " values ('" + idAuteur + "', '" + id + "');";
			
			statement2.executeUpdate(query2);	
			statement2.close();		
			connection.close();

			return idAuteur;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	} 
	public void modifierAuteur(Auteur auteur) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String dateDeNaissanceString = calendarEnStringSql(auteur.getDateDeNaissance());
		
			System.out.println(" date formatee : " + dateDeNaissanceString);
		
			String query = " update person " 
						 + " set first_name = '" + auteur.getPrenom() + "'," 
						 + " last_name = '" + auteur.getNom() + "' ,"
						 + " date_of_birth = '" + dateDeNaissanceString + "'"
						 + " where id_person = '" + auteur.getId() + "' ;";
					
			System.out.println("objet person dans methode modifier auteur de dao : id personne = " + auteur.getId() + ", prenom = " + auteur.getPrenom() + ", nom = " 
																					   + auteur.getNom() + ", date = " + dateDeNaissanceString );	
			statement.executeUpdate(query);
		
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	public Auteur obtenirAuteur(String id) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from author, person "
						 + " where id_author = '" + id + "' "
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
	public Auteur obtenirAuteurParNom(String nomAuteur) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from author, person "
						 + " where last_name = '" + nomAuteur + "' "
						 + " and author_person_id = id_person ;";
		
			result = statement.executeQuery(query);
			result.next();
		
			
			String idPersonne = result.getString("author_person_id"); 	
			String prenom = result.getString("first_name");
			String idAuteur = String.valueOf(result.getInt("id_author"));
			
			Date dateDeNaissance = result.getDate("date_of_birth");
			// appel de la methode pour formater en calendar la date
			Calendar dateFormatee = dateEnCalendar(dateDeNaissance);
			
			// System.out.println(dateFormatee);
			Auteur auteur = new Auteur (idPersonne, prenom, nomAuteur, dateFormatee, idAuteur);			
		
			statement.close();
			connection.close();
			
			return auteur;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	public void effacerAuteur(String idAuteur, String idPersonne){
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " delete from author"
						 + " where id_author = " + idAuteur + ";";
		
			System.out.println("id de l'objet auteur dans methode effacerAuteur de dao : " + idAuteur);
			
			statement.executeUpdate(query);
			
			
			statement2 = connection.createStatement();
			
			String query2 = " delete from person"
						 + " where id_person = '" + idPersonne + "';";
		
			System.out.println("id de l'objet personne dans methode effacerAuteur de dao : " + idPersonne);
			
			statement2.executeUpdate(query2);
			
			statement.close();	
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// abonnes	
	public ArrayList<Abonne> obtenirTousLesAbonnes() {
		
		ArrayList<Abonne> abonnes = null;
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from subscriber, person"
						 + " where id_person = subscriber_person_id;";
		
			result = statement.executeQuery(query);
			
			abonnes = new ArrayList<Abonne>();
			while (result.next()) {
				String id = result.getString("id_person");
				String prenom = result.getString("first_name");
				String nom = result.getString("last_name");
				String idAbonne = result.getString("id_subscriber");
				Date date = result.getDate("date_of_birth");
				Calendar dateFormatee = null;
				if (date != null) {
					dateFormatee = new GregorianCalendar();
					dateFormatee.setTime(date);
				}
				
				
				Abonne abonne = new Abonne (id, prenom, nom, dateFormatee, idAbonne);
				
				abonnes.add(abonne);
				if (Integer.valueOf(abonne.getIdAbonne()) > lastIdAbonne) {
					lastIdAbonne = Integer.valueOf(abonne.getIdAbonne());	
				}
			}
			
			System.out.println("dernier index de la liste abonne dans obtenir tous les abonnes de dao : " + lastIdAbonne);
			
			result.close();
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return abonnes;	
	}
	public void ajouterAbonne(Abonne abonne) {
		
		obtenirTousLesAbonnes();
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			int id = 0;	
			System.out.println("id avant if : " + abonne.getId());
			
			
			if (abonne.getId() == null) {
				
			// requete 1	
				statement = connection.createStatement();
				
				id = lastId + 1;	
				System.out.println("id nouvelle personne = " + id);
				
				Date dateDate = (Date) abonne.getDateDeNaissance().getTime();
				System.out.println("date au format date : " + dateDate);
				String datestring = dateEnStringSql(dateDate);
				
				System.out.println("date au format string : " + datestring);
				
				String query = " insert into person (id_person, first_name, last_name, date_of_birth)" 
							   + " values ('" + id + "', '" + abonne.getPrenom() + "', '" + abonne.getNom() + "', '" + datestring + "');";
				System.out.println("nom et prenom de l'objet person dans methode addperson de dao : " + abonne.getPrenom() + "', '" + abonne.getNom() + "', '" + datestring);
				
				statement.executeUpdate(query);
			
				statement.close();
				
			}else {
				
				id = Integer.valueOf(abonne.getId());	
				System.out.println("id personne existante = " + id);
			}
			
			statement2 = connection.createStatement();

			int idAbonne = lastIdAbonne + 1;
			
			String query2 = " insert into subscriber (id_subscriber, subscriber_person_id)" 
						   + " values ('" + idAbonne + "', '" + id + "');";
			
			statement2.executeUpdate(query2);
		
			statement2.close();		
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void modifierAbonne(Abonne abonne) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String dateDeNaissanceString = calendarEnStringSql(abonne.getDateDeNaissance());
				
			String query = " update person " 
						 + " set first_name = '" + abonne.getPrenom() + "'," 
						 + " last_name = '" + abonne.getNom() + "' ,"
						 + " date_of_birth = '" + dateDeNaissanceString + "'"
						 + " where id_person = '" + abonne.getId() + "' ;";
					
			System.out.println("objet person dans methode modifierAbonne de dao - id : " + abonne.getId() + ", prenom : " + abonne.getPrenom() + ", nom : " 
																					   + abonne.getNom() + ", date : " + dateDeNaissanceString );	
			statement.executeUpdate(query);
		
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Abonne obtenirAbonne(String id) {
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = "select * from subscriber, person "
						 + " where id_subscriber = '" + id + "' "
						 + " and subscriber_person_id = id_person ;";
		
			result = statement.executeQuery(query);
			result.next();
		
			String idPersonne = result.getString("subscriber_person_id"); 	
			String prenom = result.getString("first_name");
			String nom = result.getString("last_name");
			
			Date dateDeNaissance = result.getDate("date_of_birth");
			// appel de la methode pour formater en calendar la date
			Calendar dateFormatee = dateEnCalendar(dateDeNaissance);
			
			// System.out.println(dateFormatee);
			Abonne abonne = new Abonne (idPersonne, prenom, nom, dateFormatee,  id);			
		
			statement.close();
			connection.close();
			
			return abonne;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	public void effacerAbonne(String idAbonne, String idPersonne) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " delete from subscriber"
						 + " where id_subscriber = '" + idAbonne + "';";
		
			System.out.println("id de l'objet abonne dans methode effacerAbonne de dao" + idAbonne);
			
			statement.executeUpdate(query);
			
			statement2 = connection.createStatement();
			
			String query2 = " delete from person"
						 + " where id_person = '" + idPersonne + "';";
		
			System.out.println("id de l'objet personne dans methode effacerAuteur de dao : " + idPersonne);
			
			statement2.executeUpdate(query2);
			
			statement.close();	
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Copie> consulterAbonne(String idAbonne) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
			
			statement = connection.createStatement();
			
			String query = " select *"
						 + " from copy"
						 + " where copy_id_subscriber = " + idAbonne 
						 + " and copy_is_available = 0 "
						 + " and copy_is_on_repairing = 0 ;";
			
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
				
				String isbnOeuvre = result.getString("copy_isbn_book");
			
				statement2 = connection.createStatement();
				
				String query2 = " select last_name, first_name "
								+ " from subscriber, person"
								+ " where subscriber_person_id = id_person"
								+ " and id_subscriber = '" + idAbonne + "';";
				
				result2 = statement2.executeQuery(query2);
				result2.next();
	
				int idAbonneInt = Integer.valueOf(idAbonne);
				String prenomAbonne = result2.getString("last_name");
				String nomAbonne = result2.getString("first_name");
				
				statement2.close();
				
				statement3 = connection.createStatement();
				
				String query3 = " select * from book"
							  + " where isbn_book = '" + isbnOeuvre + "';";
				
				result3 = statement3.executeQuery(query3);
				result3.next();
				
				String titreOeuvre = result3.getString("title_book");
				String soustitreOeuvre = result3.getString("subtitle_book");
				
				statement3.close();
				
				Copie copie = new Copie (idCopie, dateEmpruntFormatee, dateRetourFormatee, copieEstDisponible, copieVientDEtreReparee,
						copieEstEnReparation, isbnOeuvre, titreOeuvre, soustitreOeuvre, idAbonneInt , prenomAbonne, nomAbonne);
				
				listCopies.add(copie);				
			}
			
			statement.close();
			connection.close();
			
			return listCopies;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void retournerCopieAbonne(Copie copie) {
		
		try {
			connection = DriverManager.getConnection(url, login, passwordConnection);
						
			String dateSql = calendarEnStringSql(copie.getDateRetour());
			
			statement = connection.createStatement();
					
			String query = " update copy"
						 + " set date_of_back = '" + dateSql + "',"
						 + " copy_is_available = 1 ,"
						 + " copy_is_on_repairing = 0 ,"
						 + " copy_is_freshly_repeared = 0 "
						 + " where id_copy = " + copie.getIdCopie() + ";";
			
			System.out.println("requete retour copie abonné effectuée");
			
			statement.executeUpdate(query);
	
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}

