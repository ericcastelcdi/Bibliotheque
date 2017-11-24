<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>

	<head>
		<title>BIBLIOTHEQUE - Genres</title>
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
			<div class="margeHaut">
				<a href="<c:url value="/listeGenres"/>"><input type="button" class="btn btn-primary boutonAGauche" name="ajoutAbonne" value="RETOUR" /></a>
				<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
			</div>
			<div>
				<h2 class="titreTableau margeHaut">AJOUT GENRE</h2>
			</div>
				<form method="post" action="<c:url value="/validerAjouterGenre"/>">
					<br><br>
					<div class="titreTableau libelle">
						<font size=5 COLOR="white" >Libellé du genre :</font>
					</div>
					<div class="centrer-div">
						<INPUT name="nomGenre" class="input centrageInput ombre"></INPUT> <br /><br />
					</div>
					<br>
					<br>
					<div class="centrer-div titreTableau">
						<input type="submit" value="VALIDER" class="btn btn-primary margeBas"/>
					</div>
			
				</form>
		
		</div>
		
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	 	<script src="bootstrap/js/bootstrap.min.js" ></script>
	 	<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>


