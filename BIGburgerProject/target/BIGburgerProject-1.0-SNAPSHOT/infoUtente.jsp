<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Mostra info utente</title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <div>
        <%@  include file="navBar.jsp" %>
    </div>
    <%
        if(u==null)
        {
            response.sendRedirect("index.jsp");
        }
        else
        {
            String ruolo="nullo";
            if(u.getRuolo().equalsIgnoreCase("u"))
            {
                ruolo="Utente";
            }
            else if(u.getRuolo().equalsIgnoreCase("a"))
            {
                ruolo="Admin";
            }
            else if(u.getRuolo().equalsIgnoreCase("f"))
            {
                ruolo="Fattorino";
            }
    %>
    <h1 class="h1 display-1 mt-4 titolo">INFORMAZIONI UTENTE:</h1>

    <div class="container">
        <div class="row">
            <div class="col-sm">

            </div>
            <div class="col-sm-8">
                <div class="container container-infoUtente mt-3 mb-3 text-center">
                    <div class="row">
                        <div class="col-sm-12">
                            <img src="https://thumbs.dreamstime.com/b/profilo-utente-vettoriale-avatar-predefinito-179376714.jpg" class="img-infoUtente img img-fluid">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <h1 class="m-3">
                                <%= u.getNome().toUpperCase()+" "+u.getCognome().toUpperCase() %>
                            </h1>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm">
                            <p class="fs-5">
                                <%= u.getEmail() %>
                            </p>
                        </div>
                        <div class="col-sm">
                            <p class="fs-5">
                                +39 <%= u.getTelefono() %>
                            </p>
                        </div>
                        <div class="col-sm">
                            <p class="fs-5">
                                <%= ruolo %>
                            </p>
                        </div>
                    </div>

                    <div class="row mt-4">
                        <div class="col-sm-6">
                            <h3>
                                <a href="logout"><button class="btn btn-danger w-100">Logout</button></a>
                            </h3>
                        </div>
                        <div class="col-sm-6">
                            <h3>
                                <a href="modificaInfoUtente.jsp"><button class="btn btn-primary w-100">Modifica</button></a>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm">

            </div>
        </div>
    </div>


    <%
        }
    %>
    <footer class="mt-auto">
        <%@  include file="/footer.jsp" %>
    </footer>
</body>
</html>
