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
<%@page import="entities.Animal" %>
<%@page import="entities.Garde" %>
<%@page import="entities.Sitting" %>
<% Garde[] listGarde=(Garde[])request.getAttribute("listGardes"); %>
<% Animal[] listAnimaux=(Animal[])request.getAttribute("listAnimaux"); %>
<body>
    <div class="container">
        <div class="row">
            <% if (request.getAttribute("entity")==null) { %>
                <form method="post" action="<%= request.getContextPath() %>/Sitting">
                    <div class="form-group">
                        <label for="idAnimal" class="form-label">Animal</label>
                        <select name="idAnimal" id="idAnimal" class="form-control">
                            <% for (int i=0; i!=listAnimaux.length; i++) { %>
                                <option value="<%= listAnimaux[i].getId() %>"><%= listAnimaux[i].getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="idGarde" class="form-label">Garde</label>
                        <select name="idGarde" id="idGarde" class="form-control">
                            <% for (int i=0; i!=listGarde.length; i++) { %>
                                <option value="<%= listGarde[i].getId() %>"><%= listGarde[i].getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="debut" class="form-label">Début</label>
                        <input type="datetime-local" name="debut" id="debut" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="fin" class="form-label">Fin</label>
                        <input type="datetime-local" name="fin" id="fin" class="form-control">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            <% } else { 
                    Sitting sitting=(Sitting)request.getAttribute("entity");
                %>
                <form method="post" action="<%= request.getContextPath() %>/Sitting">
                    <input type="hidden" name="id" value="<%= sitting.getId() %>">

                    <div class="form-group">
                        <label for="idAnimal" class="form-label">Animal</label>
                        <select name="idAnimal" id="idAnimal" class="form-control">
                            <% for (int i=0; i!=listAnimaux.length; i++) { %>
                                <% if (listAnimaux[i].getId()==sitting.getIdAnimal()) { %>
                                    <option value="<%= listAnimaux[i].getId() %>" selected><%= listAnimaux[i].getNom() %></option>
                                <% } else { %>
                                    <option value="<%= listAnimaux[i].getId() %>"><%= listAnimaux[i].getNom() %></option>
                                <% } %>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="idGarde" class="form-label">Garde</label>
                        <select name="idGarde" id="idGarde" class="form-control">
                            <% for (int i=0; i!=listGarde.length; i++) { %>
                                <% if (listGarde[i].getId()==sitting.getIdGarde()) { %>
                                    <option value="<%= listGarde[i].getId() %>" selected><%= listGarde[i].getNom() %></option>
                                <% } else { %>
                                    <option value="<%= listGarde[i].getId() %>"><%= listGarde[i].getNom() %></option>
                                <% } %>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="debut" class="form-label">Début</label>
                        <input type="datetime-local" name="debut" id="debut" class="form-control" value="<%= sitting.getFormattedDebut() %>">
                    </div>
                    <div class="form-group">
                        <label for="fin" class="form-label">Fin</label>
                        <input type="datetime-local" name="fin" id="fin" class="form-control" value="<%= sitting.getFormattedFin() %>">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            <% } %>
        </div>
    </div>
    <% if (request.getAttribute("error") != null) { 
        String msg = (String)request.getAttribute("error");
     %>
        <script>
        alert('<%= msg %>');
        </script>
    <% } %>
</body>
</html>