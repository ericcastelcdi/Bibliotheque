<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Genre</title>
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
	
	<div class="container-fluid PageParDefaut margeHaut">
	<br>

	<div class="titreTableau">
		<h2>OEUVRES A AJOUTER AU GENRE <c:out value="${nomGenre.toUpperCase()}" /></h2>
	</div>
	
	<div class="table-responsive hauteurTableau backgroundTableau ombre">
		<table class="table table-striped " >
	
			<thead>
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
			</thead>
			
			<tbody>		
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
			<tbody>				
		</table>
	</div>			
	<br>
			
		<!-- ci dessous, le formulaire qui va servir a recuperer les isbn cochées ,
		elles seront stockées concaténées dans l'input "hidden" sous l id "concatenationisbn",
		et envoyer à l'adresse : "/validerAjouterOeuvreAuGenre" sous l'id "concat" -->
		<form id="concat" action="<c:url value="/validerAjouterOeuvreAuGenre"/>" method="GET">
			<input id="concatenationisbn" name="concatenationisbn" type="hidden" value="" >
			<input name="nomGenre" type="hidden" value="${nomGenre}" >
			
		</form>
		
	<div class="row margeBas">	
	
		<div class="col-lg-4">
			<!-- ci dessous , le bouton valider execute la fonction javascript "check();" du javascript donc le lien est en bas de la page  -->
			<input id = "boutonValider" class="btn btn-success boutonAGauche " type="button" value ="VALIDER AJOUT SELECTION" onclick="check();">
		</div>
		
		<div class="col-lg-4 text-center">
			<a href="<c:url value="ajouterOeuvre"/>"><input type="button" class="btn btn-success " name="ajoutGenre" value="CREER NOUVELLE OEUVRE" /></a>
		</div>
		
		<div class="col-lg-4">
			<a href="<c:url value="/listeOeuvresParGenre?nomGenre=${nomGenre}"/>"><input type="button" class="btn btn-primary boutonADroite" name="ajoutAbonne" value="RETOUR" /></a>
		</div>
		
	</div>
</div>
	<!--lien vers le language jquery et le fichier javascript du bouton valider-->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="<c:url value="/JS/ajouterOeuvreAuGenre.js"/>"></script>		
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	<script src="bootstrap/js/bootstrap.min.js" ></script>
	<script src="<c:url value="/JS/background.js"/>"></script>
 	
</body>

</html>


