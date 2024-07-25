package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="menuServlet", urlPatterns="/menuServlet")
public class MenuServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String check= request.getParameter("check");
        String categoria= request.getParameter("cat");
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        ArrayList<Prodotto> p = (ArrayList<Prodotto>) prodottoDAO.doRetrieveAll();
        String indirizzo="/WEB-INF/results/menu.jsp";

        if(categoria!=null && !(categoria.equalsIgnoreCase("tutti")))
        {
            p = prodottoDAO.doRetrieveByCategoria(categoria);
        }

        if(p==null)
        {
            indirizzo="error/prodottoNonTrovato.jsp";
        }

        request.setAttribute("prodotti", p);

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request,response);
    }
}