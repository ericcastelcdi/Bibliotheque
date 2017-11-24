<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Genre</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<!-- on ajoute le menu du site -->
	<%@include file="menu.jsp"%>

	<h2>Ajout oeuvre(s) au genre</h2>
	
	<table border="1">
	
		<tr>
			<th></th>
			<th>isbn</th>
			<th>titreOeuvre</th>
			<th>sousTitreOeuvre</th>
			<th>dateOeuvre</th>
			<th>auteur</th>
			<th>genre</th>
			<th>Copies disponibles</th>

		</tr>
		
		<c:forEach var="oeuvre" items="${oeuvres}">
			<tr>	
				<td><INPUT type="checkbox" name="selectionner" value="selection" ></td>	
				<td><c:out value="${oeuvre.isbn}" /></td>
				<td><c:out value="${oeuvre.titreOeuvre}" /></td>
				<td><c:out value="${oeuvre.sousTitreOeuvre}" /></td>
				<td><fmt:formatDate value="${oeuvre.dateOeuvre.time}" pattern="dd/MM/yyyy"/></td>
				<td><c:out value="${oeuvre.auteur}" /></td>
				<td><c:out value="${oeuvre.nomDuGenre}" /></td>
				<td><c:out value="${String.valueOf(oeuvre.nbreDeCopiesDisponibles)}" /></td>
				<td><a href="<c:url value="/consulterOeuvre?isbn=${oeuvre.isbn}"/> " >Consulter</a></td>
				<td><a href="<c:url value="/modifierOeuvre?isbn=${oeuvre.isbn}"/> " >Modifier</a></td>
				<td><a href="<c:url value="/effacerOeuvre?isbn=${oeuvre.isbn}"/> " >Supprimer</a></td>
			</tr>
		</c:forEach>
					
	</table>
	<br>
	
	<!-- ci dessous , le bouton valider execute la fonction javascript "check();" du javascript donc le lien est en bas de la page  -->
	<input id = "boutonValider" type="button" value ="Valider ajout oeuvre(s) selectionnée(s)" onclick="check();"><br>
		
	<!-- ci dessous, le formulaire qui va servir a recuperer les isbn cochées ,
	elles seront stockées concaténées dans l'input "hidden" sous l id "concatenationisbn",
	et envoyer à l'adresse : "/validerAjouterOeuvreAuGenre" sous l'id "concat" -->
	<form id="concat" action="<c:url value="/validerAjouterOeuvreAuGenre"/>" method="GET">
		<input id="concatenationisbn" name="concatenationisbn" type="hidden" value="" >
		<input name="nomGenre" type="hidden" value="${nomGenre}" >
		
	</form>
	<br>
	
	<a href="<c:url value="/ajouterOeuvre"/>">Creer nouvelle oeuvre</a><br>
	<a href="<c:url value="/accueil"/>">Retour</a>

	<!--lien vers le language jquery et le fichier javascript du bouton valider-->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="<c:url value="/JS/ajouterOeuvreAuGenre.js"/>"></script>		
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	<script src="bootstrap/js/bootstrap.min.js" ></script>
 	
</body>

</html>


