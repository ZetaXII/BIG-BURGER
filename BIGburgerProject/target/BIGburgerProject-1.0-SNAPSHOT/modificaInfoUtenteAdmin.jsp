<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Modifica info utente</title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
<div>
    <%@  include file="navBar.jsp" %>
</div>
<%
    if(u==null || !(u.getRuolo().equalsIgnoreCase("a")))
    {
        response.sendRedirect("index.jsp");
    }
    else
    {
        Utente utente= (Utente) request.getAttribute("utente");
%>
<div class="mt-4 container text-black">
    <div class="container">
        <div class="row">
            <div class="col-sm">

            </div>

            <div class="col-sm w-100 mb-4 text-left">
                <form action="modificaUtenteAdminServlet" method="post">
                    <h3 class="h3 display-4 titolo">MODIFICA CREDENZIALI UTENTE #<%=utente.getId()%>:</h3><br/><br/>

                    <input type="hidden" name="id" value="<%=utente.getId()%>">

                    <label for="nome" class="label fw-bold m-4">Nome</label>
                    <input type="text" name="nome" placeholder="Nome" id="nome" maxlength="20" class="form-control" value="<%= utente.getNome() %>" required>

                    <label for="cognome" class="label fw-bold m-4">Cognome</label>
                    <input type="text" name="cognome" placeholder="Cognome" id="cognome" maxlength="30" class="form-control" value="<%= utente.getCognome() %>" required>

                    <label for="tel" class="label fw-bold m-4">Telefono</label>
                    <input type="tel" name="tel" placeholder="+39 numero" id="tel" class="form-control" pattern="[0-9]{10}" value="<%= utente.getTelefono() %>" disabled required>

                    <label for="emailRegistra" class="label fw-bold m-4">E-mail</label>
                    <input type="email" name="email" placeholder="email@email.com" id="emailRegistra" maxlength="50" minlength="10" class="form-control" value="<%= utente.getEmail() %>" disabled required>

                    <label for="pass" class="label fw-bold m-4">Password</label>
                    <input type="password" name="psw" placeholder="password" id="pass" class="form-control" value="<%= utente.getPsw() %>" maxlength="30" minlength="4" disabled required>

                    <label for="ruolo" class="label fw-bold m-4">Ruolo</label>
                    <%
                        if(utente.getRuolo().equalsIgnoreCase("a"))
                        {
                    %>
                    <select name="ruolo" id="ruolo" class="form-control">
                        <option value="A" selected>Admin</option>
                        <option value="U">Utente</option>
                        <option value="F">Fattorino</option>
                    </select>
                    <%
                        }
                        else if(utente.getRuolo().equalsIgnoreCase("u"))
                        {
                    %>
                    <select name="ruolo" id="ruolo" class="form-control">
                        <option value="A">Admin</option>
                        <option value="U" selected>Utente</option>
                        <option value="F">Fattorino</option>
                    </select>
                    <%
                        }
                        else if(utente.getRuolo().equalsIgnoreCase("f"))
                        {
                    %>
                    <select name="ruolo" id="ruolo" class="form-control">
                        <option value="A">Admin</option>
                        <option value="U">Utente</option>
                        <option value="F" selected>Fattorino</option>
                    </select>
                    <%
                        }
                    %>

                    <input type="submit" value="Modifica" class="btn btn-block btn-primary mt-3">
                </form>
            </div>

            <div class="col-sm">

            </div>
        </div>
    </div>
</div>
<%
    }
%>
<footer class="mt-auto">
    <%@  include file="/footer.jsp" %>
</footer>
</body>
</html>
