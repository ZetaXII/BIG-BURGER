package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet(name="eliminaProdottoServlet", urlPatterns="/eliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="";
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u==null || !(u.getRuolo().equalsIgnoreCase("a")))
        {
            indirizzo="error/accessoNegato.jsp";
        }
        else
        {
            ProdottoDAO prodottoDAO= new ProdottoDAO();
            int idProdotto= Integer.parseInt(request.getParameter("idProdotto"));

            prodottoDAO.doDeleteProdotto(idProdotto);

            List<Prodotto> prodotti = prodottoDAO.doRetrieveFirstSix();
            getServletContext().setAttribute("prodotti", prodotti);

            indirizzo="menuServlet";

        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);

    }
}
