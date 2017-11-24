<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Oeuvres</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<!-- on ajoute le menu du site -->
	<%@include file="menu.jsp"%>

	<h2>Genre <c:out value="${nomGenre}" /></h2>
	
	<table border="1">
	
		<tr>
			<th>isbn</th>
			<th>titreOeuvre</th>
			<th>sousTitreOeuvre</th>
			<th>dateOeuvre</th>
			<th>auteur</th>
			<th>Copies</th>
			<th>Disponibles</th>

		</tr>
		
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
					
	</table>
	
	<br>
	<a href="<c:url value="/ajouterOeuvreAuGenre?nomGenre=${nomGenre}"/>">Ajout oeuvre(s) au genre</a>
	<br>
	<a href="<c:url value="/accueil"/>">Retour</a>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
 	<script src="bootstrap/js/bootstrap.min.js" ></script>
</body>

</html>


