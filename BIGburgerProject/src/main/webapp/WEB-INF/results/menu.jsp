<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.Categoria" %>
<%
    ArrayList<Prodotto> prodotti= (ArrayList<Prodotto>) request.getAttribute("prodotti");
    String categoria="";
    ServletContext context= session.getServletContext();
    ArrayList<Categoria> categorie= (ArrayList<Categoria>) context.getAttribute("categorie");
%>
<html>
<head>
    <link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Men&ugrave;</title>
    <style>
        .filtro-menu:hover i{color: #f7701b; transition: 0.3s; !important;}
        .filtro-menu{height: 70px; padding:20px;}
    </style>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <div>
        <%@  include file="/navBar.jsp" %>
    </div>

    <h1 class="h1 display-1 mt-4 titolo">MEN&Ugrave;:</h1>
    <div class="mt-4 container container-fluid text-dark">
        <div class="container container-fluid">
            <div class="row">
                <div class="col-sm">

                </div>

                <div class="col-sm-12">

                    <form action="menuServlet" method="post" class="form-inline">
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="TUTTI"><i class="fas fa-drumstick-bite"></i> TUTTI</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="SFIZI"><i class="fas fa-hotdog"></i> SFIZI</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="PANINI"><i class="fas fa-hamburger"></i> PANINI</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="PIZZE"><i class="fas fa-pizza-slice"></i> PIZZE</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="MEN&Ugrave;"><i class="fas fa-utensils"></i> MEN&Ugrave;</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="BEVANDE"><i class="fas fa-wine-glass"></i> BEVANDE</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="DOLCI"><i class="fas fa-candy-cane"></i> DOLCI</button>
                        <button type="submit" name="cat" class="btn btn-dark mt-1 filtro-menu fs-4" value="ALTRO"><i class="fas fa-bacon"></i> ALTRO</button>
                    </form>

                    <%
                        if(u!=null && u.getRuolo().equalsIgnoreCase("a"))
                        {
                    %>
                    <a href="<%=request.getContextPath()%>/aggiungiProdotto.jsp"><button class="btn btn-success mt-2 mb-4">Aggiungi un nuovo prodotto</button></a>
                    <%
                        }

                        if(prodotti!=null && prodotti.size()>0)
                        {
                    %>

                    <div class="table-responsive w-100" style="margin:0; width:100%;">
                        <table class="table table-striped">
                            <%
                                for(Prodotto p: prodotti)
                                {
                                    if(!categoria.equals(p.getCategoria()))
                                    {
                                        categoria=p.getCategoria();
                            %>
                            <tr class="text-light titolo-riga">
                                <th colspan="5"><p class="h3 nome-menu text-white"><%=p.getCategoria().toUpperCase()%></p></th>
                            </tr>
                            <%
                                    }
                                String prezzo= String.valueOf(p.getPrezzo());
                                if(prezzo.length()<=4)
                                {
                                    prezzo=prezzo+"0";
                                }
                                prezzo=prezzo.replace(".",",");
                            %>
                            <tr class="text-center">
                                <td class="d-none d-md-table-cell"><img src="<%=p.getImmagine()%>" class="img mt-4 mb-4 fs-5 img-prodottoMenu"></td>
                                <td><p class="nome-menu mt-4 mb-4 fs-4"><a style="text-decoration: none;" class="text-dark fw-bold" href="mostraProdottoServlet?id=<%=p.getId()%>"><%=p.getNome().toUpperCase()%></a></p></td>
                                <td><p class="descrizione-menu mt-4 mb-5 fst-italic fs-5 d-block d-none d-sm-block"><em>"<%=p.getDescrizione()%>."</em></p></td>
                                <td><p class="prezzo-menu mt-4 mb-4 fs-4"><%=prezzo%>&euro;</p></td>
                                <td>
                            <%
                                if(u!=null)
                                {
                                    if(u.getRuolo().equalsIgnoreCase("u"))
                                    {
                            %>
                                    <form action="aggiungiAlCarrelloServlet" method="get" class="form form-responsive mt-3">
                                        <input type="hidden" name="idUtente" value="<%=u.getId()%>">
                                        <input type="hidden" name="idProdotto" value="<%=p.getId()%>">
                                        <input type="number" name="quantita" value="1" min="1" max="25" class="form-control" required>
                                        <input type="submit" value="Aggiungi" class="btn btn-dark mt-3" style="background: #f7701b; border:none;">
                                    </form>
                            <%
                                    }
                                    else if(u.getRuolo().equalsIgnoreCase("a"))
                                    {
                            %>
                                        <a href="eliminaProdottoServlet?idProdotto=<%=p.getId()%>" class="btn btn-danger mt-3">Elimina</a>

                                        <form action="modificaProdottoServlet" method="post">
                                            <input type="hidden" name="idProdotto" value="<%=p.getId()%>">
                                            <input type="hidden" name="recuperaInfo" value="1">
                                            <input type="submit" value="Modifica" class="btn btn-primary mt-3">
                                        </form>
                            <%
                                    }
                            %>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </div>
                    <%
                        }
                        else
                        {
                    %>
                    <h3 class="h3 display-4 mt-4">Nessun prodotto presente!</h3>
                    <%
                        }
                    %>
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
