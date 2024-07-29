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
<body>
    <div class="container">
        <div class="row">
            <% if (request.getAttribute("entity")==null) { %>
                <form method="post" action="<%= request.getContextPath() %>/Proprietaire">
                    <div class="form-group">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" name="nom" id="nom" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="coordonnees" class="form-label">Coordonnées</label>
                        <input type="text" name="coordonnees" id="coordonnees" class="form-control"> 
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            <% } else { 
                    Proprietaire proprietaire=(Proprietaire)request.getAttribute("entity");
                %>
                <form method="post" action="<%= request.getContextPath() %>/Proprietaire">
                    <input type="hidden" name="id" value="<%= proprietaire.getId() %>">
                    <div class="form-group">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" name="nom" id="nom" value="<%= proprietaire.getNom() %>" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="coordonnees" class="form-label">Coordonnées</label>
                        <input type="text" name="coordonnees" id="coordonnees" value="<%= proprietaire.getCoordonnees() %>" class="form-control"> 
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