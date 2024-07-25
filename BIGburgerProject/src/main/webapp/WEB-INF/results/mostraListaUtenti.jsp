<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Utente> utenti= (ArrayList<Utente>) request.getAttribute("utenti");
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>LISTA UTENTI</title>
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
                    if(u!=null && u.getRuolo().equalsIgnoreCase("a"))
                    {
                    %>
                    <h1 class="h1 display-1 mt-4 titolo">LISTA UTENTI:</h1>

                    <form action="mostraListaUtentiServlet" method="get" class="form-inline form-responsive">
                        <div class="input-group">
                            <select name="ruoloSrc" class="form-control">
                                <option value="">Tutti</option>
                                <option value="A">Admin</option>
                                <option value="U">Utente</option>
                                <option value="F">Fattorino</option>
                            </select>

                            <input type="submit" value="Filtra per ruolo" class="btn btn-dark">
                        </div>
                    </form>

                    <%
                        if(utenti==null || utenti.size()<1)
                        {
                    %>
                        <h3 class="h3 display-4 mt-4">Nessun utente presente!</h3>
                    <%
                        }
                        else
                        {
                    %>
                    <div class="table-responsive">
                        <table class="table borderless text-center">
                            <tr class="text-light titolo-riga">
                                <th class="fs-5 mt-4 mb-4">#</th>
                                <th class="fs-5 mt-4 mb-4">Nome</th>
                                <th class="fs-5 mt-4 mb-4">Cognome</th>
                                <th class="fs-5 mt-4 mb-4">Telefono</th>
                                <th class="fs-5 mt-4 mb-4">E-mail</th>
                                <th class="fs-5 mt-4 mb-4">Ruolo</th>
                                <th></th>
                                <th></th>
                            </tr>
                        <%

                                String ruolo="";
                                for(Utente utente: utenti)
                                {

                                    if(utente.getRuolo().equalsIgnoreCase("A"))
                                    {
                                        ruolo= "<span class=\"badge badge bg-danger\">Admin</span>";
                                    }
                                    else if(utente.getRuolo().equalsIgnoreCase("U"))
                                    {
                                        ruolo= "<span class=\"badge badge bg-primary\">Utente</span>";
                                    }
                                    else if(utente.getRuolo().equalsIgnoreCase("F"))
                                    {
                                        ruolo= "<span class=\"badge badge bg-warning\">Fattorino</span>";
                                    }
                            %>
                            <tr>
                                <th><p class="fs-5 mt-4 mb-4"><%=utente.getId()%></p></th>
                                <td><p class="fs-5 mt-4 mb-4"><%=utente.getNome()%></p></td>
                                <td><p class="fs-5 mt-4 mb-4"><%=utente.getCognome()%></p></td>
                                <td><p class="fs-5 mt-4 mb-4"><%=utente.getTelefono()%></p></td>
                                <td><p class="fs-5 mt-4 mb-4"><%=utente.getEmail()%></p></td>
                                <td class="text-center"><p class="fs-5 mt-4 mb-4"><%= ruolo %></p></td>
                                <td>
                                    <form action="mostraUtenteServlet" method="post">
                                        <input type="hidden" name="idUtente" value="<%=utente.getId()%>">
                                        <input type="hidden" name="modifica" value="1">
                                        <input type="submit" value="Modifica Dati" name="Modifica" class="btn btn-primary mt-4 mb-4">
                                    </form>
                                </td>
                                <td><a href="mostraUtenteServlet?idUtente=<%=utente.getId()%>"><i class="fas fa-info-circle fs-1 mt-4 mb-4"></i></a></td>
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

                <%
                    }
                %>

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
