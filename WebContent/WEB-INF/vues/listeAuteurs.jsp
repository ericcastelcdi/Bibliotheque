<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Auteurs</title>
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
	
	<div class="container-fluid PageParDefaut">
	<br>
		<div>
			<h2 class="titreTableau margeHaut">AUTEURS</h2>
		</div>

		<br>
		
	<div class="table-responsive hauteurTableau backgroundTableau ombre">
		<table class="table table-striped " >
					
			<thead>
				<tr>
					<th scope="row">Id Auteur</th>
					<th scope="row">Prenom</th>
					<th scope="row">Nom</th>
					<th scope="row">Date de naissance</th>
					<th scope="row">Id</th>
					<th scope="row"></th>
					<th scope="row"></th>
					<th scope="row"></th>
		
				</tr>
			</thead>
			
			<tbody>	
				<c:forEach var="auteur" items="${auteurs}">
					<tr>	
						<td><c:out value="${auteur.idAuteur}" /></td>	
						<td><c:out value="${auteur.prenom}" /></td>
						<td><c:out value="${auteur.nom}" /></td>
						<td><fmt:formatDate value="${auteur.dateDeNaissance.time}" pattern="dd/MM/yyyy"/></td>
						<td><c:out value="${auteur.id}" /></td>
						<td><a href="<c:url value="consulterAuteur?idAuteur=${auteur.idAuteur}"/> " >Consulter</a></td>
						<td><a href="<c:url value="/modifierAuteur?id=${auteur.idAuteur}"/> " >Modifier</a></td>
						<td><a href="<c:url value="/effacerAuteur?idAuteur=${auteur.idAuteur}&idPersonne=${auteur.id}"/> " >Supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<br>
	<a href="<c:url value="/ajouterAuteur"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAuteur" value="AJOUTER AUTEUR" /></a>
	<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-primary boutonAGauche margeBas" name="ajoutAbonne" value="ACCUEIL" /></a>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
 	<script src="bootstrap/js/bootstrap.min.js" ></script>
 	<script src="<c:url value="/JS/background.js"/>"></script>
</body>

</html>


