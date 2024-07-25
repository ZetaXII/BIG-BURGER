<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Categoria" %>
<%
    ServletContext context= session.getServletContext();
    ArrayList<Categoria> categorie= (ArrayList<Categoria>) context.getAttribute("categorie");
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Aggiungi prodotto</title>
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

                <div class="col-sm w-100 mb-4 text-left">
                    <form action="aggiungiProdottoServlet" method="post">
                        <h3 class="h3 display-4 titolo">AGGIUNGI PRODOTTO:</h3><br/><br/>

                        <label for="nome" class="label fw-bold m-4">Nome</label>
                        <input type="text" name="nome" id="nome" class="form-control" required>

                        <label for="descrizione" class="label fw-bold m-4">Descrizione</label>
                        <textarea name="descrizione" maxlength="150" id="descrizione" class="form-control" required></textarea>

                        <label for="prezzo" class="label fw-bold m-4">Prezzo</label>
                        <input name="prezzo" step="0.01" id="prezzo" class="form-control" required>

                        <label for="categoria" class="label fw-bold m-4">Categoria</label>
                        <select name="categoria" id="categoria" class="form-control">
                            <%
                                for(Categoria c : categorie)
                                {
                            %>
                            <option value="<%= c.getNome() %>" ><%= c.getNome().toUpperCase()%> </option>
                            <%
                                }
                            %>
                        </select>

                        <label for="immagine" class="label fw-bold m-4">URL immagine</label>
                        <input type="url" name="immagine" id="immagine" class="form-control" required>

                        <input type="submit" value="Aggiungi" class="btn btn-block btn-success mt-3">
                    </form>
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
