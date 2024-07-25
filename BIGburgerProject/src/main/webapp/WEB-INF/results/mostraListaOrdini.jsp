<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Ordine" %>
<%
    ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>LISTA ORDINI</title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <div>
        <%@  include file="/navBar.jsp" %>
    </div>

    <div class="mt-4 container container-fluid text-dark">
        <div class="container container-fluid">
            <div class="row">
                <div class="col-sm-1">

                </div>

                <div class="col-sm-10">

                    <%
                        if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
                        {
                    %>
                    <h1 class="h1 display-1 mt-4 titolo">I MIEI ORDINI:</h1>
                    <%
                    }
                    else if(u!=null && u.getRuolo().equalsIgnoreCase("a"))
                    {
                    %>
                    <h1 class="h1 display-1 mt-4 titolo">LISTA ORDINI:</h1>

                    <form action="mostraListaOrdiniServlet" method="get" class="form-inline form-responsive">
                        <div class="input-group">
                            <input type="date" class="form-control" name="data">

                            <select name="statoSrc" class="form-control">
                                <option value="">Tutti</option>
                                <option value="Ordinato">Ordinato</option>
                                <option value="In preparazione">In preparazione</option>
                                <option value="In spedizione">In spedizione</option>
                                <option value="Consegnato">Consegnato</option>
                            </select>

                            <input type="submit" value="Filtra per data e/o stato" class="btn btn-dark">
                        </div>
                    </form>

                    <%
                        }
                    %>

                    <%
                        if(ordini==null || ordini.size()<1)
                        {
                    %>
                        <h3 class="h3 display-4 mt-4">Nessun ordine presente!</h3>
                    <%
                        }
                        else
                        {
                    %>
                    <div class="table-responsive">
                        <table class="table borderless text-center">
                            <tr class="text-light titolo-riga">
                                <th class="fs-5 mt-4 mb-4">#</th>
                        <%
                                if(u!=null && (u.getRuolo().equalsIgnoreCase("a") || u.getRuolo().equalsIgnoreCase("f")))
                                {
                        %>
                                    <th class="fs-5 mt-4 mb-4">Id Utente</th>
                        <%
                                }
                        %>
                                <th class="fs-5 mt-4 mb-4">Data ordine</th>
                                <th class="fs-5 mt-4 mb-4">Totale</th>
                        <%
                                if(u!=null && (u.getRuolo().equalsIgnoreCase("f")))
                                {
                        %>
                                <th class="fs-5 mt-4 mb-4">Indirizzo</th>
                                <th></th>
                        <%
                                }
                                else
                                {
                        %>
                                <th class="fs-5 mt-4 mb-4">Stato</th>
                        <%
                                }

                                if(u!=null && (u.getRuolo().equalsIgnoreCase("a")))
                                {
                        %>
                                <th></th>
                        <%
                                }
                        %>
                                <th></th>
                            </tr>
                        <%

                                String stato="";
                                for(Ordine o: ordini)
                                {
                                    String prezzo= String.valueOf(o.getTotale());
                                    if(prezzo.length()<=4)
                                    {
                                        prezzo=prezzo+"0";
                                    }
                                    prezzo=prezzo.replace(".",",");

                                    if(u!=null && !(u.getRuolo().equalsIgnoreCase("f")))
                                    {
                                        if(o.getStato().equalsIgnoreCase("Ordinato"))
                                        {
                                            stato= "<span class=\"badge badge bg-primary\">Ordinato</span>";
                                        }
                                        else if(o.getStato().equalsIgnoreCase("In preparazione"))
                                        {
                                            stato= "<span class=\"badge badge bg-warning\">In preparazione</span>";
                                        }
                                        else if(o.getStato().equalsIgnoreCase("In spedizione"))
                                        {
                                            stato= "<span class=\"badge badge bg-danger\">In spedizione</span>";
                                        }
                                        else if(o.getStato().equalsIgnoreCase("Consegnato"))
                                        {
                                            stato= "<span class=\"badge badge bg-success\">Consegnato</span>";
                                        }
                                        else
                                        {
                                            stato= "<span class=\"badge badge bg-dark\">NULL</span>";
                                        }
                                    }
                            %>
                            <tr>
                                <th><p class="fs-5 mt-4 mb-4"><%=o.getId()%></p></th>
                                <%
                                    if(u!=null && (u.getRuolo().equalsIgnoreCase("a") || u.getRuolo().equalsIgnoreCase("f")))
                                    {
                                %>
                                <td><p class="fs-5 mt-4 mb-4"><a href="mostraUtenteServlet?idUtente=<%=o.getIdUtente()%>" class="text-dark">#<%=o.getIdUtente()%></a></p></td>
                                <%
                                    }
                                %>
                                <td><p class="fs-5 mt-4 mb-4"><%=o.getDataOrdinato()%></p></td>
                                <td><p class="fs-5 mt-4 mb-4"><%= prezzo %>&euro;</p></td>
                                <%
                                    if(u!=null && u.getRuolo().equalsIgnoreCase("f"))
                                    {
                                %>
                                <td class="text-center"><p class="fs-5 mt-4 mb-4"><%= o.getIndirizzo() %></p></td>
                                <%
                                    }
                                    else
                                    {
                                %>
                                <td class="text-center"><p class="fs-5 mt-4 mb-4"><%= stato %></p></td>
                                <%
                                    }

                                    if(u!=null && u.getRuolo().equalsIgnoreCase("a"))
                                    {
                                        if(o.getStato().equalsIgnoreCase("Ordinato"))
                                        {
                                %>
                                <td>
                                    <form action="modificaStatoOrdineServlet" method="post">
                                        <input type="hidden" name="idOrdine" value="<%=o.getId()%>">
                                        <input type="hidden" value="In preparazione" name="stato">
                                        <input type="submit" class="btn btn-warning m-4" value="Prepara">
                                    </form>
                                </td>
                                <%
                                        }
                                        else if(o.getStato().equalsIgnoreCase("In preparazione"))
                                        {
                                %>
                                <td>
                                    <form action="modificaStatoOrdineServlet" method="post">
                                        <input type="hidden" name="idOrdine" value="<%=o.getId()%>">
                                        <input type="hidden" value="In spedizione" name="stato">
                                        <input type="submit" class="btn btn-danger m-4" value="Spedisci">
                                    </form>
                                </td>
                                <%
                                        }
                                        else if(o.getStato().equalsIgnoreCase("In spedizione"))
                                        {
                                %>
                                <td>
                                    <form action="modificaStatoOrdineServlet" method="post">
                                        <input type="hidden" name="idOrdine" value="<%=o.getId()%>">
                                        <input type="hidden" value="Consegnato" name="stato">
                                        <input type="submit" class="btn btn-success m-4" value="Consegna">
                                    </form>
                                </td>
                                <%
                                        }
                                        else
                                        {
                                %>
                                <td>

                                </td>
                                <%
                                        }
                                    }

                                    if(u!=null && u.getRuolo().equalsIgnoreCase("f"))
                                    {
                                        if(o.getStato().equalsIgnoreCase("In preparazione"))
                                        {
                                %>
                                <td>
                                    <form action="modificaStatoOrdineServlet" method="post">
                                        <input type="hidden" name="idOrdine" value="<%=o.getId()%>">
                                        <input type="hidden" value="In spedizione" name="stato">
                                        <input type="submit" class="btn btn-danger m-4" value="Prendi incarico">
                                    </form>
                                </td>
                                <%
                                        }
                                        else if(o.getStato().equalsIgnoreCase("In spedizione"))
                                        {
                                %>
                                <td>
                                    <form action="modificaStatoOrdineServlet" method="post">
                                        <input type="hidden" name="idOrdine" value="<%=o.getId()%>">
                                        <input type="hidden" value="Consegnato" name="stato">
                                        <input type="submit" class="btn btn-success m-4" value="Consegna">
                                    </form>
                                </td>
                                <%
                                        }
                                        else
                                        {
                                %>
                                <td>

                                </td>
                                <%
                                        }
                                    }
                                %>
                                <td><a href="mostraOrdineServlet?idOrdine=<%=o.getId()%>"><i class="fas fa-info-circle fs-1 mt-4 mb-4"></i></a></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <%
                            }
                        %>
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
