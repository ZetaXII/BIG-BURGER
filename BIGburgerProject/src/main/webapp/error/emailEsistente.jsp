<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/style.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/favicon.ico" type="image/x-icon">
    <title>Utente non trovato...</title>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
<div>
    <%@ include file="/navBar.jsp" %>
</div>

<div class="mt-4 container text-black">
    <div class="container">
        <div class="row">
            <div class="col-sm">

            </div>

            <div class="col-sm-8 w-100">
                <h2 class="h1 display-2 mt-4 titolo" style="font-weight: 800;">E-MAIL NON DISPONIBILE</h2>
                <h3 class="h3 display-4 mt-4 descrizione">Ci dispiace, ma sembra che questa E-mail sia gi&agrave; in uso</h3>
            </div>

            <div class="col-sm">

            </div>
        </div>
    </div>
</div>
<footer class="mt-auto">
    <%@ include file="/footer.jsp" %>
</footer>
</body>
</html>