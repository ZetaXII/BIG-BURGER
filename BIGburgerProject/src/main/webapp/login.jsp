<%
    Cookie[] cookie = request.getCookies();
    String email="";
    String psw="";
    for ( int i=0; i<cookie.length; i++)
    {

        Cookie c = cookie[i];

        if (c.getName().equals("email"))
        {
            email=c.getValue();
        }

        if (c.getName().equals("psw"))
        {
            psw=c.getValue();
        }
    }
%>
<html>
<head>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Area di accesso</title>
</head>
<body>
    <div>
        <%@  include file="navBar.jsp" %>
    </div>

    <div class="mt-4 container text-black">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <form action="login" method="post" class="form btn-block" id="formAccedi">
                        <h3 class="h3 display-4 titolo text-center">ACCEDI</h3><br/><br/>

                        <label for="emailLogin" class="label">E-mail</label>
                        <input type="email" name="email" placeholder="email@email.com" id="emailLogin" class="form-control" maxlength="50" minlength="10" value="<%=email%>" required>

                        <label for="pswLogin" class="label">Password</label>
                        <input type="password" name="psw" placeholder="password" id="pswLogin" class="form-control" maxlength="30" minlength="4" value="<%=psw%>" required>

                        <div class="form-check mt-3">
                            <label class="form-check-label" for="ricordami">
                                Ricordami
                            </label>
                            <input class="form-check-input" type="checkbox" value="si" id="ricordami" name="ricordami">
                        </div>

                        <input type="submit" id="loginBtn" value="Entra" class="btn btn-block btn-dark mt-2 w-100" style="background-color: #f7701b; border:none">
                    </form>
                </div>
                <div class="col-sm-12">
                    <form action="registra" method="post" class="form btn-block" id="formRegistra">
                        <h3 class="h3 display-4 titolo text-center">REGISTRATI</h3><br/><br/>

                        <label for="nome" class="label">Nome</label>
                        <input type="text" name="nome" placeholder="Nome" id="nome" maxlength="20" class="form-control" required>

                        <label for="cognome" class="label">Cognome</label>
                        <input type="text" name="cognome" placeholder="Cognome" id="cognome" maxlength="30" class="form-control" required>

                        <label for="tel" class="label">Telefono</label>
                        <input type="tel" name="tel" placeholder="Telefono" id="tel" class="form-control" pattern="[0-9]{10}" required>

                        <label for="emailRegistra" class="label">E-mail</label>
                        <input type="email" name="email" placeholder="email@email.com" id="emailRegistra" maxlength="50" minlength="10" class="form-control" required>

                        <label for="pswRegistra" class="label">Password</label>
                        <input type="password" name="psw" placeholder="password" id="pswRegistra" maxlength="30" minlength="4" class="form-control" required>

                        <input type="hidden" name="checkCode" value="0">

                        <input type="submit" id="registraBtn" value="Registrati" class="btn btn-block btn-dark mt-2 w-100" style="background-color: #f7701b; border:none">
                    </form>
                    <p class="text-center">oppure</p>
                    <button id="btnRegistra" class="btn btn-block btn-dark mt-2 mb-2 w-100">Registrati</button>
                    <button id="btnAccedi" class="btn btn-block btn-dark mt-2 mb-2 w-100">Accedi</button>
                </div>
            </div>
        </div>
    </div>

    <footer class="mt-auto">
        <%@  include file="/footer.jsp" %>
    </footer>
    <script src="<%=request.getContextPath()%>/script/checkLogin.js" defer></script>
    <script src="<%=request.getContextPath()%>/script/checkRegistra.js" defer></script>
    <script src="<%=request.getContextPath()%>/script/switchAccediRegistra.js" defer></script>
</body>
</html>
