<%@ page import="model.ElementoCarrello" %>
<%@ page import="model.Prodotto" %>
<%
    double totale= 0.0;
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Carrello</title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <%
        if(u==null || !(u.getRuolo().equalsIgnoreCase("u")))
        {
            response.sendRedirect("login.jsp");
        }
        else
        {
    %>
    <h1 class="h2 display-4 mt-4 titolo">CARRELLO:</h1>
    <div class="mt-2 container container-fluid text-dark">
        <div class="container container-fluid">
            <div class="row">
                <div class="col-sm">

                </div>

                <div class="col-sm-12">

        <%
            if(carrello!=null && carrello.size()>0)
            {
                String prezzoTot= String.valueOf(0);
        %>
                <div class="table-responsive">
                <table class="table table-striped text-center">
                    <tr class="text-light titolo-riga">
                        <th class="d-none d-md-table-cell mt-4 mb-4 text-light"></th>
                        <th class="fs-5 mt-4 mb-4 text-center text-light">Prodotto</th>
                        <th class="fs-5 mt-4 mb-4 d-none d-md-table-cell text-light">Prezzo (singolo)</th>
                        <th class="fs-5 mt-4 mb-4 text-light">Prezzo (totale)</th>
                        <th class="fs-5 mt-4 mb-4 text-light">Qt.</th>
                        <th></th>
                        <th></th>
                    </tr>
        <%
                for(ElementoCarrello el : carrello)
                {
                    Prodotto p= el.getProdotto();
                    String prezzoSingolo= String.valueOf(p.getPrezzo());
                    String prezzoEl= String.valueOf(p.getPrezzo()*el.getQuantita());

                    if(prezzoSingolo.length()<=4)
                    {
                        prezzoSingolo=prezzoSingolo+"0";
                    }
                    prezzoSingolo=prezzoSingolo.replace(".",",");

                    if(prezzoEl.length()<=4)
                    {
                        prezzoEl=prezzoEl+"0";
                    }
                    prezzoEl=prezzoEl.replace(".",",");

                    totale= totale+((el.getProdotto().getPrezzo())*el.getQuantita());
        %>
                    <tr>
                        <td class="d-none d-md-table-cell"><img src="<%=p.getImmagine()%>" class="img mt-4 mb-4 img-prodottoMenu" width="100" height="100"></td>
                        <td><p class="h4 mt-4 mb-4 fs-5"><a style="text-decoration: none;" class="text-dark fw-bold" href="mostraProdottoServlet?id=<%=p.getId()%>"><%=p.getNome().toUpperCase()%></a></td>
                        <td class="d-none d-md-table-cell"><p class="mt-4 mb-4 fs-5"><%=prezzoSingolo%>&euro;</p></td>
                        <td><p class="mt-4 mb-4 fs-5"><%=prezzoEl%>&euro;</p></td>
                        <td>
                            <form action="modificaProdottoDalCarrelloServlet" method="get" class="form-responsive mt-4">
                                <input type="hidden" name="idProdotto" value="<%=p.getId()%>">
                                <input type="number" name="quantita" value="<%=el.getQuantita()%>" min="1" max="25" class="form-control" id="qt" required>
                                <input type="submit" value="Aggiorna" class="btn btn-primary btn-block mt-3">
                            </form>
                        </td>
                        <td><a href="eliminaProdottoDalCarrelloServlet?id=<%=p.getId()%>" class="btn btn-danger mt-4 mb-4">Rimuovi</a></td>
                    </tr>
        <%
                        prezzoTot= String.valueOf(totale);
                        if(prezzoTot.length()<=4)
                        {
                            prezzoTot=prezzoTot+"0";
                        }
                        prezzoTot=prezzoTot.replace(".",",");
                }
        %>
                    <tr class="text-center titolo-riga"><td class="h3 fw-bold text-light" colspan="4">TOTALE:</td><th class="h3 text-light" colspan="2"><%=prezzoTot%>&euro;</th><td></td></tr>
                </table>
                </div>
                <form method="post" action="ordinaServlet" class="form-inline form-responsive">
                    <div class="input-group">
                        <input type="text" name="via" placeholder="Via" class="form-control" required>
                        <input type="number" name="nCivico" placeholder="N. civico" class="form-control" required>
                        <input type="hidden" name="totale" value="<%=prezzoTot.replace(",",".")%>">
                        <input type="submit" value="ORDINA ORA" class="btn btn-block btn-success">
                    </div>
                </form>
        <%

            }
            else
            {
        %>
                <h3 class="h3 display-4 mt-4">Carrello vuoto!</h3>
        <%
            }
        %>
                </div>

                <div class="col-sm">

                </div>
            </div>
        </div>
    </div>
<%
    }
%>
</body>
</html>
