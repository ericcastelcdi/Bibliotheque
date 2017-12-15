package fr.afpa.Persons.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import fr.afpa.Persons.dao.DaoPersonnes;
import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;
import fr.afpa.Persons.service.IServiceOeuvre;
import fr.afpa.Persons.service.IServicePersonnes;
import fr.afpa.Persons.service.ServicePersonnes;

public class Application extends HttpServlet {

	IServicePersonnes service;
	IServiceOeuvre serviceO;

	public void init() throws ServletException {
		DaoPersonnes dao = new DaoPersonnes();
		service = new ServicePersonnes(dao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		
		String action = request.getServletPath();
// ACCUEIL
		if (action.equals("/accueil")) {
			accueilBibliotheque(request, response);
// PERSONNES
		} else if (action.equals("/listePersonnes")) {
			listPersons(request, response);

		} else if (action.equals("/add")) {
			doAdd(request, response);

		} else if (action.equals("/validate")) {
			addPerson(request, response);

		} else if (action.equals("/delete")) {
			deletePerson(request, response);

		} else if (action.equals("/modify")) {
			modifyPerson(request, response);

		} else if (action.equals("/validateModify")) {
			validateModifyPerson(request, response);
// ABONNES
		} else if (action.equals("/listeAbonnes")) {
			listeAbonnes(request, response);

		} else if (action.equals("/ajouterAbonne")) {
			ajouterAbonne(request, response);

		} else if (action.equals("/validerAjouterAbonne")) {
			validerAjouterAbonne(request, response);

		} else if (action.equals("/modifierAbonne")) {
			modifierAbonne(request, response);

		} else if (action.equals("/validerModifierAbonne")) {
			validerModifierAbonne(request, response);

		} else if (action.equals("/consulterAbonne")) {
			consulterAbonne(request, response);

		} else if (action.equals("/retournerCopieAbonne")) {
			retournerCopieAbonne(request, response);
// AUTEURS
		}else if (action.equals("/effacerAbonne")) {
			effacerAbonne(request, response);
			
		} else if (action.equals("/listeAuteurs")) {
			listeAuteurs(request, response);

		} else if (action.equals("/ajouterAuteur")) {
			ajouterAuteur(request, response);

		} else if (action.equals("/validerAjouterAuteur")) {
			validerAjouterAuteur(request, response);

		} else if (action.equals("/modifierAuteur")) {
			modifierAuteur(request, response);

		} else if (action.equals("/validerModifierAuteur")) {
			validerModifierAuteur(request, response);

		} else if (action.equals("/effacerAuteur")) {
			effacerAuteur(request, response);

		} else if (action.equals("/consulterAuteur")) {
			consulterAuteur(request, response);

		} 

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		doGet(request, response);
	}

// Methodes anexes
	private Calendar toCalendar(String dateString, String pattern) throws java.text.ParseException {
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

// ACCUEIL
	private void accueilBibliotheque(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/vues/accueil.jsp").forward(request, response);
	}

// AUTEURS
	private void listeAuteurs(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();

		auteurs = service.obtenirTousLesAuteurs();

		request.setAttribute("auteurs", auteurs);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/listeAuteurs.jsp").forward(request, response);
	}
	private void ajouterAuteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ArrayList<Personne> personnes = new ArrayList<Personne>();
	
		//personnes = service.getAll();

		//request.setAttribute("persons", personnes);

		// On passe l'url de la page precedente en "previousUrl"
		String previousUrl = (String) request.getSession().getAttribute("currentUrl");
		request.getSession().setAttribute("previousUrl", previousUrl);
		// et la currentUrl devient cette page
		request.getSession().setAttribute("currentUrl", "ajouterAuteur");

		getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterAuteur.jsp").forward(request, response);
	}
	private void validerAjouterAuteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// On recupere les parametres saisis de la page
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");	
		String page = request.getParameter("page");
		String dateSaisie = request.getParameter("dateDeNaissance");
		// On reformate la date en calendar
			Calendar dateFormatee = null;
			try {
				dateFormatee = toCalendar(dateSaisie, "dd/MM/yyyy");
			} catch (java.text.ParseException e1) {
				e1.printStackTrace();
			}
		
		// On crée l'auteur avec une id à nul pour le moment 
		// (elle va etre determinée plus tard selon l'id du dernier auteur de la base sql)
		try {	
			Auteur auteur = new Auteur(null, prenom, nom, dateFormatee);
			// une fois l'auteur crée dans la base , on recupere l'id qui y a été associée 
			int idAuteur = service.ajouterAuteur(auteur);

			// On verifi ce que contient la "previousUrl" de la session
			String previousUrl = (String) request.getSession().getAttribute("previousUrl");
			if (previousUrl == "modifierOeuvre") {
				// si on vient de la page "modifierOeuvre", on passe l'id de l'auteur en parametre de la session
				request.getSession().setAttribute("idAuteurSelectionne", idAuteur);	
				// On redirige ensuite a cette precedente page "modifierOeuvre"
				response.sendRedirect(previousUrl);		
			} else {
				// sinon, on reaffiche la page liste aprés ajout
				listeAuteurs(request, response);
			}
			
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	private void modifierAuteur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// R�cup�ration de l'id de la personne correspondant � l'auteur
		String idAuteur = request.getParameter("id");
		// Appel du service pour r�cup�rer notre objet Person du mod�le
		Auteur auteur = service.obtenirAuteur(idAuteur);

		request.setAttribute("auteur", auteur);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/modifierAuteur.jsp").forward(request, response);
	}
	private void validerModifierAuteur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAuteur = request.getParameter("idAuteur");
		String id = request.getParameter("id");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String dateSaisie = request.getParameter("dateDeNaissance");

		Calendar dateFormatee = Calendar.getInstance();
		try {
			dateFormatee = toCalendar(dateSaisie, "dd/MM/yyyy");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		System.out.println("methode modifier auteur >> idAuteur : " + idAuteur + " - id : " + id + " - " + prenom + " "
				+ nom + " - date de naissance : " + dateSaisie);

		Auteur auteur = new Auteur(id, prenom, nom, dateFormatee);
		System.out.println("date formatee en calendar : " + dateFormatee);

		service.modifierAuteur(auteur);

		// reaffichage de la page liste apr�s ajout
		listeAuteurs(request, response);

	}
	private void effacerAuteur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAuteur = request.getParameter("idAuteur");
		String idPersonne = request.getParameter("idPersonne");

		service.effacerAuteur(idAuteur, idPersonne);

		// reaffichage de la page liste apr�s ajout
		listeAuteurs(request, response);
	}
	private void consulterAuteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAuteur = request.getParameter("idAuteur");

