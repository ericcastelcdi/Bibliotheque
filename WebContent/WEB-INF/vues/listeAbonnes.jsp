<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Abonnes</title>
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
			<h2 class="titreTableau margeHaut">ABONNES</h2>
		</div>

		<br>
		
		<div class="table-responsive hauteurTableau backgroundTableau ombre">
			<table class="table table-striped " >
				
				<thead>
					<tr>
						<th scope="row">Id Abonne</th>
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
					<c:forEach var="abonne" items="${abonnes}">
						<tr>	
							<td><c:out value="${abonne.idAbonne}" /></td>	
							<td><c:out value="${abonne.prenom}" /></td>
							<td><c:out value="${abonne.nom}" /></td>		
							<td><fmt:formatDate value="${abonne.dateDeNaissance.time}" pattern="dd/MM/yyyy"/></td>
							<td><c:out value="${abonne.id}" /></td>
							<td><a href="<c:url value="consulterAbonne?idAbonne=${abonne.idAbonne}"/> " >Consulter</a></td>
							<td><a href="<c:url value="/modifierAbonne?id=${abonne.idAbonne}"/> " >Modifier</a></td>
							<td><a href="<c:url value="/effacerAbonne?idAbonne=${abonne.idAbonne}&idPersonne=${abonne.id}"/> " >Supprimer</a></td>
						</tr>
					</c:forEach>
				</tbody>	
			
			</table>
		</div>
		
		<br>
		<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-primary boutonAGauche margeBas" name="ajoutAbonne" value="RETOUR" /></a>
		<a href="<c:url value="/ajouterAbonne"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="AJOUTER ABONNE" /></a>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
 	<script src="bootstrap/js/bootstrap.min.js" ></script>
 	<script src="<c:url value="/JS/background.js"/>"></script>
</body>

</html>


