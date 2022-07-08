<%@page import="fr.eni.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

<section class="research-section">
     <form class="formInscription" action="<%= request.getContextPath() %>/les-encheres" method="post">
        <input  class="roundRadius form-control form-control-lg inputResearch" type="text" name="research" placeholder="Trouvez l'objet de vos envies...">

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


            
            <div class="selectionArticle">

                <div class="achat">
                    <div>
                        <input type="radio" id="achat" name="achatVente" value="achat" checked>
                        <label for="achat">Achat</label>
                    </div>    
                    <div id="Container-checkEncheres">

                        <div>
                            
                            <input class="EnchereCheckBox" type="checkbox" id="enchereOuverte" value="ouverte" name="enchere"  checked>
                            <label for="enchereOuverte">Enchère ouverte</label>
                        </div>
                        <div>
                            
                            <input class="EnchereCheckBox" type="checkbox" id="EnCours" name="enchere" value="enCour"  >
                            <label for="enchereEnCours">Mes enchères en cours</label>
                        </div>
                        <div>
                            
                            <input class="EnchereCheckBox" type="checkbox" id="enchereEnTermines" name="enchere" value="termine" >
                            <label for="enchereEnTermines">Mes enchères remportées</label>
                        </div>


                    </div>                
                </div>

    
                <div class="vente">
                    <div>
                        <input type="radio" id="vente" name="achatVente" value="vente" >                           
                        <label for="vente">vente</label>
                    </div>
                    <div id="Container-checkVente">
                        <div>
                            
                            <input class="VenteCheckBox" type="checkbox" id="venteEnCours" name="vente" value="venteEnCour" >
                            <label for="venteEnCours">Mes ventes en cours</label>
                        </div>
                        <div>
                            
                            <input class="VenteCheckBox" type="checkbox" id="venteNonDebute" name="vente" value="venteNonDebute" >
                            <label for="venteNonDebute">Mes ventes non débutées</label>
                        </div>
                        <div>
                            
                            <input class="VenteCheckBox" type="checkbox" id="ventesTermines" name="vente" value="venteTermine" >
                            <label for="ventesTermines">Mes ventes terminées</label>
                        </div>
                    </div>  
                    
                </div>


            </div>
            
            <button type="submit" class="montserrat600 bg-blue btn roundRadius SB30 H5"> Rechercher </button>
        </div>
        
    </form>


</section>

<%@include file="/WEB-INF/jspf/affichageArticles.jspf" %>


</body>

<%@include file="/WEB-INF/jspf/script.jspf" %>
</html>