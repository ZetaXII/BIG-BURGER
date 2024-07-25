<%
    int check= (int) request.getAttribute("check"); //check sarà 0 se non esiste il commento dell'utente in sessione
%>
<html>
<head>
    <link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <title>Commenta</title>
    <style>
        label {
            cursor: pointer;
        }

        svg {
            width: 3rem;
            height: 3rem;
            padding: 0.15rem;
        }


        /* hide radio buttons */

        input[name="star"] {
            display: inline-block;
            width: 0;
            opacity: 0;
            margin-left: -2px;
        }

        /* hide source svg */

        .star-source {
            width: 0;
            height: 0;
            visibility: hidden;
        }


        /* set initial color to transparent so fill is empty*/

        .star {
            color: transparent;
            transition: color 0.2s ease-in-out;
        }


        /* set direction to row-reverse so 5th star is at the end and ~ can be used to fill all sibling stars that precede last starred element*/

        .star-container {
            display: flex;
            flex-direction: row-reverse;
            justify-content: center;
        }

        label:hover ~ label .star,
        svg.star:hover,
        input[name="star"]:focus ~ label .star,
        input[name="star"]:checked ~ label .star {
            color: #f7701b;
        }

        input[name="star"]:checked + label .star {
            animation: starred 0.5s;
        }

        input[name="star"]:checked + label {
            animation: scaleup 1s;
        }

        @keyframes scaleup {
            from {
                transform: scale(1.2);
            }
            to {
                transform: scale(1);
            }
        }

        @keyframes starred {
            from {
                color: #f7701b;
            }
            to {
                color: #f7701b;
            }
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100 text-center">
    <div>
        <%@  include file="/navBar.jsp" %>
    </div>
        <%
            if(u!=null && u.getRuolo().equalsIgnoreCase("u"))
            {
                String descrizione="";
                String servlet="aggiungiCommentoServlet";
                int stars=0;
                if(check==1)
                {
                    descrizione= (String) request.getAttribute("descrizione");
                    stars= (int) request.getAttribute("star");
                    servlet="modificaCommentoServlet";
                }
        %>
    <div class="mt-4 container container-fluid text-dark">
        <div class="container container-fluid">
            <div class="row">
                <div class="col-sm-1">

                </div>

                <div class="col-sm commento">
                    <a href="mostraCommentiServlet"><button type="button" class="btn btn-dark" style="background-color: #f7701b;"><i class="fa fa-arrow-left"></i></button></a>
                    <h1 class="h1 display-1 mt-4 titolo">IL TUO PARERE:</h1>
                    <h3 class="display-5 text-center">Come valuteresti la tua esperienza?</h3><br/>
                    <form action="<%=servlet%>" method="post" class="rating">

                        <div class="star-source">
                            <svg>
                                <linearGradient x1="50%" y1="5.41294643%" x2="87.5527344%" y2="65.4921875%" id="grad">
                                    <stop stop-color="#f7701b" offset="0%"></stop>
                                    <stop stop-color="#f7701b" offset="60%"></stop>
                                    <stop stop-color="#f7701b" offset="100%"></stop>
                                </linearGradient>
                                <symbol id="star" viewBox="153 89 106 108">
                                    <polygon id="star-shape" stroke="url(#grad)" stroke-width="12" fill="currentColor" points="206 162.5 176.610737 185.45085 189.356511 150.407797 158.447174 129.54915 195.713758 130.842203 206 95 216.286242 130.842203 253.552826 129.54915 222.643489 150.407797 235.389263 185.45085"></polygon>
                                </symbol>
                            </svg>

                        </div>
                        <div class="star-container">
                        <%
                            String[] numeri={"one", "two", "three", "four", "five"};
                            int j=5;
                            for(int i=0; i<5; i++)
                            {
                                if(stars==j)
                                {
                        %>
                                    <input type="radio" name="star" id="<%=numeri[i]%>" value="<%=j%>" checked="checked" required>
                        <%
                                }
                                else
                                {
                        %>
                                    <input type="radio" name="star" id="<%=numeri[i]%>" value="<%=j%>" required>
                        <%
                                }
                        %>
                                <label for="<%=numeri[i]%>">
                                    <svg class="star">
                                        <use xlink:href="#star"/>
                                    </svg>
                                </label>
                        <%
                                j--;
                            }
                        %>
                        </div>

                        <h3 class="display-5 text-center mt-4">Dicci di pi&ugrave;:</h3>
                        <textarea maxlength="250" class="form-control" name="descrizione" required><%=descrizione%></textarea>
                        <input type="submit" value="Commenta" class="btn btn-dark mt-3" style="border: none; background-color: #f7701b;">
                        <%
                            if(check==1)
                            {
                        %>
                        <br/><a href="eliminaCommentoServlet" class="mt-4"><button type="button" class="btn btn-danger mt-4">Elimina</button></a>
                        <%
                            }
                        %>
                    </form>
                </div>

                <div class="col-sm-1">

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
