<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<title>BIBLIOTHEQUE - Oeuvre</title>
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
			<a href="<c:url value="/listeOeuvres"/>"><input type="button" class="btn btn-primary boutonAGauche" name="ajoutAbonne" value="RETOUR" /></a>
			<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
		</div>
	
		<div>
			<h2 class="titreTableau">CONSULTATION OEUVRE</h2><BR>  
		</div>
		
		<div class="titreTableau libelle">
			<font size=4><c:out value="${oeuvre}"/></font><br>
		</div><br>
		
		<div class="container-fluid">
 			<div class="row">
				<div class="centrer-div board">
					<font size=5 COLOR="black">COPIES EN CIRCULATION</font><br>
				</div>
			</div>
		</div>
		<div>
			<select class="ombre backgroundListe" size = 10 name="selectCopie" id="selectCopieId" style="width:100%">		
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
		</div>
		
		<br><br>
		<div class="row margeBas">	
			<div class="col-lg-6">
			<!-- ci dessous , le bouton valider execute la fonction javascript "emprunterCopie();" du javascript donc le lien est en bas de la page  -->
				<input class="btn btn-success boutonAGauche" id = "emprunterCopie" type="button" value ="Emprunter copie selectionnée" onclick="emprunterCopie();">
				
				<select class="btn margeGauche" name="selectAbonne" id="selectAbonneId" style="width:320px" >
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
			</div>	
			<div class="col-lg-6">	
			<!-- ci dessous , le bouton valider execute la fonction javascript reparerCopie();" du javascript donc le lien est en bas de la page  -->
				<input class="btn btn-warning boutonADroite" id = "reparerCopie" type="button" value ="Envoyer copie selectionnée en réparation" onclick="reparerCopie();">
				
				<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer la suppression -->
				<form id="reparation" action="<c:url value="/reparerCopie"/>" method="GET">
					<input id="IdCopieReparation" name="IdCopieReparation" type="hidden" value="" >
					<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >	
				</form>
			</div>
		</div>
		
		<div class="row margeBas">	
			<div class="col-lg-6">
			<!-- ci dessous , le bouton valider execute la fonction javascript "retournerCopie();" du javascript donc le lien est en bas de la page  -->
				<input class="btn btn-primary boutonAGauche" id = "retournerCopie" type="button" value ="Retourner copie selectionnée" onclick="retournerCopie();">
				
				<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer le retour -->
				<form id="retour" action="<c:url value="/retournerCopie"/>" method="GET">
					<input id="IdCopieRetour" name="IdCopieRetour" type="hidden" value="" >
					<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >
				</form>
			</div>
			
			<div class="col-lg-6 text-center margeBas">	
				<!-- ci dessous , le bouton valider execute la fonction javascript "emprunterCopie();" du javascript donc le lien est en bas de la page  -->
				<input class="btn btn-danger boutonADroite" id = "supprimerCopie" type="button" value ="Supprimer copie selectionnée" onclick="supprimerCopie();">
				
				<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer la suppression -->
				<form id="suppression" action="<c:url value="/supprimerCopie"/>" method="GET">
					<input id="IdCopieSuppression" name="IdCopieSuppression" type="hidden" value="" >
					<input id="IsbnOeuvre" name="IsbnOeuvre" type="hidden" value="${oeuvre.isbn}" >		
				</form>
				
			</div>
			
			
		</div>	
		<div class="row margeBas">	
			<div class="col-lg-12">
				<!-- ci dessous , le bouton ajouter copie  -->	
					<a href="<c:url value="/ajouterCopie?isbn=${oeuvre.isbn}"/>"><input class="btn btn-success boutonADroite" type="button" name="creerCopie" value="Ajouter copie" /></a><br /><br />
			</div>
		</div>
	<br>

</div>	
	<!--lien vers le language jquery et le fichier javascript des 3 fonctions : emprunter, retourner et supprimer -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="<c:url value="/JS/consultationOeuvre.js"/>"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	<script src="bootstrap/js/bootstrap.min.js" ></script>
	<script src="<c:url value="/JS/background.js"/>"></script>
	
</body>

</html>


