<%@page import="fr.eni.encheres.bo.Article"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="fr.eni.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="css/nouvelleVente.css">
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/affichageErreur.jspf" %>


 <% Article article = (Article) request.getAttribute("article");  %>
<h2>Modification de l'enchere</h2>



<form class="form" action="<%= request.getContextPath() %>/modification-enchere" method="post" enctype="multipart/form-data">
   <div class="formNouvelleVente" > 

    
    <img id="output" width="200"  src="asset/img/ImgArticles/Article<%=article.getNoArticle()%>.jpg">
    <output id="list"></output>
	<div class="container-form">
        <div class="maxWidth">
            <div class="input-icons">
                <i class="fa fa-user icon"></i>
                <input class="input-field roundRadius" type ="text" id="idNomArticle" name="nomArticle" required="required" value="<%=article.getNomArticle() %>" placeholder="Nom de votre article" >
            </div>
            <div class="input-icons">
                <i class="fa fa-user icon"></i>
                <textarea class="input-field roundRadius" type ="text" id="idDescription" name="description"  placeholder="Décrivez votre article en quelques mots" ><%=article.getDescription() %></textarea>
            </div>	
         </div>
	     <select class="H5 montserrat-normal custom-select roundRadius SB30 bg-blue" name="categorie" id="categorie">
                <option value="" disabled hidden>Selectionner une catégorie</option>
                
                <%
                 List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("Categories");
                for(Categorie categorie : listeCategories){
                	String str = categorie.getLibelle();
                	String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                %>
                 <option <% if(article.getNoCategorie()==categorie.getNoCategorie()){%> selected <%} %>   value="<%= categorie.getNoCategorie() %>"> <%= cap %> </option>             
                
                <%
                }
                %>
                
         </select>


         <div class="input-icons">

            <label tabindex="0" for="my-file" class="">Choisir une image</label>
            <div class="input-file-container">  
                <input class="input-file " name="photoProduit" id="photoProduit" accept="image/jpg, image/png" type="file" onchange="loadFile(event)">
               
              </div>
            
              <p class="file-return"></p>
         </div>
        
        
        <p> Mise à prix : <%= article.getPrixInitial() %> (vous ne pouvez pas modifier le prix initial)</p>
        
        	    
	     
	    
	    
	    
	   <p id="test"></p>
	    <p>Retrait</p>
			<c:set var="rue" value="<%= user.getRue() %>"/>
			<c:set var="code" value="<%= user.getCodePostal() %>"/>
			<c:set var="ville" value="<%= user.getVille() %>"/>
	
<div>
           <label for="idPrix"> Rue :</label>
	        <i class="fa fa-map icon"> </i>	
	        <c:choose>	
	        	<c:when test="${(rue != null)}">		        
	        <input class="input-field roundRadius" type ="text" id="idRue" name="rue" required="required" value="<c:out value="${rue}"/>">
   					
  				</c:when>
  				<c:when test="${(rue == null)}">
  					<input class="input-field roundRadius" type ="text" id="idRue" name="rue" required="required" value="Veuillez renseigner votre rue de retrait">
  					
  				</c:when>
  
			</c:choose>  	 
	    </div>	
	    <div>
           <label for="idPrix"> Code postal :</label>
	        <i class="fa fa-map icon"> </i>	
	        <c:choose>	
	        	<c:when test="${(code != null)}">
	        	<input class="input-field roundRadius" value="<c:out value="${code}"/>" type ="number" id="idCodePostal" name="codePostal" required="required" placeholder="votre code postal" inputmode="numeric" pattern="^(?(^00000(|-0000))|(\d{5}(|-\d{4})))$">		        

   					
  				</c:when>
  				<c:when test="${(code == null)}">
  					<input class="input-field roundRadius" type ="text" id="idCodePostal" name="codePostal" required="required" value="Veuillez renseigner votre code postal de retrait">
  					
  				</c:when>
  
			</c:choose>  	 
	    </div>
        
        <div>
           <label for="idPrix"> Ville :</label>
	        <i class="fa fa-map icon"> </i>	
	        <c:choose>	
	        	<c:when test="${(ville != null)}">
	        	<input class="input-field roundRadius" value="<c:out value="${ville}"/>" type ="text" id="idVille" name="ville" required="required" placeholder="votre code postal">		        

   					
  				</c:when>
  				<c:when test="${(ville == null)}">
  					<input class="input-field roundRadius" type ="text" id="idVille" name="ville" required="required" value="Veuillez renseigner votre code postal de retrait">
  					
  				</c:when>
  
			</c:choose>  	 
	    </div>	
	    

    
	   
        
	     
		<div class="groupeBtn">
	        <a href="<%= request.getContextPath()%>/article?idArticle=<%= article.getNoArticle() %>">Annuler les modifications</a>
        	<input class="montserrat600 bg-blue btn roundRadius SB30 H5" type="submit" value="Enregisrer les modifications">
       	</div>
        
        
    

    
    </form>


		<a href="">Annuler la vente</a>


</body>
<script>


function ajouterJour(days) {
	
		
	  let dateDebut = document.getElementById("idDebutEncheres").value; 
	  let dateFinEncheres = document.getElementById("idFinEncheres");
	  
	  var result = new Date(dateDebut);
	  
	  result.setDate(result.getDate() + days);
	  //document.getElementById("test").innerHTML = result.toISOString().substring(0,16); 
	  dateFinEncheres.value = result.toISOString().substring(0,16);
	}


//bouton uploadfichier
var loadFile = function(event) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(event.target.files[0]);
};


</script>


</html>