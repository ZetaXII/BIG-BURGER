package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ElementoCarrello;
import model.ElementoCarrelloDAO;
import model.Utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name="logout", urlPatterns="/logout")
public class LogoutServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session= request.getSession();
        Utente u= (Utente) session.getAttribute("utente");

        if(u!= null && u.getRuolo().equalsIgnoreCase("u")) //inserisce gli elementi del carrello che sono in sessione nel DB prima di effettuare il logout
        {
            ArrayList<ElementoCarrello> carrello= (ArrayList<ElementoCarrello>) session.getAttribute("carrello");
            ArrayList<ElementoCarrello> elCancellati= (ArrayList<ElementoCarrello>) session.getAttribute("elCancellati");
            ElementoCarrelloDAO elementoCarrelloDAO= new ElementoCarrelloDAO();
            ArrayList<ElementoCarrello> carrelloDB= elementoCarrelloDAO.doRetrieveByIdUtenteSenzaIdOrdine(u.getId());
            if(carrello!=null)
            {
                if(carrelloDB==null || carrelloDB.size()<=0)
                {
                    for(ElementoCarrello el : carrello)
                    {
                        elementoCarrelloDAO.salvaElementoCarrello(el);
                    }
                }
                else
                {
                    for(ElementoCarrello el : carrello)
                    {
                        int ugualeAlDb=0;
                        for(ElementoCarrello elDb : carrelloDB)
                        {
                            if(elDb.getIdProdotto()==el.getIdProdotto())
                            {
                                ugualeAlDb++;
                            }

                            if((elDb.getId()==el.getId()) && (elDb.getQuantita()!=el.getQuantita()))
                            {
                                elementoCarrelloDAO.doUpdateQuantita(el);
                            }
                        }
                        if(ugualeAlDb<=0)
                        {
                            elementoCarrelloDAO.salvaElementoCarrello(el);
                        }
                    }
                }
            }
            if(elCancellati!=null)
            {
                for(ElementoCarrello el : elCancellati)
                {
                    for(ElementoCarrello elDb : carrelloDB)
                    {
                        if((elDb.getId()==el.getId()))
                        {
                            elementoCarrelloDAO.doDropElementoById(el);
                        }
                    }
                }
            }
        }

        session.invalidate();
        String indirizzo="index.jsp";

        response.sendRedirect(indirizzo);
    }
}