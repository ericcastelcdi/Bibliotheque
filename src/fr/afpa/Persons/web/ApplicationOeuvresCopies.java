package fr.afpa.Persons.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import fr.afpa.Persons.dao.DaoOeuvre;
import fr.afpa.Persons.dao.DaoPersonnes;
import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Copie;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.service.IServiceOeuvre;
import fr.afpa.Persons.service.IServicePersonnes;
import fr.afpa.Persons.service.ServiceOeuvre;
import fr.afpa.Persons.service.ServicePersonnes;

public class ApplicationOeuvresCopies extends HttpServlet {

	IServiceOeuvre service;
	IServicePersonnes serviceP;

	public void init() throws ServletException {
		DaoOeuvre dao = new DaoOeuvre();
		service = new ServiceOeuvre(dao);
		
		DaoPersonnes daoP = new DaoPersonnes();
		serviceP = new ServicePersonnes(daoP);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// en cas de probleme d'encodage , rajouté cette ligne ci dessous dans chaque
		// servlet
		request.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		// OEUVRES
		if (action.equals("/listeOeuvres")) {
			listeOeuvres(request, response);

		} else if (action.equals("/ajouterOeuvre")) {
			ajouterOeuvre(request, response);

		} else if (action.equals("/validerAjouterOeuvre")) {
			validerAjouterOeuvre(request, response);

		} else if (action.equals("/modifierOeuvre")) {
			modifierOeuvre(request, response);

		} else if (action.equals("/validerModifierOeuvre")) {
			validerModifierOeuvre(request, response);

		} else if (action.equals("/effacerOeuvre")) {
			effacerOeuvre(request, response);

		} else if (action.equals("/consulterOeuvre")) {
			consulterOeuvre(request, response);

		} else if (action.equals("/ajouterCopie")) {
			ajouterCopie(request, response);

		} else if (action.equals("/emprunterCopie")) {
			emprunterCopie(request, response);

		} else if (action.equals("/retournerCopie")) {
			retournerCopie(request, response);

		} else if (action.equals("/supprimerCopie")) {
			supprimerCopie(request, response);

		} else if (action.equals("/reparerCopie")) {
			reparerCopie(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

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

// OEUVRES
	private void listeOeuvres(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		oeuvres = service.obtenirToutesLesOeuvres();

		request.setAttribute("oeuvres", oeuvres);
		
		// On defini l'url courante 
		// cela va servir a detecter si qd on est sur modifierOeuvre, on vient de cette page ou d'une autre
		// pour mettre l'element selectionné de la liste des auteurs à : " ------ selectioner un auteur ------ "
		request.getSession().setAttribute("currentUrl", "listeOeuvres");

		getServletContext().getRequestDispatcher("/WEB-INF/vues/listeOeuvres.jsp").forward(request, response);
	}

	private void effacerOeuvre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");

		service.effacerOeuvre(isbn);

		// reaffichage de la page liste apr�s ajout
		listeOeuvres(request, response);
	}

	public void modifierOeuvre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// On recupere l'url precedente de la session et on la passe en "previousUrl"
		String previousUrl = (String) request.getSession().getAttribute("currentUrl");
		request.getSession().setAttribute("previousUrl", previousUrl);
				
		Oeuvre oeuvre = null;
		
		// On verifi ce que contient la "previousUrl" de la session
		if (previousUrl.equals("listeOeuvres") || previousUrl.equals("listeOeuvresParGenre")) {
			request.getSession().setAttribute("idAuteurSelectionne", null);
			// On recupere l'isbn de l'oeuvre qu'on a passé dans la requete 
			String isbn = request.getParameter("isbn");
			System.out.println("isbn oeuvre dans modifierOeuvre de dao : " + isbn);
			// On va chercher l'oeuvre grace a l'id
			oeuvre = service.obtenirOeuvre(isbn);
		}
			
		// On crée les arraylists des auteurs et des genres
		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
			auteurs = service.obtenirTousLesAuteurs();
		ArrayList<Genre> genres = new ArrayList<Genre>();
			genres = service.obtenirTousLesGenres();
	
		// On passe les listes auteurs et genres en attributs de la session
		request.getSession().setAttribute("auteurs", auteurs);
		request.getSession().setAttribute("genres", genres);
		
		// On passe l'oeuvre dans la session
		if (oeuvre != null) {
			request.getSession().setAttribute("oeuvre", oeuvre);
		}
		
		// On defini l'url courante 
		request.getSession().setAttribute("currentUrl", "modifierOeuvre");
		// On affiche la page modifierOeuvre
		getServletContext().getRequestDispatcher("/WEB-INF/vues/modifierOeuvre.jsp").forward(request, response);

	}

	private void validerModifierOeuvre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On recupere les parametres saisis de la page modifierOeuvre
		String isbn = request.getParameter("isbn");
		String titreOeuvre = request.getParameter("titreOeuvre");
		String sousTitreOeuvre = request.getParameter("sousTitreOeuvre");
		String idAuteur = request.getParameter("idAuteur");		
		String genreActuel = request.getParameter("genre");				
		String dateOeuvreString = request.getParameter("dateOeuvre");
		// On reformate la date
		Calendar dateFormatee = null;
		try {
			dateFormatee = toCalendar(dateOeuvreString, "dd/MM/yyyy");
		} catch (java.text.ParseException e) {
			System.out.println("c'est balox, la requete a planté lors du formatage de la date, dans validerModifierOeuvre de ApplicationOeuvresCopies !");
			e.printStackTrace();
		}
			
		// On recupere la valeur de l'element selectionne de la liste des genres
		String valeurGenreSelectionne = request.getParameter("selectGenre");	
		if (valeurGenreSelectionne.equals("Aucun")) {
			valeurGenreSelectionne = genreActuel;
		}
		
		// On crée l'auteur, selon s' il est changé ou pas
		Auteur auteur = null;
		// On recupere la valeur de l'element selectionne de la liste des auteurs
		String valeurIdAuteurSelectionne = request.getParameter("selectAuteur");
		if (valeurIdAuteurSelectionne.equals("Aucun")) {
			auteur = service.obtenirAuteur(idAuteur);
		} else {
			auteur = service.obtenirAuteur(valeurIdAuteurSelectionne);
		}

		// verification dasn la console
		System.out.println("methode valider modifier oeuvre : ISBN " + isbn + " - " + titreOeuvre + " " + sousTitreOeuvre
						+ " - " + dateOeuvreString + " / genre : " + valeurGenreSelectionne + " - Auteur : " + auteur);

		// On crée l'oeuvre
		Oeuvre oeuvre = new Oeuvre(isbn, titreOeuvre, sousTitreOeuvre, dateFormatee, auteur, valeurGenreSelectionne);
		// on transmet au service
		service.modifierOeuvre(oeuvre);

		// réaffichage de la liste d'oeuvres pour visioner les modifs
		listeOeuvres(request, response);

	}

