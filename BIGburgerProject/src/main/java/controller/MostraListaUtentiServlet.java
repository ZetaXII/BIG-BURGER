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

@WebServlet(name="mostraListaUtentiServlet", urlPatterns="/mostraListaUtentiServlet")
public class MostraListaUtentiServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String indirizzo="/WEB-INF/results/mostraListaUtenti.jsp";
        HttpSession session = request.getSession();
        Utente u= (Utente) session.getAttribute("utente");

        String ruoloReq= request.getParameter("ruoloSrc");


        if(u!= null && u.getRuolo().equalsIgnoreCase("a"))
        {
            UtenteDAO utenteDAO = new UtenteDAO();

            ArrayList<Utente> utenti= new ArrayList<>();

            if(ruoloReq==null || ruoloReq.equalsIgnoreCase(""))
            {
                utenti= utenteDAO.doRetrieveAll(u.getId());
            }
            else
            {
                utenti= utenteDAO.doRetrieveAllByRuolo(ruoloReq, u.getId());
            }

            request.setAttribute("utenti", utenti);
        }
        else
        {
            indirizzo="error/accessoNegato.jsp";
        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }
}
