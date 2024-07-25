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
    if(u==null)
    {
        response.sendRedirect("index.jsp");
    }
    else
    {
%>
<div class="mt-4 container text-black">
    <div class="container">
        <div class="row">
            <div class="col-sm">

            </div>

            <div class="col-sm w-100 mb-4 text-left">
                <form action="modificaUtenteServlet" method="post">
                    <h3 class="h3 display-4 titolo">MODIFICA CREDENZIALI:</h3><br/><br/>

                    <input type="hidden" name="id" value="<%=u.getId()%>">

                    <label for="nome" class="label fw-bold m-4">Nome</label>
                    <input type="text" name="nome" placeholder="Nome" maxlength="20" id="nome" class="form-control" value="<%= u.getNome() %>" required>

                    <label for="cognome" class="label fw-bold m-4">Cognome</label>
                    <input type="text" name="cognome" placeholder="Cognome" maxlength="30" id="cognome" class="form-control" value="<%= u.getCognome() %>" required>

                    <label for="tel" class="label fw-bold m-4">Telefono</label>
                    <input type="tel" name="tel" placeholder="+39 numero" id="tel" class="form-control" pattern="[0-9]{10}" value="<%= u.getTelefono() %>" required>

                    <label for="emailRegistra" class="label fw-bold m-4">E-mail</label>
                    <input type="email" name="email" placeholder="email@email.com" id="emailRegistra" maxlength="50" minlength="10" class="form-control" value="<%= u.getEmail() %>" required>

                    <label for="pass" class="label fw-bold m-4">Password</label>
                    <input type="password" name="psw" placeholder="password" id="pass" class="form-control" maxlength="30" minlength="4" value="<%= u.getPsw() %>" required>

                    <input type="hidden" name="ruolo" value="<%=u.getRuolo()%>">

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
