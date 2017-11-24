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

import fr.afpa.Persons.dao.DaoGenre;
import fr.afpa.Persons.dao.DaoPersonnes;
import fr.afpa.Persons.model.Abonne;
import fr.afpa.Persons.model.Auteur;
import fr.afpa.Persons.model.Genre;
import fr.afpa.Persons.model.Oeuvre;
import fr.afpa.Persons.model.Personne;
import fr.afpa.Persons.service.IServiceGenre;
import fr.afpa.Persons.service.IServicePersonnes;
import fr.afpa.Persons.service.ServiceGenre;
import fr.afpa.Persons.service.ServicePersonnes;

public class ApplicationGenres extends HttpServlet {

	IServiceGenre service;

	public void init() throws ServletException {
		DaoGenre dao = new DaoGenre();
		service = new ServiceGenre(dao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
	

		String action = request.getServletPath();
		
	// ACCUEIL
		if (action.equals("/accueil")) {
			accueilBibliotheque(request, response);
	// GENRES
		}else if (action.equals("/listeGenres")) { 
			listeGenres(request, response);
		
		}else if (action.equals("/ajouterGenre")) { 
			ajouterGenre(request, response);
		  
		}else if (action.equals("/validerAjouterGenre")) {
			validerAjouterGenre(request, response);
		  
		}else if (action.equals("/modifierGenre")) { 
			modifierGenre(request, response);
		
		}else if (action.equals("/validerModifierGenre")) {
			validerModifierGenre(request, response);
		  
		}else if (action.equals("/effacerGenre")) { 
			effacerGenre(request,response);
		 
		}else if (action.equals("/listeOeuvresParGenre")) { 
			listeOeuvresParGenre(request,response);
		 
		}else if (action.equals("/ajouterOeuvreAuGenre")) { 
			ajouterOeuvreAuGenre(request,response);
		 
		}else if (action.equals("/validerAjouterOeuvreAuGenre")) { 
			validerAjouterOeuvreAuGenre(request,response);
		 
		}else if (action.equals("/nbreOeuvresParGenre")) { 
			nbreOeuvresParGenre(request,response);
		 
		}
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		doGet(request, response);
	}

	// ACCUEIL
	private void accueilBibliotheque(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/vues/accueil.jsp").forward(request, response);
	}

	// GENRES
	private void listeGenres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Genre> genres = new ArrayList<Genre>();

		genres = service.obtenirTousLesGenres();

		request.setAttribute("genres", genres);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/listeGenres.jsp").forward(request, response);
	}

	private void ajouterGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Genre> genres = new ArrayList<Genre>();

		genres = service.obtenirTousLesGenres();

		request.setAttribute("genres", genres);

		String previousPage = (String) request.getSession().getAttribute("currentPage");
		request.getSession().setAttribute("previousPage", previousPage);

		request.getSession().setAttribute("currentPage", "/WEB-INF/vues/ajouterGenre.jsp");

		getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterGenre.jsp").forward(request, response);
	}

	private void validerAjouterGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomGenre = request.getParameter("nomGenre");

		Genre genre = new Genre(nomGenre);
		service.ajouterGenre(genre);

		listeGenres(request, response);
		
	}

	private void modifierGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomGenre = request.getParameter("nomGenre");
		
		Genre genre = service.obtenirGenre(nomGenre);
		
		request.setAttribute("genre", genre);

		getServletContext().getRequestDispatcher("/WEB-INF/vues/modifierGenre.jsp").forward(request, response);
	}

	private void validerModifierGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomGenre = request.getParameter("nomGenreAncien");
		
		String nomGenreNouveau = request.getParameter("nomGenreNouveau");
		Genre genre = new Genre(nomGenreNouveau);

		service.modifierGenre(genre, nomGenre);
		
		System.out.println("nom du genre apres appui sur valider modification genre de la servlet applicationGenres : " + nomGenre );
		System.out.println("nom du genre saisi pour modifier : " + genre.getNomGenre() );

		// reaffichage de la page liste apr�s ajout
		listeGenres(request, response);

	}

	private void effacerGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomGenre = request.getParameter("nomGenre");

		service.effacerGenre(nomGenre);

		listeGenres(request, response);
	}

	private void listeOeuvresParGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomGenre = request.getParameter("nomGenre");
		
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
		
		oeuvres = service.obtenirToutesLesOeuvresParGenre(nomGenre);
	
		request.setAttribute("oeuvres", oeuvres);
		request.setAttribute("nomGenre", nomGenre);
		
		getServletContext().getRequestDispatcher("/WEB-INF/vues/listeOeuvresParGenre.jsp").forward(request, response);
	}

	private void nbreOeuvresParGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomGenre = request.getParameter("nomGenre");
		
		int nbreOeuvresParGenre = service.obtenirNbreOeuvresParGenre(nomGenre);
		
		request.setAttribute("nbreOeuvres", nbreOeuvresParGenre);
		
		getServletContext().getRequestDispatcher("/WEB-INF/vues/listeGenres.jsp").forward(request, response);
		
	}

	private void ajouterOeuvreAuGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

			oeuvres = service.obtenirToutesLesOeuvres();
	
			request.setAttribute("oeuvres", oeuvres);  
		
		String nomGenre = request.getParameter("nomGenre");
		
			request.setAttribute("nomGenre", nomGenre);
		
		request.getSession().setAttribute("currentUrl", "ajouterOeuvreAuGenre");

		getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterOeuvreAuGenre.jsp").forward(request, response);
	}
	
	private void validerAjouterOeuvreAuGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// on recupere la concatenation des isbns des oeuvres cochés envoyées par le formulaire de la page "ajout oeuvre au genre"
		String concatenationisbn = request.getParameter("concatenationisbn");
			System.out.println(concatenationisbn);
		// on enleve le premier slash de la string et on la met dans une autre string
		String concatNetoyee = concatenationisbn.substring(1,concatenationisbn.length());
			System.out.println(concatNetoyee);
		// on splite la nouvelle string , en enlevant les ";" pour avoir un tableau d'isbn 
		String[] TabIsbn = concatNetoyee.split(";");
	
		// On recupere le nom du genre
		String nomGenre = request.getParameter("nomGenre");
		
		// on envoi au service
		service.ajouterOeuvreAuGenre(TabIsbn, nomGenre);
		
		// on revient ensuite a la page qui etait avant le clic sur "ajout oeuvres au genre" en redirigeant a la bonne url
		response.sendRedirect("/Persons/listeOeuvresParGenre?nomGenre=" + nomGenre);

	}
	
	
}
