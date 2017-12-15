<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

	<head>
		<title>BIBLIOTHEQUE - Genres</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
		<link type="text/css" href="css/formatPage.css" rel="stylesheet" media="screen"/>
		<link type="text/css" href="css/tableaux.css" rel="stylesheet">
	</head>
	
	<body>
	<!-- on ajoute le menu du site -->
		<%@include file="menu.jsp"%>
	
		<div>
			<img src="images/BIBLIOTHEQUE 2.4.jpg" class="superbg" >
		</div>
	
		<div class="container-fluid PageParDefaut">
			<br>
			<div class="margeHaut">
				<a href="<c:url value="/listeGenres"/>"><input type="button" class="btn btn-primary boutonAGauche" name="ajoutAbonne" value="RETOUR" /></a>
				<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
			</div>
			
			<div>
				<h2 class="titreTableau margeHaut">MODIFICATION GENRE</h2>
			</div>

	
		<form method="post" action="<c:url value="/validerModifierGenre"/>">
			<br><br>
			<div class="titreTableau libelle">
				<font size=5 COLOR="white">Libellé du genre</font>
			</div>
			
			<div class="centrer-div">
				<INPUT class="input centrageInput ombre"  name="nomGenreAncien" value="${genre.nomGenre}" readonly="readonly"></INPUT> <br>
			</div><br>
			
			<div class="titreTableau libelle">
				<font size=5 COLOR="white">CHANGER GENRE</font>
			</div>
			
			<div class="centrer-div">
				<INPUT class="input centrageInput ombre"  name="nomGenreNouveau"></INPUT> <br>
			</div>
			
			<div class="centrer-div titreTableau"><br/>
				<input type="submit" class="btn btn-primary margeBas" value="ENREGISTRER MODIFICATIONS" />
			</div>
	
		</form>
	
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	 	<script src="bootstrap/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>

