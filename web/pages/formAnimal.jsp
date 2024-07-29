<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form</title>
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/boxicons-2.1.4/css/boxicons.min.css">
    <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="entities.Proprietaire" %>
<%@page import="entities.Animal" %>
<% Proprietaire[] proprietaires=(Proprietaire[])request.getAttribute("listProprietaires"); %>
<body>
    <div class="container">
        <div class="row">
            <% if (request.getAttribute("entity")==null) { %>
                <form method="post" action="<%= request.getContextPath() %>/Animal">
                    <div class="form-group">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" name="nom" id="nom" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="idProprietaire" class="form-label">Propriétaire</label>
                        <select name="idProprietaire" id="idProprietaire" class="form-control">
                            <% for (int i=0; i!=proprietaires.length; i++) { %>
                                <option value="<%= proprietaires[i].getId() %>"><%= proprietaires[i].getNom() %></option>
                            <% } %>
                        </select>   
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            <% } else { 
                    Animal animal=(Animal)request.getAttribute("entity");
                %>
                <form method="post" action="<%= request.getContextPath() %>/Animal">
                    <input type="hidden" name="id" value="<%= animal.getId() %>">

                    <div class="form-group">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" name="nom" id="nom" value="<%= animal.getNom() %>" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="idProprietaire" class="form-label">Propriétaire</label>
                        <select name="idProprietaire" id="idProprietaire" class="form-control">
                            <% for (int i=0; i!=proprietaires.length; i++) { %>
                                <% if (proprietaires[i].getId()==animal.getIdProprietaire()) { %>
                                    <option value="<%= proprietaires[i].getId() %>" selected><%= proprietaires[i].getNom() %></option>
                                <% } else { %>
                                    <option value="<%= proprietaires[i].getId() %>"><%= proprietaires[i].getNom() %></option>
                                <% } %>
                            <% } %>
                        </select>   
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            <% } %>
        </div>
    </div>
</body>
</html>