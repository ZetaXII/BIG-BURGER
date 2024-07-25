package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name="mostraUtenteServlet", urlPatterns="/mostraUtenteServlet")
public class MostraUtenteServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        UtenteDAO utenteDAO = new UtenteDAO();
        String indirizzo="modificaInfoUtenteAdmin.jsp";
        if(request.getParameter("modifica")==null)
        {
            indirizzo="/WEB-INF/results/mostraInfoUtente.jsp";
        }

        int id= Integer.parseInt(request.getParameter("idUtente"));
        Utente utente=null;
        utente= utenteDAO.doRetrieveById(id);

        if(utente==null)
        {
            indirizzo="error/utenteNonTrovato.jsp";
        }
        else
        {
            request.setAttribute("utente", utente);
        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(indirizzo);
        dispatcher.forward(request,response);
    }
}