<%@ page import="model.Prodotto" %>
<%
    Prodotto prodotto= (Prodotto) request.getAttribute("prodotto");
    String prezzo= String.valueOf(prodotto.getPrezzo());
    if(prezzo.length()<=4)
    {
        prezzo=prezzo+"0";
    }
    prezzo=prezzo.replace(".",",");
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/style.css">
    <title>Mostra info di <%= prodotto.getNome() %></title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <div>
        <%@  include file="/navBar.jsp" %>
    </div>

    <div class="mt-4 container text-black">
        <div class="container">
            <div class="row">
                <div class="col-sm">

                </div>

                <div class="col-sm-9 mb-4 mt-4">
                    <div class="container container-infoProdotto">
                        <a href="menuServlet"><button class="btn btn-dark"><i class="fa fa-arrow-left"></i></button></a>
                        <span class="titolo">
                            <h1 class="display-4 nome-infoProdotto"><%=prodotto.getNome().toUpperCase()%></h1>
                            <p class="h2 tipo-infoProdotto"><%=prodotto.getCategoria().toUpperCase()%></p>
                        </span>
                        <div class="infoProdotto">
                            <img src="<%=prodotto.getImmagine()%>" class="img-fluid"/>
                            <p class="h4 descrizione"><em>"<%=prodotto.getDescrizione()%>."</em></p>
                            <p class="display-3 prezzo"><%=prezzo%>&euro;</p>
                        </div>
                    </div>
                </div>

                <div class="col-sm">

                </div>
            </div>
        </div>
    </div>
    <footer class="mt-auto">
        <%@  include file="/footer.jsp" %>
    </footer>
</body>
</html>
