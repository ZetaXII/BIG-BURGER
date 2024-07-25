<%@ page import="model.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ServletContext context= session.getServletContext();
    ArrayList<Prodotto> prodotti= (ArrayList<Prodotto>) context.getAttribute("prodotti");
    ArrayList<Commento> commenti= (ArrayList<Commento>) context.getAttribute("commenti");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>BIG Burger | Benvenuto!</title>

</head>
<body class="d-flex flex-column min-vh-100">
    <div>
        <%@  include file="navBar.jsp" %>
    </div>

    <h1 class="h1 display-1 text-center mt-3 titolo">BENVENUTO IN BIGBURGER!</h1>
    <hr>
    <div class="w-100">
        <%@ include file="nuoviArrivi.jsp" %>
    </div>

    <div class="container-informazioni text-light">
        <div class="container-commenti text-light">
            <h2 class="h3 display-4 text-light text-center mb-2 titolo">COMMENTI RECENTI:</h2>
            <div class="mt-4 container container-fluid text-dark">
                <div class="container container-fluid">
                    <div class="row">
                        <div class="col-sm-1">

                        </div>

                        <div class="col-sm-10 text-light">
                            <%
                                for(Commento c : commenti)
                                {
                            %>
                            <div class="container-commento">
                                <div class="utente-commento">
                                    <img src="https://thumbs.dreamstime.com/b/profilo-utente-vettoriale-avatar-predefinito-179376714.jpg" class="img-utente">
                                    <p class="nome-utente"><%=c.getUtente().getNome().charAt(0)+". "+c.getUtente().getCognome()%></p>
                                </div>
                                <p class="data-commento">Recensito il <%=c.getData()%></p>
                                <p class="stelle"><i class="fa-solid fa-star"></i> <%=c.getStelle()%> stelle</p>
                                <div class="descrizione-commento">
                                    <em>"<%=c.getCommento()%>."</em>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <a href="mostraCommentiServlet"><button class="btn btn-block btn-dark mb-2 w-100" style="background-color: #f7701b; border:none">Mostra tutti</button></a>
                        </div>

                        <div class="col-sm-1">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-mappa">
            <h2 class="h3 display-4 text-light text-center mb-2 titolo">CI TROVI QUI:</h2>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3009.51629342758!2d14.295861551221037!3d41.035837325575365!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133a55454c697f8b%3A0xfd25a167215a4be3!2sBullo%20Burger!5e0!3m2!1sit!2sit!4v1651397250932!5m2!1sit!2sit" width="100%" height="500" allowfullscreen></iframe>
        </div>
    </div>

    <footer class="mt-auto">
        <%@  include file="footer.jsp" %>
    </footer>
</body>
</html>