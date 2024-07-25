<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
  <title>Nuovi Arrivi</title>
</head>
<body>
  <div>
    <h1 class="h1 display-3 text-center mb-4 titolo">SCOPRI LE NOVT&Agrave;!</h1>
    <div class="container-prodotto">
      <%
        for(Prodotto p: prodotti)
        {
          String prezzo= String.valueOf(p.getPrezzo());
          if(prezzo.length()<=4)
          {
            prezzo=prezzo+"0";
          }
          prezzo=prezzo.replace(".",",");
      %>
      <div class="prodotto">
        <div class="testo-prodotto">
          <h1 class="nome-menu"><%=p.getNome().toUpperCase()+" <br/>"+prezzo+"&euro;"%></h1>
          <p><em>"<%=p.getDescrizione()%>."</em></p>
          <p class="nome-menu fs-5"></p>
        </div>
        <a href="mostraProdottoServlet?id=<%=p.getId()%>">
          <img src="<%=p.getImmagine()%>" class="img-prodotto">
        </a>
      </div>
      <%
        }
      %>
    </div>
  </div>
</body>
</html>
