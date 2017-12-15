<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<head>
		<title>BIBLIOTHEQUE - Auteur</title>
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
				<a href="<c:url value="/listeAuteurs"/>"><input type="button" class="btn btn-primary boutonAGauche" value="RETOUR" /></a>
				<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
			</div>
			
			<div>
				<h2 class="titreTableau margeHaut">INFORMATIONS AUTEUR</h2><BR>  
			</div>
			
			<div class="titreTableau libelle">
				<font size=5><c:out value="${auteur}"/></font><br>
			</div><br><br>
	
			<div class="container-fluid">
  				<div class="row">
					<div class="centrer-div board">
						<font size=5 COLOR="black">OEUVRES</font><br>
					</div>
				</div>
			</div>
			<div>
				<select class="ombre backgroundListe" size = 10 name="selectOeuvre" id="selectIsbn" style="width:100%">		
			 		<c:forEach var="oeuvre" items="${oeuvres}">
						<c:choose>		
						    <c:when test="${isbnSelectionne != oeuvre.isbn}">
			   					<option value="<c:out value="${oeuvre.isbn}"/>" ><c:out value="${oeuvre}" />
			 				</c:when>
			 					
			 				<c:otherwise>
			 					<option value="<c:out value="${oeuvre.isbn}"/>" selected="selected"><c:out value="${oeuvre}" />
			 				</c:otherwise>			
						</c:choose>	
					</c:forEach>
				</select>
			</div>
			
			<br>
	
		</div>
	
		<!--lien vers le language jquery et le fichier javascript des 3 fonctions : retourner  -->
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
		<script src="bootstrap/js/bootstrap.min.js" ></script>
		<script src="<c:url value="/JS/consultationAbonne.js"/>"></script>
		<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>


