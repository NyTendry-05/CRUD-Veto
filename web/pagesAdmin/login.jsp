<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/boxicons-2.1.4/css/boxicons.min.css">
    <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body>
    <div class="container">
        <div class="row">
            <form method="post" action="<%= request.getContextPath() %>/Login" class="form-horizontal">
                <div class="form-group">
                    <label for="username" class="form-label">Nom d'utilisateur</label>
                    <input type="text" name="username" id="username" class="form-control" value="Freddy">
                </div>
                <div class="form-group">
                    <label for="pwd" class="form-label">Mot de passe</label>
                    <input type="password" name="pwd" id="pwd" class="form-control" value="1234">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        Se connecter
                    </button>
                    <a href="../Sitting" class="btn btn-default">Se connecter en utilisateur simple</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>