	private void ajouterOeuvre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();

		auteurs = service.obtenirTousLesAuteurs();

		request.setAttribute("auteurs", auteurs);

		ArrayList<Genre> genres = new ArrayList<Genre>();

		genres = service.obtenirTousLesGenres();

		request.setAttribute("genres", genres);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterOeuvre.jsp").forward(request, response);
	}

	private void validerAjouterOeuvre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("NOUVELLE OEUVRE :");
		String isbn = request.getParameter("isbn");
		String titreOeuvre = request.getParameter("titre");
		String sousTitreOeuvre = request.getParameter("sousTitre");

		String valeurElementSelectioneAuteur = request.getParameter("selectAuteur");
		String idAuteur = null;
		Auteur auteur = null;
		if (!valeurElementSelectioneAuteur.equals("0")) {
			idAuteur = valeurElementSelectioneAuteur;

			auteur = service.obtenirAuteur(idAuteur);
		} else {
			// String idPersonne = request.getParameter("");
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");

			String dateDeNaissanceString = request.getParameter("dateDeNaissance");
			Calendar dateDeNaissance = null;
			try {
				dateDeNaissance = toCalendar(dateDeNaissanceString, "dd/MM/yyyy");
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

			auteur = new Auteur(prenom, nom, dateDeNaissance);
		}

		String dateOeuvreString = request.getParameter("dateOeuvre");
		Calendar dateOeuvre = null;
		try {
			dateOeuvre = toCalendar(dateOeuvreString, "dd/MM/yyyy");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		String valeurElementSelectioneGenre = request.getParameter("selectGenre");
		String nomDuGenre = null;
		if (!valeurElementSelectioneGenre.equals("0")) {
			nomDuGenre = valeurElementSelectioneGenre;
		} else {
			nomDuGenre = request.getParameter("");
		}

		Oeuvre oeuvre = new Oeuvre(isbn, titreOeuvre, sousTitreOeuvre, dateOeuvre, auteur, nomDuGenre);

		System.out.println("methode ajouter oeuvre : " + isbn + " " + titreOeuvre + " " + dateOeuvreString);

		service.creerOeuvre(oeuvre);

		// reaffichage de la page liste apr�s ajout
		listeOeuvres(request, response);
	}

	private void consulterOeuvre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");
			System.out.println("isbn recupérée : " + isbn);
			
		Oeuvre oeuvre = service.obtenirOeuvre(isbn);
			System.out.println("oeuvre de consulterOeuvre : " + oeuvre);
			
		request.setAttribute("oeuvre", oeuvre);
		
		ArrayList<Copie> copies = new ArrayList<Copie>();
		copies = service.obtenirToutesLesCopiesOeuvre(oeuvre);
		
		if (copies != null) {
			request.setAttribute("copies", copies);
		}
		
		ArrayList<Abonne> abonnes = new ArrayList<Abonne>();
		abonnes = serviceP.obtenirTousLesAbonnes();
		
		if (abonnes != null) {
			request.setAttribute("abonnes", abonnes);
		}
		
		String idCopie = request.getParameter("idCopieSelectionnee");
			System.out.println("idCopie : " + idCopie);
		
		if (idCopie != null) {	
			request.setAttribute("idCopieSelectionnee", idCopie);
		}
		
		getServletContext().getRequestDispatcher("/WEB-INF/vues/consulterOeuvre.jsp").forward(request, response);
	}

