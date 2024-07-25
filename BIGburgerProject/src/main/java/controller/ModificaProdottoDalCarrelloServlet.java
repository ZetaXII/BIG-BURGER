package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElementoCarrello;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="modificaProdottoDalCarrelloServlet", urlPatterns="/modificaProdottoDalCarrelloServlet")
public class ModificaProdottoDalCarrelloServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        String indirizzo= "carrello.jsp";
        if(u!= null && u.getRuolo().equalsIgnoreCase("u"))
        {
            int i = 0;
            int id = Integer.parseInt(request.getParameter("idProdotto"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            ProdottoDAO service = new ProdottoDAO();
            Prodotto p = service.doRetrieveById(id);

            ArrayList<ElementoCarrello> carrello = (ArrayList<ElementoCarrello>) session.getAttribute("carrello");

            if (carrello != null)
            {
                for (ElementoCarrello el : carrello)
                {
                    Prodotto elP = el.getProdotto();
                    if (elP.getId() == p.getId())
                    {
                        el.setQuantita(quantita);
                        break;
                    }
                    i++;
                }

                if (carrello.size() <= 0)
                {
                    carrello = null;
                }

                session.setAttribute("carrello", carrello);
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