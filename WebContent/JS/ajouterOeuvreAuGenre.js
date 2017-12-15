// fonction check qui permet de recupérer les isbn des oeuvres cochées
function check() {
	// note : les termes apres des "$" sont du jquery
	
	// on cré une variable qui va contenir les isbn des oeuvres cochées qu'on va concaténer entre des slashs "/"
	var txt = "";
	// pour chaque "checked",
	$("input:checked").each(function(){
		// on recupere la value de la premiere colone apres la case (si elle est cochée)
		txt += ';' + ($(this).parent().next('td').text());
	});
	// alert(txt);// alerte pour verifier si besoin
	
	// on met les isbn concaténées dans un formulaire qui contient un input "hidden"
	$("#concatenationisbn").val(txt);
	
	// si rien n'est selectionné, rien ne sera concaténé et donc on ne veut pas changer de page et afficher une alerte
	if (txt === ""){
		alert("veuillez selectionner something")
	}else{
		// puis on soumet le formulaire
		$("#concat").submit();
	}
	

}
