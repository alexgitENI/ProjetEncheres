<%@page import="fr.eni.encheres.gestionerreurs.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/head.jspf" %>
  <link rel="stylesheet" href="css/styles_inscription.css">
    <link rel="stylesheet"
    href=
"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">   

</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/affichageErreur.jspf" %>

<form class="formInscription" action="<%= request.getContextPath() %>/inscription" method="post">
   <div class="formInscription" > 
	<div class="container-form">
	    <div class="input-icons">
	        <i class="fa fa-user icon"></i>
	        <input class="input-field roundRadius" type ="text" id="idPseudo" name="pseudo" required="required" placeholder="votre speudo" >
	    </div>
	    <div class="input-icons">
	        <i class="fa fa-user icon"></i>
	        <input class="input-field roundRadius" type ="text" id="idNom" name="nom" required="required" placeholder="votre nom" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-user icon"></i>
	        <input class="input-field roundRadius" type ="text" id="idPrenom" name="prenom" required="required" placeholder="votre prénom" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-at icon"></i>
	        <input class="input-field roundRadius" type ="email" id="idEmail" name="email" required="required" placeholder="votre email" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-phone icon"></i> 
	        <input class="input-field roundRadius" type ="tel" id="idTelephone" name="telephone" required="required" placeholder="votre téléphone" inputmode="tel" pattern="^\+?\d{0,13}" >
	    </div>	
	
	</div>
	   
	<div class="container-form">
	    <div class="input-icons">
	        <i class="fa fa-map icon"></i> 
	        <input class="input-field roundRadius" type ="text" id="idRue" name="rue" required="required" placeholder="votre adresse">
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-envelope icon"></i>     
	        <input class="input-field roundRadius" type ="number" id="idCodePostal" name="codePostal" required="required" placeholder="votre code postal" inputmode="numeric" pattern="^(?(^00000(|-0000))|(\d{5}(|-\d{4})))$">
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-map icon"></i>   
	        <input class="input-field roundRadius" type ="text" id="idVille" name="ville" required="required" placeholder="votre ville" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-lock icon"></i>   
	        <input class="input-field roundRadius" type ="password" id="idMotdePassee" name="motDePasse" required="required" placeholder="votre MotDePasse" >
	    </div>
	    <div class="input-icons">
	        <i class="fa fa-lock icon"></i>         
	        <input class="input-field roundRadius" type ="password" id="idConfirmation" name="confirmation" required="required" placeholder="Confirmer votre mot de passe" >
	    </div>
	</div>  
	
</div>
<div class="groupeBtn">
        <a href="<%=request.getContextPath() %>" >Annuler</a>
        <input class="montserrat600 bg-blue btn roundRadius SB30 H5" type="submit" value="valider">
       
        
        
    

    </div>
    </form>
    



</body>


<script>
document.getElementById("idPseudo").setAttribute("value","MonSpeudo");
document.getElementById("idEmail").setAttribute("value","mail@gmail.com");
document.getElementById("idNom").setAttribute("value","MonNom");
document.getElementById("idPrenom").setAttribute("value","MonPrenom");
document.getElementById("idTelephone").setAttribute("value","0698657485");
document.getElementById("idRue").setAttribute("value","9 rue du clos");
document.getElementById("idCodePostal").setAttribute("value","95000");
document.getElementById("idVille").setAttribute("value","Paris");
document.getElementById("idMotdePasse").setAttribute("value","123");
document.getElementById("idConfirmation").setAttribute("value","123");


</script>

<%@include file="/WEB-INF/jspf/script.jspf" %>
</html>