		//ArrayList<Oeuvre> oeuvres = serviceO.obtenirToutesLesOeuvres();
	
		//if (oeuvres != null) {
			//request.setAttribute("oeuvres", oeuvres);
		//}
		
		Auteur auteur = service.obtenirAuteur(idAuteur);
		
		request.setAttribute("auteur", auteur);
		
		getServletContext().getRequestDispatcher("/WEB-INF/vues/consulterAuteur.jsp").forward(request, response);
	}	
	
// ABONNES
	private void listeAbonnes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Abonne> abonnes = new ArrayList<Abonne>();

		abonnes = service.obtenirTousLesAbonnes();

		request.setAttribute("abonnes", abonnes);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/listeAbonnes.jsp").forward(request, response);
	}
	private void ajouterAbonne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Personne> personnes = new ArrayList<Personne>();

		personnes = service.getAll();

		request.setAttribute("persons", personnes);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterAbonne.jsp").forward(request, response);
	}
	private void validerAjouterAbonne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("NOUVEAU");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");

		String dateSaisie = request.getParameter("dateDeNaissance");

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDate = sdf.parse(dateSaisie);
			Calendar dateFormatee = Calendar.getInstance();
			dateFormatee.setTime(dateDate);

			System.out.println("methode ajouter abonne : " + prenom + " " + nom + " " + dateDate);
			System.out.println("date au format calendar avant creation objet abonn� : " + dateFormatee);

			Abonne abonne = new Abonne(null, prenom, nom, dateFormatee);
			service.ajouterAbonne(abonne);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		// reaffichage de la page liste apr�s ajout
		listeAbonnes(request, response);
	}
	private void modifierAbonne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// R�cup�ration de l'id de la personne correspondant � l'abonne
		String idAbonne = request.getParameter("id");
		System.out.println("id abonne r�cup�r�e dans modifierAbonne de Application : " + idAbonne);
		// Appel du service pour r�cup�rer notre objet Person du mod�le
		Abonne abonne = service.obtenirAbonne(idAbonne);

		request.setAttribute("abonne", abonne);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/modifierAbonne.jsp").forward(request, response);
	}
	private void validerModifierAbonne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAbonne = request.getParameter("idAbonne");
		String id = request.getParameter("id");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");

		String dateSaisie = request.getParameter("dateDeNaissance");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar dateFormatee = Calendar.getInstance();
		try {
			dateFormatee = toCalendar(dateSaisie, "dd/MM/yyyy");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		System.out.println("methode ajouterAbonne dans validerModifierAbonne de Application : " + idAbonne + " " + id
				+ " " + prenom + " " + nom + " " + dateSaisie);

		Abonne abonne = new Abonne(id, prenom, nom, dateFormatee, idAbonne);

		service.modifierAbonne(abonne);

		// reaffichage de la page liste apr�s ajout
		listeAbonnes(request, response);

	}
	private void effacerAbonne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAbonne = request.getParameter("idAbonne");
		String idPersonne = request.getParameter("idPersonne");

		service.effacerAbonne(idAbonne, idPersonne);

		// reaffichage de la page liste apr�s ajout
		listeAbonnes(request, response);
	}
	private void consulterAbonne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAbonne = request.getParameter("idAbonne");
		
		System.out.println("idAbonne dans consulterAbonne de servlet Abonnes/auteurs/personnes: " + idAbonne);

		ArrayList<Copie> copies = service.consulterAbonne(idAbonne);
	
		if (copies != null) {
			request.setAttribute("copies", copies);
		}
		
		Abonne abonne = service.obtenirAbonne(idAbonne);
		
		request.setAttribute("abonne", abonne);
		
		getServletContext().getRequestDispatcher("/WEB-INF/vues/consulterAbonne.jsp").forward(request, response);
	}	
	private void retournerCopieAbonne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("");
		System.out.println("*** RETOUR ***");
		
		System.out.println("IdCopieRetour = " + request.getParameter("IdCopieRetour"));
		// On recupere la valeur de l'input hidden IdCopieRetour qui correspond a la copie selectionnée
		int valeurIdCopieSelectionnee = Integer.valueOf(request.getParameter("IdCopieRetour"));
		
		// On crée la date d'emprunt a la date et heure de maintenant
		Calendar dateRetour = Calendar.getInstance();
				
		Copie copie = new Copie(valeurIdCopieSelectionnee, dateRetour);
	
		service.retournerCopieAbonne(copie);
		
		String IdAbonne = request.getParameter("IdAbonne");
		System.out.println("IdAbonne : " + IdAbonne);
			
		response.sendRedirect("/Persons/consulterAbonne?idAbonne=" + IdAbonne);
	}
	