// AUTEUR
	private void obtenirAuteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

// COPIES
	private void ajouterCopie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
			System.out.println("isbn recupérée : " + isbn);
		
		service.ajouterCopie(isbn);
		
		consulterOeuvre(request, response);
		
	}
	
	private void emprunterCopie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("");
		System.out.println("*** EMPRUNT ***");
					
		// On recupere la valeur de l'input hidden IdCopieEmprunt qui correspond a la copie selectionnée
		String IdCopieSelectionneeString = request.getParameter("IdCopieEmprunt");
		System.out.println("IdCopieEmprunt = " + request.getParameter("IdCopieEmprunt"));
		
		
		// On recupere la valeur de l'input hidden IdAbonneEmprunt qui correspond a la copie selectionnée
		String IdAbonneSelectionneeString = request.getParameter("IdAbonneEmprunt");
		System.out.println("IdAbonneEmprunt = " + request.getParameter("IdAbonneEmprunt"));	
		
		int valeurIdAbonneSelectionnee = 0;
		int valeurIdCopieSelectionnee = 0;
		
		if (!IdAbonneSelectionneeString.equals("selection") & IdAbonneSelectionneeString != null & IdCopieSelectionneeString != null) {
			
			valeurIdAbonneSelectionnee = Integer.valueOf(IdAbonneSelectionneeString);
			
			valeurIdCopieSelectionnee = Integer.valueOf(IdCopieSelectionneeString);
			
			// On crée la date d'emprunt a la date et heure de maintenant
			Calendar dateEmprunt = Calendar.getInstance();
					
			Copie copie = new Copie(valeurIdCopieSelectionnee, dateEmprunt, valeurIdAbonneSelectionnee);
		
			service.emprunterCopie(copie);
		}
		
			String IsbnOeuvreCourante = request.getParameter("IsbnOeuvre");
			
			response.sendRedirect("/Persons/consulterOeuvre?isbn=" + IsbnOeuvreCourante + "&idCopieSelectionnee=" + valeurIdCopieSelectionnee);
	
	}

	private void retournerCopie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("");
		System.out.println("*** RETOUR ***");
		
		System.out.println("IdCopieRetour = " + request.getParameter("IdCopieRetour"));
		// On recupere la valeur de l'input hidden IdCopieRetour qui correspond a la copie selectionnée
		int valeurIdCopieSelectionnee = Integer.valueOf(request.getParameter("IdCopieRetour"));
		
		// On crée la date d'emprunt a la date et heure de maintenant
		Calendar dateRetour = Calendar.getInstance();
				
		Copie copie = new Copie(valeurIdCopieSelectionnee, dateRetour);
	
		service.retournerCopie(copie);
		
		String IsbnOeuvreCourante = request.getParameter("IsbnOeuvre");
		
		response.sendRedirect("/Persons/consulterOeuvre?isbn=" + IsbnOeuvreCourante + "&idCopieSelectionnee=" + valeurIdCopieSelectionnee);
}

	private void supprimerCopie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("");
		System.out.println("*** SUPPRESSION ***");
		
		System.out.println("IdCopieSuppression = " + request.getParameter("IdCopieSuppression"));
		// On recupere la valeur de l'input hidden IdCopieRetour qui correspond a la copie selectionnée
		int valeurIdCopieSelectionnee = Integer.valueOf(request.getParameter("IdCopieSuppression"));
		
		service.supprimerCopie(valeurIdCopieSelectionnee);
		
		String IsbnOeuvreCourante = request.getParameter("IsbnOeuvre");
		
		response.sendRedirect("/Persons/consulterOeuvre?isbn=" + IsbnOeuvreCourante);
	}
	
	private void reparerCopie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("");
		System.out.println("*** REPARATION ***");
		
		System.out.println("IdCopieReparation = " + request.getParameter("IdCopieReparation"));
		// On recupere la valeur de l'input hidden IdCopieRetour qui correspond a la copie selectionnée
		int valeurIdCopieSelectionnee = Integer.valueOf(request.getParameter("IdCopieReparation"));
		
		// On crée la date d'emprunt a la date et heure de maintenant
		Calendar dateEmprunt = Calendar.getInstance();
				
		Copie copie = new Copie(valeurIdCopieSelectionnee, dateEmprunt, true, false);
	
		service.reparerCopie(copie);
		
		String IsbnOeuvreCourante = request.getParameter("IsbnOeuvre");
		
		response.sendRedirect("/Persons/consulterOeuvre?isbn=" + IsbnOeuvreCourante + "&idCopieSelectionnee=" + valeurIdCopieSelectionnee);
	}
	
}
