<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jspf/head.jspf" %>
 <link rel="stylesheet" href="css/styles_inscription.css">
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

</head>
<body>

<% Utilisateur userProfil = (Utilisateur) request.getAttribute("userProfil");  %>


<div id="infoUser" class="info-utilisateur">
	<p>Speudo : <%= userProfil.getPseudo() %></p>
	<p>Nom : <%= userProfil.getNom() %></p>
	<p>Prenom : <%= userProfil.getPrenom() %> </p>
	<p>Email : <%= userProfil.getEmail() %> </p>
	<p>Téléphone : <%= userProfil.getTelephone() %></p>
	<p>Adresse : <%= userProfil.getRue() %> </br> <%= userProfil.getVille() %> <%= userProfil.getCodePostal() %> </p>


</div>

<!-- AFFICHAGE BOUTON MODIFICATION PROFIL -->
<% 

int noPageProfil = Integer.parseInt(request.getParameter("id"));
if(user != null && noPageProfil  == user.getNoUtilisateur()){ %>
<div class="container-btn">
	<input style="margin:auto; " id="btnUpdate" class="montserrat600 bg-blue btn roundRadius SB30 H5" type="submit" value="Modifier mon profil" onclick="affichageFormulaire()">
</div>

<form style="display: none; " id="formulaire" class="formInscription" action="<%= request.getContextPath() %>/utilisateur" method="post">
   <div class="formInscription" > 
   
	<div class="container-form">
	    <div class="input-icons">
	        <i class="fa fa-user icon"></i>
	        <input class="input-field roundRadius" value="<%= user.getPseudo() %>" type ="text" id="idPseudo" name="pseudo" required="required" placeholder="votre speudo" >
	    </div>
	    <div class="input-icons">
	        <i class="fa fa-user icon"></i>
	        <input class="input-field roundRadius" value="<%= user.getNom() %>" type ="text" id="idNom" name="nom" required="required" placeholder="votre nom" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-user icon"></i>
	        <input class="input-field roundRadius" value="<%= user.getPrenom() %>" type ="text" id="idPrenom" name="prenom" required="required" placeholder="votre prénom" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-at icon"></i>
	        <input class="input-field roundRadius" value="<%= user.getEmail() %>" type ="email" id="idEmail" name="email" required="required" placeholder="votre email" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-phone icon"></i> 
	        <input class="input-field roundRadius" value="<%= user.getTelephone() %>" type ="tel" id="idTelephone" name="telephone" required="required" placeholder="votre téléphone" inputmode="tel" pattern="^\+?\d{0,13}" >
	    </div>	
	
	</div>
	   
	<div class="container-form">
	    <div class="input-icons">
	        <i class="fa fa-map icon"></i> 
	        <input class="input-field roundRadius" value="<%= user.getRue() %>" type ="text" id="idRue" name="rue" required="required" placeholder="votre adresse">
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-envelope icon"></i>     
	        <input class="input-field roundRadius" value="<%= user.getCodePostal() %>" type ="number" id="idCodePostal" name="codePostal" required="required" placeholder="votre code postal" inputmode="numeric" pattern="^(?(^00000(|-0000))|(\d{5}(|-\d{4})))$">
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-map icon"></i>    
	        <input class="input-field roundRadius" value="<%= user.getVille() %>" type ="text" id="idVille" name="ville" required="required" placeholder="votre ville" >
	    </div>	
	    <div class="input-icons">
	        <i class="fa fa-lock icon"></i>   
	        <input class="input-field roundRadius" type ="password" id="idMotdePassee" name="motDePasse"  placeholder="votre MotDePasse" >
	    </div>
	    <div class="input-icons">
	        <i class="fa fa-lock icon"></i>         
	        <input class="input-field roundRadius" type ="password" id="idConfirmation" name="confirmation"  placeholder="Confirmer votre mot de passe" >
	    </div>
	</div>  
	
</div>
	<div class="groupeBtn">
        <a href="<%=request.getContextPath() %>/utilisateur?id=<%=user.getNoUtilisateur() %>" >Annuler</a>
        <input class="montserrat600 bg-blue btn roundRadius SB30 H5" type="submit" value="Enregistrer les modifications">
    </div>   
        
        
    
</form>
    
<%} %>
</body>


<script>


function affichageFormulaire(){
	document.getElementById("infoUser").style.display = "none"; 
	document.getElementById("btnUpdate").style.display = "none";
	document.getElementById("formulaire").style.display = "block"; 
}

</script>
</html>