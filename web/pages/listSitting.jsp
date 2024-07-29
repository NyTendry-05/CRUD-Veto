<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/boxicons-2.1.4/css/boxicons.min.css">
    <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/main.css">
</head>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="entities.Garde" %>
<%@page import="entities.Animal" %>
<%@page import="entities.Sitting" %>
<% Sitting[] list=(Sitting[])request.getAttribute("list"); %>
<% Animal[] listAnimaux=(Animal[])request.getAttribute("listAnimaux"); %>
<% Garde[] listGardes=(Garde[])request.getAttribute("listGardes"); %>
<body>
    <header>
        <a href="#" class="logo">Veto</a>

        <form action="Sitting" method="get" class="form-inline">
            <label class="form-label" for="idAnimal">Animal</label>
            <select name="idAnimal" id="idAnimal" class="form-control">
                <option value="0">Tous</option>
                <% for (int i=0; i!=listAnimaux.length; i++) { %>
                    <option value="<%= listAnimaux[i].getId() %>"><%= listAnimaux[i].getNom() %></option>
                <% } %>
            </select>
            <label class="form-label" for="idGarde">Garde</label>
            <select name="idGarde" id="idGarde" class="form-control">
                <option value="0">Tous</option>
                <% for (int i=0; i!=listGardes.length; i++) { %>
                    <option value="<%= listGardes[i].getId() %>"><%= listGardes[i].getNom() %></option>
                <% } %>
            </select>
            <br><br>
            <label class="form-label" for="debut">Début</label>
            <input type="datetime-local" placeholder="Debut" name="debut" id="debut" class="form-control">
            <label class="form-label" for="fin">Fin</label>
            <input type="datetime-local" placeholder="Fin" name="fin" id="fin" class="form-control">
            <button type="submit" class="btn btn-default">Rechercher</button>
        </form>

        <ul class="nav-list">
            <li>
                <a href="#" class="nav-link active">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                        viewBox="0 0 24 24">
                        <path
                            d="m21.743 12.331-9-10c-.379-.422-1.107-.422-1.486 0l-9 10a.998.998 0 0 0-.17 1.076c.16.361.518.593.913.593h2v7a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-4h4v4a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-7h2a.998.998 0 0 0 .743-1.669z">
                        </path>
                    </svg>
                </a>
            </li>
            <li>
                <a href="Proprietaire" class="nav-link">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                        viewBox="0 0 24 24">
                        <path
                            d="M20 4H4c-1.103 0-2 .897-2 2v12c0 1.103.897 2 2 2h16c1.103 0 2-.897 2-2V6c0-1.103-.897-2-2-2zM8.715 8c1.151 0 2 .849 2 2s-.849 2-2 2-2-.849-2-2 .848-2 2-2zm3.715 8H5v-.465c0-1.373 1.676-2.785 3.715-2.785s3.715 1.412 3.715 2.785V16zM19 15h-4v-2h4v2zm0-4h-5V9h5v2z">
                        </path>
                    </svg>
                </a>
            </li>
            <li>
                <a href="Animal" class="nav-link">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                        viewBox="0 0 24 24">
                        <path
                            d="M21 6h-2l-1.27-1.27A2.49 2.49 0 0 0 16 4h-2.5A2.64 2.64 0 0 0 11 2v6.36a4.38 4.38 0 0 0 1.13 2.72 6.57 6.57 0 0 0 4.13 1.82l3.45-1.38a3 3 0 0 0 1.73-1.84L22 8.15a1.06 1.06 0 0 0 0-.31V7a1 1 0 0 0-1-1zm-5 2a1 1 0 1 1 1-1 1 1 0 0 1-1 1z">
                        </path>
                        <path
                            d="M11.38 11.74A5.24 5.24 0 0 1 10.07 9H6a1.88 1.88 0 0 1-2-2 1 1 0 0 0-2 0 4.69 4.69 0 0 0 .48 2A3.58 3.58 0 0 0 4 10.53V22h3v-5h6v5h3v-8.13a7.35 7.35 0 0 1-4.62-2.13z">
                        </path>
                    </svg>
                </a>
            </li>
            <li>
                <a href="Garde" class="nav-link">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                        viewBox="0 0 24 24">
                        <path
                            d="M12 2C6.579 2 2 6.579 2 12s4.579 10 10 10 10-4.579 10-10S17.421 2 12 2zm0 5c1.727 0 3 1.272 3 3s-1.273 3-3 3c-1.726 0-3-1.272-3-3s1.274-3 3-3zm-5.106 9.772c.897-1.32 2.393-2.2 4.106-2.2h2c1.714 0 3.209.88 4.106 2.2C15.828 18.14 14.015 19 12 19s-3.828-.86-5.106-2.228z">
                        </path>
                    </svg>
                </a>
            </li>

            <% if (session.getAttribute("user")!=null) { %>
                <li>
                    <a href="<%= request.getContextPath() %>/pagesAdmin/logout.jsp"
                        class="nav-link">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                            viewBox="0 0 24 24">
                            <path d="M16 13v-2H7V8l-5 4 5 4v-3z"></path>
                            <path
                                d="M20 3h-9c-1.103 0-2 .897-2 2v4h2V5h9v14h-9v-4H9v4c0 1.103.897 2 2 2h9c1.103 0 2-.897 2-2V5c0-1.103-.897-2-2-2z">
                            </path>
                        </svg>
                    </a>
                </li>
            <% } else { %>
                <a href="<%= request.getContextPath() %>/Login" class="nav-link">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" 
                        viewBox="0 0 24 24">
                        <path d="m13 16 5-4-5-4v3H4v2h9z"></path>
                        <path
                            d="M20 3h-9c-1.103 0-2 .897-2 2v4h2V5h9v14h-9v-4H9v4c0 1.103.897 2 2 2h9c1.103 0 2-.897 2-2V5c0-1.103-.897-2-2-2z">
                        </path>
                    </svg>
                </a>
            <% } %>
        </ul>

        <div class="bx bx-menu" id="menu-icon"></div>
    </header>

    <div class="container">
        <h1>Liste des tours de garde</h1>
        <table class="table">
            <% if (session.getAttribute("user")==null) { %>
                <tr>
                    <th>Id</th>
                    <th>IdAnimal</th>
                    <th>Nom de l'animal</th>
                    <th>idGarde</th>
                    <th>Nom du garde</th>
                    <th>Début</th>
                    <th>Fin</th>
                </tr>

                <% for (int i=0; i!=list.length; i++) { %>
                    <tr>
                        <td>
                            <%= list[i].getId() %>
                        </td>
                        <td>
                            <%= list[i].getIdAnimal() %>
                        </td>
                        <td>
                            <%= list[i].getAnimal().getNom() %>
                        </td>
                        <td>
                            <%= list[i].getIdGarde() %>
                        </td>
                        <td>
                            <%= list[i].getGarde().getNom() %>
                        </td>
                        <td>
                            <%= list[i].getFormattedDebut() %>
                        </td>
                        <td>
                            <%= list[i].getFormattedFin() %>
                        </td>
                    </tr>
                    <% } %>
                        <% } else { %>
                            <tr>
                                <th>Id</th>
                                <th>IdAnimal</th>
                                <th>Nom de l'animal</th>
                                <th>idGarde</th>
                                <th>Nom du garde</th>
                                <th>Début</th>
                                <th>Fin</th>
                                <th></th>
                                <th></th>
                            </tr>

                            <% for (int i=0; i!=list.length; i++) { %>
                                <tr>
                                    <td>
                                        <%= list[i].getId() %>
                                    </td>
                                    <td>
                                        <%= list[i].getIdAnimal() %>
                                    </td>
                                    <td>
                                        <%= list[i].getAnimal().getNom() %>
                                    </td>
                                    <td>
                                        <%= list[i].getIdGarde() %>
                                    </td>
                                    <td>
                                        <%= list[i].getGarde().getNom() %>
                                    </td>
                                    <td>
                                        <%= list[i].getFormattedDebut() %>
                                    </td>
                                    <td>
                                        <%= list[i].getFormattedFin() %>
                                    </td>
                                    <td><a href="FormSitting?id=<%= list[i].getId() %>" class="btn btn-success">Edit</a></td>
                                    <td><a href="Sitting?mod=d&id=<%= list[i].getId() %>"
                                            class="btn btn-danger">Delete</a></td>
                                </tr>
                                <% } %>
                                    <% } %>
        </table>

        <% if (session.getAttribute("user")!=null) { %>
            <a href="FormSitting" class="nav-link" id="more">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                    <path
                        d="M12 2C6.486 2 2 6.486 2 12s4.486 10 10 10 10-4.486 10-10S17.514 2 12 2zm5 11h-4v4h-2v-4H7v-2h4V7h2v4h4v2z">
                    </path>
                </svg>
            </a>
        <% } %>
    </div>
</body>
</html>