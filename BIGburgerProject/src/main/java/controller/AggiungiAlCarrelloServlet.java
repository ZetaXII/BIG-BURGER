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
import java.util.ArrayList;

@WebServlet(name="aggiungiAlCarrelloServlet", urlPatterns="/aggiungiAlCarrelloServlet")
public class AggiungiAlCarrelloServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String indirizzo="menuServlet";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u!= null && u.getRuolo().equalsIgnoreCase("u"))
        {
            ArrayList<ElementoCarrello> carrello = (ArrayList<ElementoCarrello>) session.getAttribute("carrello");
            if(request.getParameter("idUtente")!=null)
            {
                ProdottoDAO service = new ProdottoDAO();
                int idUtente = Integer.parseInt(String.valueOf(request.getParameter("idUtente")));
                int idProdotto = Integer.parseInt(String.valueOf(request.getParameter("idProdotto")));
                int quantita = Integer.parseInt(String.valueOf(request.getParameter("quantita")));
                Prodotto p = service.doRetrieveById(idProdotto);
                ElementoCarrello elementoCarrello = new ElementoCarrello(idUtente, idProdotto, quantita, p);

                if (carrello == null)
                {
                    carrello = new ArrayList<>();
                    carrello.add(elementoCarrello);
                }
                else
                {
                    boolean check = false;
                    for (ElementoCarrello el : carrello)
                    {
                        if (el.getIdProdotto() == p.getId())
                        {
                            int quantitaTot = el.getQuantita() + quantita;
                            if(quantitaTot>25)
                            {
                                quantitaTot=25;
                            }
                            el.setQuantita(quantitaTot);
                            check = true;
                            break;
                        }
                    }
                    if (check == false)
                    {
                        carrello.add(elementoCarrello);
                    }
                }
                session.setAttribute("carrello", carrello);
            }
            else if (carrello == null)
            {
                ElementoCarrelloDAO elementoCarrelloDAO= new ElementoCarrelloDAO();
                carrello=elementoCarrelloDAO.doRetrieveByIdUtenteSenzaIdOrdine(u.getId());
                if(carrello.size()>0)
                {
                    session.setAttribute("carrello", carrello);
                }
            }
        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);

        //response.sendRedirect(indirizzo);

    }
}