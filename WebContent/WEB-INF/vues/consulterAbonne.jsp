<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<head>
		<title>BIBLIOTHEQUE - Abonne</title>
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
				<a href="<c:url value="/listeAbonnes"/>"><input type="button" class="btn btn-primary boutonAGauche" name="ajoutAbonne" value="RETOUR" /></a>
				<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
			</div>
			
			<div>
				<h2 class="titreTableau margeHaut">PROFIL ABONNE</h2><BR>  
			</div>
			
			<div class="titreTableau libelle">
				<font size=5><c:out value="${abonne}"/></font><br>
			</div><br><br>
	
			<div class="container-fluid">
  				<div class="row">
					<div class="centrer-div board">
						<font size=5 COLOR="black">EMPRUNTS EN COURS</font><br>
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
			
			<!-- ci dessous, le formulaire qui va servir a recuperer les id copie pour effectuer le retour -->
			<form id="retour" action="<c:url value="/retournerCopieAbonne"/>" method="GET">
				<input id="IdCopieRetour" name="IdCopieRetour" type="hidden" value="" >
				<input id="IdAbonne" name="IdAbonne" type="hidden" value="${abonne.idAbonne}" >
				
			</form>
			<br>
	
			<!-- ci dessous , le bouton valider execute la fonction javascript "retournerCopie();" du javascript donc le lien est en bas de la page  -->
			<input class="btn btn-success margeBas boutonAGauche" id = "retournerCopie" type="button" value ="RETOURNER COPIE" onclick="retournerCopie();">
			<a href="<c:url value="/listeOeuvres"/>"><input  class="btn btn-primary margeBas boutonADroite" type="button" name="emprunterDesCopies" value="EFFECTUER EMPRUNT(S)" /></a>
	</div>
	
		<!--lien vers le language jquery et le fichier javascript des 3 fonctions : retourner  -->
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
		<script src="bootstrap/js/bootstrap.min.js" ></script>
		<script src="<c:url value="/JS/consultationAbonne.js"/>"></script>
		<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>


