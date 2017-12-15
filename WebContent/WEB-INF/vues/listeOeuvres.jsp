<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Oeuvres</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link type="text/css" href="css/tableaux.css" rel="stylesheet">
	<link type="text/css" href="css/formatPage.css" rel="stylesheet">
</head>

<body>
<!-- on ajoute le menu du site -->
	<%@include file="menu.jsp"%>

	<div>
		<img src="images/BIBLIOTHEQUE 2.4.jpg" class="superbg" >
	</div>
	
	<div class="container-fluid PageParDefaut"><br>
		<div>
			<h2 class="titreTableau margeHaut">OEUVRES</h2>
		</div><br>
		
		<div class="table-responsive hauteurTableau backgroundTableau ombre">
			<table class="table table-striped " >
				
				<thead>
					<tr>
						<th scope="row">isbn</th>
						<th scope="row">titreOeuvre</th>
						<th scope="row">sousTitreOeuvre</th>
						<th scope="row">dateOeuvre</th>
						<th scope="row">auteur</th>
						<th scope="row">genre</th>
						<th scope="row">Nbre copies </th>
						<th scope="row">Disponibles</th>
			
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
							<td><c:out value="${oeuvre.nomDuGenre}" /></td>
							<td><c:out value="${oeuvre.nbreDeCopies}" /></td>
							<td><c:out value="${oeuvre.nbreDeCopiesDisponibles}" /></td>
							<td><a href="<c:url value="/consulterOeuvre?isbn=${oeuvre.isbn}"/> " >Consulter</a></td>
							<td><a href="<c:url value="/modifierOeuvre?isbn=${oeuvre.isbn}"/> " >Modifier</a></td>
							<td><a href="<c:url value="/effacerOeuvre?isbn=${oeuvre.isbn}"/> " >Supprimer</a></td>
						</tr>
					</c:forEach>
				</tbody>	
				
			</table>
		</div>
		<br>
		<a href="<c:url value="/ajouterOeuvre"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutOeuvre" value="AJOUT OEUVRE" /></a>
		<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-primary boutonAGauche margeBas" name="accueil" value="ACCUEIL" /></a>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
 	<script src="bootstrap/js/bootstrap.min.js" ></script>
 	<script src="<c:url value="/JS/background.js"/>"></script>
</body>

</html>


