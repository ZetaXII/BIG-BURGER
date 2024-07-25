package controller;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet(name = "ordineJSON", value = "/ordineJSON")
public class OrdineJSON extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrdineDAO ordineDAO= new OrdineDAO();
        int idOrdine= Integer.parseInt(request.getParameter("idOrdine"));
        Ordine ordine= ordineDAO.doRetrieveById(idOrdine);
        JSONObject oj= new JSONObject();

        oj.put("stato", ordine.getStato());
        oj.put("fattorino", ordine.getIdFattorino());

        response.getWriter().write(oj.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }
}
