<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Categoria" %>
<%
    ServletContext context= session.getServletContext();
    ArrayList<Categoria> categorie= (ArrayList<Categoria>) context.getAttribute("categorie");
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>LISTA CATEGORIE</title>
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
                <h1 class="h1 display-1 mt-4 titolo">LISTA CATEGORIE:</h1>

                <%
                        if(categorie==null || categorie.size()<1)
                        {
                %>
                <h3 class="h3 display-4 mt-4">Nessuna categoria presente!</h3>
                <%
                        }
                        else
                        {
                %>
                <div class="table-responsive">
                    <table class="table borderless text-center">
                        <tr class="text-light titolo-riga">
                            <th class="fs-5 mt-4 mb-4">Nome</th>
                        </tr>
                        <%
                            for(Categoria c: categorie)
                            {
                        %>
                        <tr>
                            <td><p class="fs-5 mt-4 mb-4"><%=c.getNome().toUpperCase()%></p></td>
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
                    else
                    {
                        response.sendRedirect("error/accessoNegato.jsp");
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
