

<%@page import="fr.eni.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>


<section class="research-section">

 	<div class="slogan">
        <h1>Donnez <span>une</span>  seconde vie </br> <span>à vos</span> objets</h1>
    </div>
    <form>
        <input  class="roundRadius form-control form-control-lg inputResearch" type="text" name="research" placeholder="Trouvez l'objet de vos envies...">
		<!-- Faire itération à partir des catagorie recupérer sur serveur -->
        <div class="flex-spaceb centered montserrat-normal'">
            <select class="H5 montserrat-normal custom-select roundRadius SB30 bg-blue">
                <option selected value="all" > Toutes les Catégories</option>
                
                <%
                 List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("Categories");
                for(Categorie categorie : listeCategories){
                	String str = categorie.getLibelle();
                	String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                %>
                 <option value="<%= categorie.getNoCategorie() %>"> <%= cap %> </option>             
                
                <%
                }
                %>
                
            </select>

            <a  href="<%= request.getContextPath() %>/les-encheres" class="montserrat-normal">plus d'options</a>
            <button type="submit" class="montserrat600 bg-blue btn roundRadius SB30 H5"> Rechercher </button>
        </div>
        
    </form>


</section>

<%@include file="/WEB-INF/jspf/affichageArticles.jspf" %>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>

<%@include file="/WEB-INF/jspf/script.jspf" %>
</html>