<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Footer</title>
</head>
<body id="body" class="d-flex flex-column min-vh-100">
    <footer class="text-center text-lg-start mt-auto text-light">
        <section class="d-flex justify-content-center p-4 text-center">
            <div class="me-3 d-none d-lg-block">
                <span>Seguici anche su:</span>
            </div>
            <div>
                <a href="" class="me-4 text-reset">
                    <i class="fab fa-facebook-f"></i>
                </a>
                <a href="" class="me-4 text-reset">
                    <i class="fab fa-twitter"></i>
                </a>
                <a href="" class="me-4 text-reset">
                    <i class="fab fa-instagram"></i>
                </a>
                <a href="" class="me-4 text-reset">
                    <i class="fab fa-linkedin"></i>
                </a>
            </div>
        </section>

        <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05); border: none;">
            &#169; <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %> Copyright: <b>BIG Burger</b>
        </div>

    </footer>

</body>
</html>
