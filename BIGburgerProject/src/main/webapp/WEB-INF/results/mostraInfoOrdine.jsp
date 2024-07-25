<%@ page import="model.ElementoCarrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.Ordine" %>
<%
    ArrayList<ElementoCarrello> elementiOrdinati=null;
    elementiOrdinati= (ArrayList<ElementoCarrello>) request.getAttribute("elementiOrdinati");
    Ordine o= (Ordine) request.getAttribute("ordine");
    Utente f= (Utente) request.getAttribute("fattorino");
    String oraConsegna="In attesa di consegna";
    String fattorino="Nessun fattorino ha preso in carico l'ordine, attendi";
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>ORDINE #<%=o.getId()%></title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <div>
        <%@  include file="/navBar.jsp" %>
    </div>
    <h1 class="h2 display-4 mt-4 titolo" id="ordAjax">ORDINE #<%=o.getId()%></h1>

    <%
        if(o.getOraConsegnato()!=null && !(o.getOraConsegnato().equalsIgnoreCase("")))
        {
            oraConsegna=o.getOraConsegnato();
        }

        if(f!=null)
        {
            fattorino= f.getNome()+" "+f.getCognome()+" (TELEFONO: +39 "+f.getTelefono()+")";
        }
    %>
    <div class="mt-2 container container-fluid text-dark">
        <div class="container container-fluid">
            <div class="row">
                <div class="col-sm-1">

                </div>

                <div class="col-sm-10">

                    <div class="container">
                        <div class="table-responsive resoconto-ordine mt-4">
                            <table class="table table-striped mb-4 fs-5">
                                <tr><th>Indirizzo</th><td>Via <%=o.getIndirizzo()%></td></tr>
                                <tr><th>Ordinato il</th><td><%=o.getDataOrdinato()%></td></tr>
                                <tr><th>Ordinato alle</th><td><%=o.getOraOrdinato()%></td></tr>
                                <tr><th>Consegnato alle</th><td><%=oraConsegna%></td></tr>
                                <tr><th>Fattorino</th><td><%=fattorino%></td></tr>
                            </table>
                            <div class="progress">
                                <div id="barra" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <h1 class="h2 display-4 mt-4 titolo">PRODOTTI ORDINATI</h1>
                <%
                    if(elementiOrdinati!=null)
                    {
                        String prezzoTot= String.valueOf(0);
                        String prezzoEl= String.valueOf(0);
                %>
                    <div class="table-responsive">
                        <table class="table table-striped text-center">
                            <%
                                for(ElementoCarrello el : elementiOrdinati)
                                {
                                    Prodotto p= el.getProdotto();

                                    prezzoEl= String.valueOf(p.getPrezzo()*el.getQuantita());
                                    if(prezzoEl.length()<=4)
                                    {
                                        prezzoEl=prezzoEl+"0";
                                    }
                                    prezzoEl=prezzoEl.replace(".",",");
                            %>
                            <tr>
                                <td class="d-none d-md-table-cell"><img src="<%=p.getImmagine()%>" class="img mt-4 mb-4 fs-5 img-prodottoMenu"></td>
                                <td><p class="h4 mt-4 mb-4 fs-5"><a style="text-decoration: none;" class="text-dark fw-bold" href="mostraProdottoServlet?id=<%=p.getId()%>"><%=p.getNome().toUpperCase()%></a></p></td>
                                <td><p class="mt-4 mb-4 fs-5"><%=prezzoEl%>&euro;</p></td>
                                <td><p class="mt-4 mb-4 fs-5"><em class="font-weight-normal">x<%=el.getQuantita()%></em></p></td>
                            </tr>
                            <%
                                }
                                prezzoTot= String.valueOf(o.getTotale());
                                if(prezzoTot.length()<=4)
                                {
                                    prezzoTot=prezzoTot+"0";
                                }
                                prezzoTot=prezzoTot.replace(".",",");
                            %>
                            <tr class="text-center titolo-riga">
                                <td class="h3 text-light fw-bold titolo">TOTALE:</td>
                                <th class="h3 text-light" colspan="3"><%=prezzoTot%>&euro;</th>
                            </tr>
                        </table>
                    </div>
                    <%

                    }
                    else
                    {
                    %>
                    <h3 class="h3 display-4 mt-4">Ordine vuoto!</h3>
                    <%
                        }
                    %>
                </div>

                <div class="col-sm-1">

                </div>
            </div>
        </div>
    </div>
    <footer class="mt-auto">
        <%@  include file="/footer.jsp" %>
    </footer>
    <script src="<%=request.getContextPath()%>/script/progressBarOrdine.js" defer></script>
</body>
</html>
