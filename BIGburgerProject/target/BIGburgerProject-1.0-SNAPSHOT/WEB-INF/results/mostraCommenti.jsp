<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Commento" %><%
    ArrayList<Commento> commenti= (ArrayList<Commento>) request.getAttribute("commenti");
    int totPerStella[]= (int[]) request.getAttribute("totPerStella");
    double totaleVoto= (double) request.getAttribute("totaleVoto");
    if(commenti.size()<=0)
    {
        totaleVoto=0;
    }
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Sezione Commenti</title>
</head>

<body>
    <div>
        <%@  include file="/navBar.jsp" %>
    </div>
    <div class="mt-4 container container-fluid text-dark">
        <div class="container container-fluid">
            <div class="row">
                <div class="col-sm-1">

                </div>

                <div class="col-sm-10">
                    <h1 class="h1 display-1 mt-4 titolo text-center">I VOSTRI PARERI:</h1>
                    <table class="resoconto-commenti table table-striped mb-4">
                        <tr>
                            <th colspan="2">
                                <p class="stella-media" style="display: inline"><i class="fa-solid fa-star"></i></p>
                                <p style="display: inline">MEDIA DI <%=totaleVoto%></p>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <p class="stelle">
                                    <i class="fa-solid fa-star"></i>
                                </p>
                            </td>
                            <td><p>(<%=totPerStella[0]%> voti)</p></td>
                        </tr>
                        <tr>
                            <td>
                                <p class="stelle">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </p>
                            </td>
                            <td><p>(<%=totPerStella[1]%> voti)</p></td>
                        </tr>
                        <tr>
                            <td>
                                <p class="stelle">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </p>
                            </td>
                            <td><p>(<%=totPerStella[2]%> voti)</p></td>
                        </tr>
                        <tr>
                            <td>
                                <p class="stelle">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </p>
                            </td>
                            <td><p>(<%=totPerStella[3]%> voti)</p></td>
                        </tr>
                        <tr>
                            <td>
                                <p class="stelle">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </p>
                            </td>
                            <td><p>(<%=totPerStella[4]%> voti)</p></td>
                        </tr>
                    </table>

    <%
        if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
        {
    %>
            <form action="recuperaCommentoServlet" method="post">
                    <div class="d-grid gap-2 m-4">
                        <button type="submit" class="btn btn-dark w-100" type="button">DICCI LA TUA!</button>
                    </div>
            </form>
    <%
        }

        for(Commento c : commenti)
        {
    %>
        <div class="container-commento" style="text-align: left;">
            <div class="utente-commento">
                <img src="https://thumbs.dreamstime.com/b/profilo-utente-vettoriale-avatar-predefinito-179376714.jpg" class="img-utente">
                <p class="nome-utente"><%=c.getUtente().getNome().charAt(0)+". "+c.getUtente().getCognome()%></p>
            </div>
            <p class="data-commento">Recensito il <%=c.getData()%></p>
            <p class="stelle"><i class="fa-solid fa-star"></i> <%=c.getStelle()%> stelle</p>
            <div class="descrizione-commento">
                <em>"<%=c.getCommento()%>."</em>
            </div>
    <%
        if(u!=null && u.getRuolo().equalsIgnoreCase("a"))
        {
    %>
            <a href="eliminaCommentoServlet?idUtente=<%=c.getUtente().getId()%>"><button class="btn btn-block btn-danger mb-2 w-100">Elimina</button></a>
    <%
        }
    %>
        </div>
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
</body>
</html>
