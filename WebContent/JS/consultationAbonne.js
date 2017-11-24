// fonctions qui permet de recupérer les id copie et abonne cochées et d'envoyer le formulaire correspondant

// les termes apres des "$" sont du jquery

function retournerCopie() {
	
	// on crée une variable qui contenir l'id de la copie
	var copieSelected = document.getElementById("selectCopieId");
	var idCopie = copieSelected.options[copieSelected.selectedIndex].value;
	
		// on met l'id copie dans un formulaire qui contient un input "hidden"
		$("#IdCopieRetour").val(idCopie);
	
	// puis on soumet le formulaire correspondant
	$("#retour").submit();
}
