<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<head>
		<title>BIBLIOTHEQUE - Oeuvres</title>
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
				<a href="<c:url value="/listeAuteurs"/>"><input type="button" class="btn btn-primary boutonAGauche" name="ajoutAbonne" value="RETOUR" /></a>
				<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
			</div>	
				
			<div>
				<h2 class="titreTableau margeHaut">AJOUT OEUVRE</h2>
			</div>
		
			<form method="post" action="<c:url value="/validerAjouterOeuvre"/>">
				<br><br>
				
				<div class="row">
					<div class="col-lg-4 col-sm-6">
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">ISBN</font> 
						</div>
						<div class="centrer-div">
							<INPUT name="isbn" class="input centrageInput ombre"></INPUT>
						</div>
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">Titre</font> 
						</div>
						<div class="centrer-div">
							<INPUT name="titre" class="input centrageInput ombre"></INPUT>
						</div>
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">Sous-titre</font> 
						</div>
						<div class="centrer-div">
							<INPUT name="sousTitre" class="input centrageInput ombre"></INPUT>
						</div>
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">Date d'édition</font> 
						</div>
						<div class="centrer-div">
							<INPUT name="dateOeuvre" class="input centrageInput ombre"></INPUT>
						</div>
					</div><br>
					<br><br>
					
					<div class="col-lg-4 col-sm-6">
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">AUTEUR</font>
						</div>
						<div class="centrer-div3">
							<select name="selectAuteur" id="auteurs" class="input centrageInput ombre">
								<option value="0" ><c:out value="----- Selectionner un auteur -----"/>
				   				<c:forEach var="auteur" items="${auteurs}">
									<option value="<c:out value="${auteur.idAuteur}"/>" ><c:out value="${auteur}" />
								</c:forEach>
							</select>
						</div>
						<br>
						<br>
						
						<fieldset class="fielset ombre">
							<legend class="legend libelle">CREER AUTEUR</legend>
							<div class="titreTableau libelle">
								<font size=5 COLOR="white">Prenom</font> 
							</div>
							<div class="centrer-div">
								<INPUT name="prenom" class="input centrageInput"></INPUT><br/><br/>
							</div>
							<div class="titreTableau libelle">
								<font size=5 COLOR="white">Nom</font> 
							</div>
							<div class="centrer-div">
								<INPUT name="nom" class="input centrageInput"></INPUT><br/><br/>
							</div>
							<div class="titreTableau libelle">
								<font size=5 COLOR="white">Date de naissance</font>
							</div>
							<div class="centrer-div"> 
								<INPUT name="dateDeNaissance" value="JJ/MM/YYYY" class="input centrageInput"></INPUT> <br/><br/>
							</div>
						</fieldset>
						<br>
					</div>
			
					<div class="col-lg-4 col-sm-12">
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">GENRE</font>
						</div>
						<div class="centrer-div3">
							<select name="selectGenre" id="genres" class="input centrageInput ombre">
								<option value="0"><c:out value="----- Selectionner un genre -----"/>
				   				<c:forEach var="genre" items="${genres}">
									<option><c:out value="${genre.nomGenre}" />
								</c:forEach>
							</select>
						</div>
						<br><br>
						
						<fieldset class="fielset ombre">
							<legend class="legend2 libelle">CREER GENRE</legend>
							<div class="titreTableau libelle">
								<font size=5 COLOR="white">Libellé du genre</font> 
							</div>
						
							<div class="centrer-div">
								<INPUT name="libelle" class="input centrageInput" ></INPUT>
							</div><br>
						</fieldset>
					
					</div>
					
					<div class="centrer-div2">	<br>
						<input type="submit" name="validerAjoutOeuvre" class="btn btn-primary margeBas" value="VALIDER AJOUT OEUVRE" />
					</div>
		
				</div>
					
			</form>

		</div>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	 	<script src="bootstrap/js/bootstrap.min.js" ></script>
	 	<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>


