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

@WebServlet(name="ordinaServlet", urlPatterns="/ordinaServlet")
public class OrdinaServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String indirizzo="";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");

        if(u!= null && u.getRuolo().equalsIgnoreCase("u"))
        {
            ArrayList<ElementoCarrello> carrello= (ArrayList<ElementoCarrello>) session.getAttribute("carrello");
            ArrayList<ElementoCarrello> elCancellati=null;
            if(session.getAttribute("elCancellati")!=null)
            {
                elCancellati = (ArrayList<ElementoCarrello>) session.getAttribute("elCancellati");
            }

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

            double totale= Double.parseDouble(request.getParameter("totale"));
            String indirizzoSpedizione=request.getParameter("via")+", "+request.getParameter("nCivico");

            OrdineDAO ordineDAO= new OrdineDAO();

            ordineDAO.createOrdine(u.getId(),totale, indirizzoSpedizione);
            Ordine o=ordineDAO.doRetrieveLastByIdUtente(u.getId());

            int idOrdine=o.getId();
            carrello=elementoCarrelloDAO.doRetrieveByIdUtenteSenzaIdOrdine(u.getId());

            for(ElementoCarrello el : carrello)
            {
                elementoCarrelloDAO.ordinaElementoDelCarrello(el.getId(), idOrdine);
            }

            indirizzo="mostraOrdineServlet?idOrdine="+idOrdine;
            session.setAttribute("carrello",null);
        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }
        response.sendRedirect(indirizzo);
    }
}
