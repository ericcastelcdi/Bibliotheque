package fr.afpa.Persons.model;

public class Genre {

// Attributs
	private String nomGenre;
	private int indexGenre;
	private int nbreOeuvres;
	
// Conctructeur 1 
	public Genre(String nomGenre){
		this.nomGenre = nomGenre;
	}	
	
// Conctructeur 2
	public Genre(String nomGenre, int indexGenre){
		this.nomGenre = nomGenre;
		this.indexGenre = indexGenre;
	}

// Conctructeur 3
	public Genre(String nomGenre, int indexGenre, int nbreOeuvres){
		this.nomGenre = nomGenre;
		this.indexGenre = indexGenre;
		this.nbreOeuvres = nbreOeuvres;
	}	
	
	// Methode to String
	
	// getters et setters

	public String getNomGenre() {
		return nomGenre;
	}
	public void nouveauGenre(String nomGenre) {
		this.nomGenre = nomGenre;
	}

	public int getIndexGenre() {
		return indexGenre;
	}
	public void setIndexGenre(int indexGenre) {
		this.indexGenre = indexGenre;
	}

	
	public int getNbreOeuvres() {
		return nbreOeuvres;
	}

	public void setNbreOeuvres(int nbreOeuvres) {
		this.nbreOeuvres = nbreOeuvres;
	}
	
	
}