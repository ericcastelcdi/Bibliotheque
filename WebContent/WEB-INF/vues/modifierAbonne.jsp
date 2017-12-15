<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<head>
		<title>BIBLIOTHEQUE - Personnes</title>
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
				<h2 class="titreTableau margeHaut">MODIFICATION ABONNE</h2>
			</div>
			
			<form method="post" action="<c:url value="/validerModifierAbonne"/>">
				<br><br>
				<div class="titreTableau libelle">
					<font size=5 COLOR="white">Id Abonné</font>
				</div>
				<div class="centrer-div">
					<INPUT class="input centrageInput ombre" name="idAbonne" value="${abonne.idAbonne}" readonly="readonly"></INPUT> <br>
				</div>
				<div class="titreTableau libelle">
					<font size=5 COLOR="white">Id Personne</font>
				</div>
				<div class="centrer-div">
					<INPUT class="input centrageInput ombre" name="id" value="${abonne.id}" readonly="readonly"></INPUT> <br>
				</div>
				<div class="titreTableau libelle">
					<font size=5 COLOR="white">Prenom</font>
				</div>
				<div class="centrer-div">
					<INPUT class="input centrageInput ombre" name="prenom" value="${abonne.prenom}"></INPUT> <br>
				</div>
				<div class="titreTableau libelle">
					<font size=5 COLOR="white">Nom</font>
				</div>
				<div class="centrer-div">
					<INPUT class="input centrageInput ombre" name="nom" value="${abonne.nom}"></INPUT> <br />
				</div>
				<div class="titreTableau libelle">
					<font size=5 COLOR="white">Date de naissance (JJ/MM/AAAA)</font>
				</div>
				<div class="centrer-div">
					<INPUT class="input centrageInput ombre" name="dateDeNaissance" value = <fmt:formatDate value="${abonne.dateDeNaissance.time}" pattern="dd/MM/yyyy"/> ></INPUT><br />
				</div>
				<br>
				
				<div class="centrer-div titreTableau"><br/>
					<input type="submit" class="btn btn-primary margeBas" value="ENREGISTRER MODIFICATIONS" />
				</div>
		
			</form>
		</div>

		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	 	<script src="bootstrap/js/bootstrap.min.js" ></script>
	 	<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>

