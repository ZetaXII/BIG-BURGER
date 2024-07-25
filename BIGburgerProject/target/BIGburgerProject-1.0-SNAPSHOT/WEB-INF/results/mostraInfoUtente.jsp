<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Mostra info utente</title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
<div>
    <%@  include file="/navBar.jsp" %>
</div>
<%
    if(u==null || !(u.getRuolo().equalsIgnoreCase("a")))
    {
        response.sendRedirect("/index.jsp");
    }
    else
    {
        Utente utente= (Utente) request.getAttribute("utente");
        String ruolo="nullo";
        if(utente.getRuolo().equalsIgnoreCase("u"))
        {
            ruolo="Utente";
        }
        else if(utente.getRuolo().equalsIgnoreCase("a"))
        {
            ruolo="Admin";
        }
        else if(utente.getRuolo().equalsIgnoreCase("f"))
        {
            ruolo="Fattorino";
        }
%>
<h1 class="h1 display-1 mt-4 titolo">INFORMAZIONI UTENTE #<%=utente.getId()%>:</h1>

<div class="container">
    <div class="row">
        <div class="col-sm">

        </div>
        <div class="col-sm-8">
            <a href="mostraListaUtentiServlet"><button class="btn btn-dark w-100 mt-4 mb-4" style="background-color: #f7701b; border: none;">Mostra tutti gli utenti</button></a>
            <div class="container container-infoUtente mt-3 mb-3 text-center">
                <div class="row">
                    <div class="col-sm-12">
                        <img src="https://thumbs.dreamstime.com/b/profilo-utente-vettoriale-avatar-predefinito-179376714.jpg" class="img-infoUtente img img-fluid">
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <h1 class="m-3">
                            <%= utente.getNome().toUpperCase()+" "+utente.getCognome().toUpperCase() %>
                        </h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm">
                        <p class="fs-5">
                            <%= utente.getEmail() %>
                        </p>
                    </div>
                    <div class="col-sm">
                        <p class="fs-5">
                            +39 <%= utente.getTelefono() %>
                        </p>
                    </div>
                    <div class="col-sm">
                        <p class="fs-5">
                            <%= ruolo %>
                        </p>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-sm-12">
                        <h3>
                            <form action="mostraUtenteServlet" method="post">
                                <input type="hidden" name="idUtente" value="<%=utente.getId()%>">
                                <input type="hidden" name="modifica" value="1">
                                <input type="submit" value="Modifica Dati" name="Modifica" class="btn btn-primary w-100">
                            </form>
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