// PERSONNES
	private void listPersons(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Personne> personnes = new ArrayList<Personne>();

		personnes = service.getAll();

		request.setAttribute("persons", personnes);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/listePersonnes.jsp").forward(request, response);

	}
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/vues/add.jsp").forward(request, response);
	}
	private void addPerson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");

		String dateSaisie = request.getParameter("dateDeNaissance");
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDate = sdf.parse(dateSaisie);
			Calendar dateFormatee = Calendar.getInstance();
			dateFormatee.setTime(dateDate);

			System.out.println("methode addPerson : " + prenom + " " + nom + " " + dateDate);

			Personne personne = new Personne(prenom, nom, dateFormatee);

			service.addPerson(personne);

			// reaffichage de la page liste apr�s ajout
			listPersons(request, response);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}
	private void deletePerson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		service.deletePerson(id);

		// reaffichage de la page liste apr�s ajout
		listPersons(request, response);
	}
	private void modifyPerson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// R�cup�ration de l'id de la personne
		String id = request.getParameter("id");
		System.out.println("id r�cup�r�e : " + id);
		// Appel du service pour r�cup�rer notre objet Person du mod�le
		Personne personne = service.getPerson(id);

		request.setAttribute("personne", personne);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/modify.jsp").forward(request, response);
	}
	private void validateModifyPerson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String dateSaisie = request.getParameter("dateDeNaissance");

		Calendar dateFormatee = Calendar.getInstance();
		try {
			dateFormatee = toCalendar(dateSaisie, "dd/MM/yyyy");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		System.out.println("methode addPerson : " + id + " " + prenom + " " + nom + " " + dateSaisie);

		Personne personne = new Personne(id, prenom, nom, dateFormatee);

		service.modifyPerson(personne);

		// reaffichage de la page liste apr�s ajout
		listPersons(request, response);

	}

}
