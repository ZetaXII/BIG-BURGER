package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name="modificaStatoOrdineServlet", urlPatterns="/modificaStatoOrdineServlet")
public class ModificaStatoOrdineServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String indirizzo="mostraListaOrdiniServlet";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u!= null && (u.getRuolo().equalsIgnoreCase("a") || u.getRuolo().equalsIgnoreCase("f")))
        {
            String stato= request.getParameter("stato");
            int idOrdine= Integer.parseInt(request.getParameter("idOrdine"));

            OrdineDAO ordineDAO= new OrdineDAO();
            ordineDAO.doUpdateStatoOrdine(idOrdine, stato, u.getId());

            if(u.getRuolo().equalsIgnoreCase("f") && stato.equalsIgnoreCase("Consegnato"))
            {
                indirizzo="mostraListaOrdiniServlet?consegnati=no";
            }

        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }
}