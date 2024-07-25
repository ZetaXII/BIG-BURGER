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

@WebServlet(name="eliminaProdottoDalCarrelloServlet", urlPatterns="/eliminaProdottoDalCarrelloServlet")
public class EliminaProdottoDalCarrelloServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String indirizzo="carrello.jsp";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if(u!= null && u.getRuolo().equalsIgnoreCase("u"))
        {
            int i=0;
            int id= Integer.parseInt(request.getParameter("id"));
            ProdottoDAO service = new ProdottoDAO();
            Prodotto p= service.doRetrieveById(id);
            ArrayList<ElementoCarrello> carrello= (ArrayList<ElementoCarrello>) session.getAttribute("carrello");
            ArrayList<ElementoCarrello> elCancellati= (ArrayList<ElementoCarrello>) session.getAttribute("elCancellati");
            if(elCancellati==null)
            {
                elCancellati=new ArrayList<>();
            }

            if(carrello!=null)
            {
                for(ElementoCarrello el : carrello)
                {
                    Prodotto elP= el.getProdotto();
                    if(elP.getId()==p.getId())
                    {
                        carrello.remove(i);
                        elCancellati.add(el);
                        break;
                    }
                    i++;
                }

                if(carrello.size()<=0)
                {
                    carrello=null;
                }

                session.setAttribute("carrello", carrello);
                session.setAttribute("elCancellati", elCancellati);
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
