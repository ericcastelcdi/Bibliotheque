<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
	<title>BIBLIOTHEQUE - Genres</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<!-- on ajoute le menu du site -->
	<%@include file="menu.jsp"%>

	<h2>Modification Genre</h2>

	<form method="post" action="<c:url value="/validerModifierGenre"/>">

		<font size=4>Libellé du genre :</font> 
		<input name="nomGenreAncien" value="${genre.nomGenre}" readonly="readonly"></INPUT> 
		<font size=4>Changer :</font> 
		<input name="nomGenreNouveau"></input> <br> <br> 
		
		<input type="submit" value="Enregistrer modification genre" />

	</form>

	<br />
	<a href="<c:url value="/listeGenres"/>">Retour</a>
	<br />
	<a href="<c:url value="/accueil"/>">Accueil</a>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
 	<script src="bootstrap/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</body>

</html>

