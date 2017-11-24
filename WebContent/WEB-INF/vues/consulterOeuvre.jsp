<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Oeuvre</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<!-- on ajoute le menu du site -->
	<%@include file="menu.jsp"%>

	<h2>Consultation Oeuvre</h2>
	
	<font size=4><c:out value="${oeuvre}"/></font><br>
	
	<h3>Copies : </h3>
	
		<select size = 10 name="selectCopie" id="selectCopieId" style="width:100%">		
	 		<c:forEach var="copie" items="${copies}">
				<c:choose>		
				    <c:when test="${idCopieSelectionnee != copie.idCopie}">
	   					<option value="<c:out value="${copie.idCopie}"/>" ><c:out value="${copie}" />
	 				</c:when>
	 					
	 				<c:otherwise>
	 					<option value="<c:out value="${copie.idCopie}"/>" selected="selected"><c:out value="${copie}" />
	 				</c:otherwise>			
				</c:choose>	
			</c:forEach>
		</select>
	
	<br><br>
	
<!-- ci dessous , le bouton valider execute la fonction javascript "emprunterCopie();" du javascript donc le lien est en bas de la page  -->
	<input id = "emprunterCopie" type="button" value ="Emprunter copie selectionnée" onclick="emprunterCopie();">
	
		<select name="selectAbonne" id="selectAbonneId" style="width:250px" >
			<option value="<c:out value="selection"/>" ><c:out value="-------- Selectionner un abonné --------" />
	 		<c:forEach var="abonne" items="${abonnes}">
				<option value="<c:out value="${abonne.idAbonne}"/>" ><c:out value="${abonne}" />
			</c:forEach>	
		</select>
	
		<!-- ci dessous, le formulaire qui va servir a recuperer les id abonne et copie pour effectuer l'emprunt -->
		<form id="emprunt" action="<c:url value="/emprunterCopie"/>" method="GET">
			<input id="IdCopieEmprunt" name="IdCopieEmprunt" type="hidden" value="" >
			<input id="IdAbonneEmprunt" name="IdAbonneEmprunt" type="hidden" value="" >
			<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >
				
		</form>
		
<!-- ci dessous , le bouton valider execute la fonction javascript "retournerCopie();" du javascript donc le lien est en bas de la page  -->
	<input id = "retournerCopie" type="button" value ="Retourner copie selectionnée" onclick="retournerCopie();">
	
		<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer le retour -->
		<form id="retour" action="<c:url value="/retournerCopie"/>" method="GET">
			<input id="IdCopieRetour" name="IdCopieRetour" type="hidden" value="" >
			<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >
			
		</form>
	
<!-- ci dessous , le bouton valider execute la fonction javascript "emprunterCopie();" du javascript donc le lien est en bas de la page  -->
	<input id = "supprimerCopie" type="button" value ="Supprimer copie selectionnée" onclick="supprimerCopie();">
	
		<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer la suppression -->
		<form id="suppression" action="<c:url value="/supprimerCopie"/>" method="GET">
			<input id="IdCopieSuppression" name="IdCopieSuppression" type="hidden" value="" >
			<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >
			
		</form>
		
<!-- ci dessous , le bouton valider execute la fonction javascript reparerCopie();" du javascript donc le lien est en bas de la page  -->
	<input id = "reparerCopie" type="button" value ="Envoyer copie selectionnée en réparation" onclick="reparerCopie();">
	
		<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer la suppression -->
		<form id="reparation" action="<c:url value="/reparerCopie"/>" method="GET">
			<input id="IdCopieReparation" name="IdCopieReparation" type="hidden" value="" >
			<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >
			
		</form>
		
<!-- ci dessous , le bouton ajouter copie  -->	
	<a href="<c:url value="/ajouterCopie?isbn=${oeuvre.isbn}"/>"><input type="button" name="creerCopie" value="Ajouter copie" /></a><br /><br />
	<br>
	
	
	<a href="<c:url value="/listeOeuvres"/>">Retour</a>
	<br>
	<a href="<c:url value="/accueil"/>">Accueil</a>
	
	<!--lien vers le language jquery et le fichier javascript des 3 fonctions : emprunter, retourner et supprimer -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="<c:url value="/JS/consultationOeuvre.js"/>"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	<script src="bootstrap/js/bootstrap.min.js" ></script>
	
</body>

</html>


