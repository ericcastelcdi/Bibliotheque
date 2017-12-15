<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Oeuvres</title>
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
	
		<div class="titreTableau margeHaut">
			<h2>GENRE <c:out value="${nomGenre.toUpperCase()}" /></h2>
		</div>
		
		<div class="table-responsive hauteurTableau backgroundTableau ombre">
			<table class="table table-striped " >
		
				<thead>
					<tr>
						<th>isbn</th>
						<th>titreOeuvre</th>
						<th>sousTitreOeuvre</th>
						<th>dateOeuvre</th>
						<th>auteur</th>
						<th>Copies</th>
						<th>Disponibles</th>
			
					</tr>
				</thead>
			
				<tbody>	
					<c:forEach var="oeuvre" items="${oeuvres}">
						<tr>		
							<td><c:out value="${oeuvre.isbn}" /></td>
							<td><c:out value="${oeuvre.titreOeuvre}" /></td>
							<td><c:out value="${oeuvre.sousTitreOeuvre}" /></td>
							<td><fmt:formatDate value="${oeuvre.dateOeuvre.time}" pattern="dd/MM/yyyy"/></td>
							<td><c:out value="${oeuvre.auteur}" /></td>
							<td><c:out value="${oeuvre.nbreDeCopies}" /></td>
							<td><c:out value="${oeuvre.nbreDeCopiesDisponibles}" /></td>
							<td><a href="<c:url value="/consulterOeuvre?isbn=${oeuvre.isbn}"/> " >Consulter</a></td>
							<td><a href="<c:url value="/modifierOeuvre?isbn=${oeuvre.isbn}"/> " >Modifier</a></td>
							<td><a href="<c:url value="/effacerOeuvre?isbn=${oeuvre.isbn}"/> " >Supprimer</a></td>
						</tr>
					</c:forEach>
				<tbody>	
							
			</table>
		</div>
		
		<br>
		<a href="<c:url value="ajouterOeuvreAuGenre?nomGenre=${nomGenre}"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutGenre" value="AJOUT OEUVRE(S) AU GENRE" /></a>
		<a href="<c:url value="/listeGenres"/>"><input type="button" class="btn btn-primary boutonAGauche margeBas" name="ajoutAbonne" value="RETOUR" /></a>
		
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
 	<script src="bootstrap/js/bootstrap.min.js" ></script>
 	<script src="<c:url value="/JS/background.js"/>"></script>
</body>

</html>


