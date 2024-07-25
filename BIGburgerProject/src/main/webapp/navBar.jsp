<%@ page import="model.Utente" %>
<%@ page import="model.ElementoCarrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Utente u= (Utente) session.getAttribute("utente");

    ArrayList<ElementoCarrello> carrello= new ArrayList<>();

    if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
    {
        if(session.getAttribute("carrello")!=null)
        {
            carrello= (ArrayList<ElementoCarrello>) session.getAttribute("carrello");
        }
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/favicon.ico" type="image/x-icon">

    <title>NavBar</title>

</head>
<body>
<nav class="navbar">
    <a href="<%=request.getContextPath()%>/index.jsp" style="text-decoration: none;"><img src="<%=request.getContextPath()%>/img/BigBurgerLogo.png" class="logo" /></a>
    <ul class="menu">
        <%
        if(u==null)
        {
        %>
        <li>
            <a class="opzioni" href="menuServlet">Scopri il nostro men&ugrave;</a>
        </li>

        <li>
            <a class="opzioni" href="chiSiamo.jsp">Chi siamo?</a>
        </li>
        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("u"))
        {
        %>
        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/menuServlet">Scopri il nostro men&ugrave;</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet">I miei ordini</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/chiSiamo.jsp">Chi siamo?</a>
        </li>

        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("a"))
        {
        %>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/menuServlet">Gestisci men&ugrave;</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet">Gestisci ordini</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaCategorie.jsp">Visualizza categorie</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaUtentiServlet">Gestisci utenti</a>
        </li>

        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("f"))
        {
        %>
        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/menuServlet">Visualizza men&ugrave;</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet">Visualizza tutti gli incarichi</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet?consegnati=no">I miei incarichi</a>
        </li>

        <li>
            <a class="opzioni" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet?consegnati=si">I miei incarichi completati</a>
        </li>
        <%
        }
        %>
    </ul>

    <ul class="altro">
        <%
        if(u==null)
        {
        %>
        <li>
            <a href="<%=request.getContextPath()%>/login.jsp" class="opzioni"><i class="fa fa-user me-2" aria-hidden="true"></i> Accedi </a>
        </li>
        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("u"))
        {
        %>
        <li>
            <button class="me-2 opzioni btnCarrello"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Carrello (<%=carrello.size()%>)</button>
        </li>
        <li>
            <a class="me-2 opzioni" href="infoUtente.jsp"><i class="fa fa-user me-2" aria-hidden="true"></i> Ciao, <%= u.getNome().toUpperCase() %> </a>
        </li>

        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("a") || u.getRuolo().equalsIgnoreCase("f"))
        {
        %>

        <li>
            <a class="me-2 opzioni" href="<%=request.getContextPath()%>/infoUtente.jsp"><i class="fa fa-user me-2" aria-hidden="true"></i>Ciao, <%=u.getNome().toUpperCase()%></a>
        </li>

        <%
            }
        %>
    </ul>

    <button class="hamburger"><i class="fas fa-bars"></i></button>

</nav>

<!-- MENU PER SMARTPHONE O TABLET -->

<div class="menuCompatto">
    <ul>

        <%
        if(u==null)
        {
        %>

        <li>
            <a class="me-2"  href="<%=request.getContextPath()%>/menuServlet">Scopri il nostro men&ugrave;</a>
        </li>

        <li>
            Chi siamo?
        </li>

        <li>
            <a href="<%=request.getContextPath()%>/login.jsp"><i class="fa fa-user me-2" aria-hidden="true"></i> Accedi </a>
        </li>

        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("u"))
        {
        %>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/menuServlet">Scopri il nostro men&ugrave;</a>
        </li>

        <li>
            Chi siamo?
        </li>

        <li class="nav-item">
            <a class="me-2" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet">I miei ordini</a>
        </li>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/carrello.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Carrello (<%=carrello.size()%>)</a>
        </li>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/infoUtente.jsp"><i class="fa fa-user me-2" aria-hidden="true"></i>Ciao, <%=u.getNome().toUpperCase()%></a>
        </li>
        <%
        }
        else if(u.getRuolo().equalsIgnoreCase("a"))
        {
        %>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/menuServlet">Gestisci men&ugrave;</a>
        </li>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/mostraListaOrdiniServlet">Gestisci ordini</a>
        </li>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/mostraListaUtentiServlet">Gestisci utenti</a>
        </li>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/mostraListaCategorie.jsp">Visualizza categorie</a>
        </li>

        <li>
            <a class="me-2" href="<%=request.getContextPath()%>/infoUtente.jsp"><i class="fa fa-user me-2" aria-hidden="true"></i>Ciao, <%=u.getNome().toUpperCase()%></a>
        </li>

        <%
            }
        %>
        <li>
            <button class="btnChiudiMenuCompatto">Chiudi</button>
        </li>
    </ul>
</div>

<%
    if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
    {
        double tot=0.0;
%>

<div class="mt-4 container text-black divCarrello overflow-auto">
    <div class="container">
        <div class="row">
            <div class="col-sm">

            </div>

            <div class="col-sm">

            </div>

            <div class="col-md-4 bg-light text-center border border-dark d-none d-xl-block overflow-auto carrello">
                <div class="container mt-4 mb-4" style="top:6%; position: absolute;">
                    <button class="me-2 d-md-none d-lg-table-cell bg-light btnChiudiCarrello"><i class="fas fa-times-circle" id="iconaChiudi"></i></button>
                    <%@ include file="mostraCarrello.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>
<%
    }
%>

<div class="alert alert-danger alert-dismissible fade show text-center" id="messaggioErrore" role="alert" data-dismiss="alert">
    <strong>ERRORE!</strong> <em id="testo-errore"> </em>
</div>

<script src="<%=request.getContextPath()%>/script/mostraCarrello.js" defer></script>

<script src="<%=request.getContextPath()%>/script/mostraMenuCompatto.js" defer></script>

</body>
</html>
