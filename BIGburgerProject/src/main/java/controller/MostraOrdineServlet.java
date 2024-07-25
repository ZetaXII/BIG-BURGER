package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name="mostraOrdineServlet", urlPatterns="/mostraOrdineServlet")
public class MostraOrdineServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String indirizzo="/WEB-INF/results/mostraInfoOrdine.jsp";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");

        if(u!= null)
        {
            ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
            OrdineDAO ordineDAO = new OrdineDAO();
            UtenteDAO utenteDAO = new UtenteDAO();
            int idOrdine= Integer.parseInt(request.getParameter("idOrdine"));
            Ordine o= ordineDAO.doRetrieveById(idOrdine);

            Utente f= utenteDAO.doRetrieveFattorinoByIdOrdine(idOrdine,o.getIdFattorino());

            ArrayList<ElementoCarrello> elementiOrdinati= elementoCarrelloDAO.doRetrieveByIdOrdine(idOrdine);
            request.setAttribute("elementiOrdinati", elementiOrdinati);
            request.setAttribute("ordine", o);
            request.setAttribute("fattorino", f);

        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);

    }
}


