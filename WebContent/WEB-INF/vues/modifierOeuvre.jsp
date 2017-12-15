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
				<a href="<c:url value="/listeOeuvres"/>"><input type="button" class="btn btn-primary boutonAGauche" name="ajoutAbonne" value="RETOUR" /></a>
				<a href="<c:url value="/accueil"/>"><input type="button" class="btn btn-success boutonADroite margeBas" name="ajoutAbonne" value="ACCUEIL" /></a><br>
			</div>	
				
			<div>
				<h2 class="titreTableau margeHaut">MODIFICATION OEUVRE</h2>
			</div>
		
		<form method="post" action="<c:url value="/validerModifierOeuvre"/>">
		
			<div class="row">
				<div class="col-lg-4 col-sm-6">
					<div class="titreTableau libelle">
						<font size=5>ISBN :</font> 
					</div>
					<div class="centrer-div">
						<INPUT class="input centrageInput" name="isbn" value="${oeuvre.isbn}" readonly="readonly"></INPUT><br/><br/>
					</div>
					<div class="titreTableau libelle">
						<font size=5>Titre :</font> 
					</div>
					<div class="centrer-div">
						<INPUT class="input centrageInput" name="titreOeuvre" value="${oeuvre.titreOeuvre}"></INPUT><br/><br/>
					</div>
					<div class="titreTableau libelle">
						<font size=5>Sous-titre :</font> 
					</div>
					<div class="centrer-div">
						<INPUT class="input centrageInput" name="sousTitreOeuvre" value="${oeuvre.sousTitreOeuvre}"></INPUT><br/><br/>
					</div>
					<div class="titreTableau libelle">
						<font size=5>Date d'édition (JJ/M/AAAA) :</font> 
					</div>
					<div class="centrer-div">
						<INPUT class="input centrageInput" name="dateOeuvre" value = <fmt:formatDate value="${oeuvre.dateOeuvre.time}" pattern="dd/MM/yyyy"/> ></INPUT> <br/><br/>
					</div>
					
				</div>
				<br><br>
			
				<div class="col-lg-4 col-sm-6">
					<div class="titreTableau libelle">
						<font size=5 >AUTEUR</font>
					</div>
					<div class="centrer-div">
						<input type='hidden' name = "idAuteur" value="<c:out value="${oeuvre.auteur.idAuteur}"/>">			
						<INPUT name="auteur" class="input centrageInput" value="${oeuvre.auteur}" readonly="readonly"></INPUT>
					</div><BR>
					<div class="titreTableau libelle">
						<font size=5>CHANGER AUTEUR</font>
					</div>	
					<div class="centrer-div3">
						<select name="selectAuteur" id="auteurs" style="width:100% ; overflow: scroll">
							<option value="Aucun" ><c:out value="---------- Selectionner un auteur ----------"/>
			   				<c:forEach var="auteur" items="${auteurs}">
			   				
				   				<c:choose>		   				
								    <c:when test="${idAuteurSelectionne != auteur.idAuteur}">
		       							<option value="<c:out value="${auteur.idAuteur}"/>" ><c:out value="${auteur}" />
			    					</c:when>
			    					
			    					<c:otherwise>
			        					<option value="<c:out value="${auteur.idAuteur}"/>" selected="selected"><c:out value="${auteur}" />
			    					</c:otherwise>		
								</c:choose>
								
							</c:forEach>
						</select><BR>
					</div><br><br>
					<div class="centrageInput">
							<a href="<c:url value="/ajouterAuteur?page=modification"/>"><input class="btn btn-success margeBas" type="button" name="creerAuteur" value="CREER NOUVEL AUTEUR" /></a><br /><br />
						</div>
					<br>
					<br>
			
					</div>
					<div class="col-lg-4 col-sm-12">
						<div class="titreTableau libelle">
							<font size=5 COLOR="white">GENRE</font>
						</div>
						
						<div class="centrer-div">
							<INPUT class="input centrageInput" name="genre" style ="width: 100%" value="${oeuvre.nomDuGenre}" readonly="readonly"></INPUT>
						</div><br>
						
						<div class="titreTableau libelle">
							<font size=5 COLOR="white"> CHANGER GENRE</font>
						</div>
						
						<div class="centrer-div3">
							<select style ="width: 100%" name="selectGenre" id="genres" class="input centrageInput ombre">
								<option value="0"><c:out value="-------- Selectionner un genre --------"/>
				   				<c:forEach var="genre" items="${genres}">
									<option><c:out value="${genre.nomGenre}" />
								</c:forEach>
							</select>
						</div>
		
					</div>
			
					<div class="centrer-div2">	<br>
						<input class="btn btn-primary margeBas" type="submit" value="Enregistrer modifications" />
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

