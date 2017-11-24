// fonctions qui permet de recupérer les id copie et abonne cochées et d'envoyer le formulaire correspondant

// les termes apres des "$" sont du jquery

function emprunterCopie() {

	// on crée une variable qui contenir l'id de la copie
	var copieSelected = document.getElementById("selectCopieId");
	var idCopie = copieSelected.options[copieSelected.selectedIndex].value;
	
		// on met l'id copie dans un formulaire qui contient un input "hidden"
		$("#IdCopieEmprunt").val(idCopie);
	
	// on crée une variable qui contenir l'id de l'abonne
	var AbonneSelected = document.getElementById("selectAbonneId");
	var idAbonne = AbonneSelected.options[AbonneSelected.selectedIndex].value;
	
		// on met l'id abonne dans un formulaire qui contient un input "hidden"
		$("#IdAbonneEmprunt").val(idAbonne);
	
	// puis on soumet le formulaire correspondant
	$("#emprunt").submit();
}

function retournerCopie() {
	
	// on crée une variable qui contenir l'id de la copie
	var copieSelected = document.getElementById("selectCopieId");
	var idCopie = copieSelected.options[copieSelected.selectedIndex].value;
	
		// on met l'id copie dans un formulaire qui contient un input "hidden"
		$("#IdCopieRetour").val(idCopie);
	
	// puis on soumet le formulaire correspondant
	$("#retour").submit();
}

function supprimerCopie() {
	
	// on crée une variable qui contenir l'id de la copie
	var copieSelected = document.getElementById("selectCopieId");
	var idCopie = copieSelected.options[copieSelected.selectedIndex].value;
	
		// on met l'id copie dans un formulaire qui contient un input "hidden"
		$("#IdCopieSuppression").val(idCopie);
	
	// puis on soumet le formulaire correspondant
	$("#suppression").submit();
	
}

function reparerCopie() {

	// on crée une variable qui contenir l'id de la copie
	var copieSelected = document.getElementById("selectCopieId");
	var idCopie = copieSelected.options[copieSelected.selectedIndex].value;
	
		// on met l'id copie dans un formulaire qui contient un input "hidden"
		$("#IdCopieReparation").val(idCopie);
	
	// puis on soumet le formulaire correspondant
	$("#reparation").submit();
	
}