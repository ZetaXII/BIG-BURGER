<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/style.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/favicon.ico" type="image/x-icon">
    <title>Accesso negato...</title>
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

            <div class="col-sm-8 w-100">
                <h1 class="h1 display-1 mt-4 titolo" style="font-weight: 800;">ACCESSO NEGATO!</h1>
                <h3 class="h3 display-4 mt-4 descrizione">Credenziali errate o permessi non consentiti.</h3>
                <a href="<%=request.getContextPath()%>/login.jsp"><button class="btn btn-dark btn-block m-4" style="background: #f7701b; border: none;">VAI AL LOGIN</button></a>
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
